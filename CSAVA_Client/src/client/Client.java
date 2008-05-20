/**
 * 
 */
package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import server.ServerInterface;
import com.sap.mw.jco.JCO;

/**
 * @author
 * 
 */
public class Client {

	// Public static String ServerURL;
	public static ServerInterface server;	
	// Public static JCO.Table
	public static JCO.Table sales_orders;
	
	/*
	 *  Start MainWindow
	 */
	public static void main(String[] args){		
		new gui.MainWindow();		
	}
	
	/*
	 * 
	 */
	public static boolean ConnectToServer(String ServerURL) {

		// Connect to Server
		try {
			server = (ServerInterface) Naming.lookup("rmi://" + ServerURL
					+ "/ServerFunctions");			

			return true;

		} catch (Exception e) {
			
			return false;
		}		
	}	
	
	/*
	 * 
	 */
	public static String getSapTable(String CustNumber,
			String SalesOrg,
			String DocDate,
			String DocDateTo,
			String TAGroup) {
		
		// JCO.Table
		sales_orders = null;
		
		try {
			// RPC!
			sales_orders = server.getSalesOrderList(CustNumber,
					SalesOrg,
					DocDate,
					DocDateTo,
					TAGroup);

			if (sales_orders == null){ 
				return "SAP-Server ist nicht mit SAP-Datenbank verbunden";

			}
			else{
				
				if (sales_orders.getNumRows() == 0){
					return "Keine Ergebnisse zu dieser Anfrage";
				}
				
			}
		} catch (RemoteException e) {
			return "SAP-Server ist nicht verfügbar";
		}
		
		return "OK";
	}
}
