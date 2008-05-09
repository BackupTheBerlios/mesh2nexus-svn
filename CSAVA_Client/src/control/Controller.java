/**
 * 
 */
package control;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import communication.ServerInterface;
import gui.ErrorDialog;
import gui.LogonGUI;

/**
 * @author 
 *
 */
public class Controller {
	
	public static String ServerURL;
	public static ServerInterface server;
	
	public static LogonGUI logonGui;
	public static ErrorDialog errDialog;

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
	

	public static String connectToServer(String text) {
		
		String response = "Fehler..";
		// Serverdaten auslesen	
		ServerURL = text;
		
		// Verbindung zum Server aufbauen
		try {
			server = (ServerInterface) Naming.lookup("rmi://"+ ServerURL +"/ServerFunctions");
			// Get response
			response = server.getString();
			System.out.println(response);
			return response;
			
		} catch (MalformedURLException e) {
			// TODO: 
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO: Parameter fuer Dialog uebergeben!
			//ShowErrorDialog();
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO: 
			e.printStackTrace();
		}
		
		return response ;
		
		
	}
	

}
