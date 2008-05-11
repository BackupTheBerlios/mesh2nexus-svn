/**
 * 
 */
package control;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.sap.mw.jco.JCO;

import communication.ServerInterface;
import gui.ErrorDialog;
import gui.LogonGUI;
import gui.MainGUI;
import gui.QueryGUI;

/**
 * @author 
 *
 */
public class Controller {
	
	public static String ServerURL;
	public static ServerInterface server;
	
	public static LogonGUI logonGui;
	public static MainGUI mainGui;
	public static QueryGUI queryGui;	
	public static ErrorDialog errDialog;
	public static boolean connect = false;

	/**
	 * @param 
	 */
//	public static String getServerURL() {
//		
//		return logonGui.text.getText();
//		//return "ura";
//	}

	public static void StartLogonGui() {		
		logonGui = new LogonGUI();		
	}

	public static void ShowErrorDialog() {		
		errDialog = new ErrorDialog();		
		

	}

//	public static void setServerURL(String text) {
//		ClientStart.ServerURL = text;
//		
//	}
	

	public static String ConnectToServer(String text) {
		
		String response = "Es ist ein Fehler aufgetreten";
		// Serverdaten auslesen	
		ServerURL = text;
		
		// Verbindung zum Server aufbauen
		try {
			server = (ServerInterface) Naming.lookup("rmi://"+ ServerURL +"/ServerFunctions");
			// Get response
			response = server.getString();
			System.out.println(response);
			connect = true;
			return response;
			
		} catch (MalformedURLException e) {
			//  
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO: Parameter fuer Dialog uebergeben!
			//ShowErrorDialog();
			e.printStackTrace();
		} catch (NotBoundException e) {
			//  
			e.printStackTrace();
		}
		
		return response ;
		
		
	}

	public static void StartMainGui() {
		mainGui = new MainGUI();	
		
	}

	public static void StartQueryGui() {
		queryGui = new QueryGUI();	
		
	}
	
	public static JCO.Table CreateTable(String ura) {
		
		JCO.Table sales_orders = null;
		try {
			// RPC!
			sales_orders = server.getSalesOrderList("0000100001", "WING", "2005-05-28", "2005-05-30", "0");
			
			if (sales_orders == null) {
				System.err.println("Funktion konnte nicht ausgeführt werden, siehe Server Log");
			}
			else {
				System.out.println(sales_orders.getNumRows());
				//return sales_orders;
			}

		} catch (RemoteException e) {
			// 
			e.printStackTrace();
		}
		// Valeri4 eto 4ish dage ne smotri)))))))))))))))))
		
		// Spalten
		
	           for (JCO.FieldIterator e = sales_orders.fields(); e.hasMoreElements(); ) {
	        	   JCO.Field field = e.nextField();
	               // Output
	               //System.out.println(field.getName() + ":\t" + field.getString());
	      		
	               TableColumn id = new TableColumn(MainGUI.table,SWT.LEFT);				
	               id.setText(field.getName());				
	               id.setWidth(150);
	      		   
	            }//for


		
		// Zeilen
	           String[] items = new String[1000];
	           int i = 0;
		do {
           System.out.println("-----------------------------------------");
           //
           //TableItem item1 = new TableItem(MainGUI.table,SWT.NONE);
           // Loop over all columns in the current row
           for (JCO.FieldIterator e = sales_orders.fields(); e.hasMoreElements(); ) {
        	   JCO.Field field = e.nextField();
               // Output
               System.out.println(field.getName() + ":\t" + field.getString());
      		items[i] = field.getString();  
      		i++;
      		
      		
      		TableItem item1 = new TableItem(MainGUI.table,SWT.NONE);
            item1.setText(field.getString() + i);
      		   
            }//for
           //item1.setText(items);
           
        } while(sales_orders.nextRow());

		
		return sales_orders;
	}

	public static void CloseLogonGui() {
		//LogonGUI.display.getActiveShell().dispose();
		//LogonGUI.display.getActiveShell().close();
		
		
	}

	public static void CloseQueryGui() {
		
		LogonGUI.display.getActiveShell().close();
		
	}




}
