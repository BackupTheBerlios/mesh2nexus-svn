package client;

import java.net.MalformedURLException;
import java.rmi.*;

import communication.ServerInterface;

public class ClientStart {
	
	public static String ServerURL;
	public static ServerInterface server;
	
	
	public static void main(String[] args){
		
		
		// Start Verbindungsdialog
		// Serverdaten auslesen
		// Verbindung zum Server aufbauen
		// Start Haupt-Gui
		
		
		ServerURL =  "92.227.33.232:21";
		
		try {
			server = (ServerInterface) Naming.lookup("rmi://"+ ServerURL +"/ServerFunctions");
			
			System.out.println(server.getString());
			
		} catch (MalformedURLException e) {
			// TODO
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	

}
