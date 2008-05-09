package server;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import communication.SAPConnector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * 
 */
public class ServerStart {
	
	public static Integer Port;
	public static Registry registry;
	public static Properties properties;

	/**
	 * 
	 */
	public static void main(String[] args){
		
		System.out.println("> Server Start...");
		
		// Properties laden
		properties = new Properties();
		
		try {
			// "server.properties." laden
			properties.load(new FileInputStream("res/server.properties"));
			
			// Geladene Werte werden entsprechenden Variablen zugewiesen
			LoadFromProperties ();
			
			System.out.println("> Properties geladen");
			
		} catch (FileNotFoundException e) {
			System.err.println("Properties Datei nicht gefunden \n" + e);
		} catch (IOException e1) {
			System.err.println(e1);
		}
		

		
		// Pool anlegen, verbindung zu Sap testen
		SAPConnector con = new SAPConnector();
		
		
	
		try {
			// Neue Registry am Port erstellen
			registry = LocateRegistry.createRegistry(Port);
			System.out.println("> RMI Registry am Port " + Port + " erstellt");
			
			try {
				// Implementation vom ServerInterface bei der Registry anmelden
				registry.rebind("ServerFunctions", new communication.ServerInterfaceImpl());
				System.out.println("> ServerFunctions bei der RMI Registry angemeldet");
				
			} catch (Exception e) {
				
				System.err.println("ServerFunctions konnten bei RMI Registry nicht angemeldet werden \n" + e);
			}
				
		} catch (Exception e) {
			System.err.println("RMI Registry konnte nicht gestartet werden \n" + e);
		}
	
			
		System.out.println("> Server gestartet");
		
		
		
//	    con.systemInfo();
	    con.salesOrders();
	    con.cleanUp();
	
	}
	
	/**
	 * 
	 *
	 */
	private static void LoadFromProperties (){
		
		Port = (new Integer (properties.getProperty("Port"))).intValue();
		SAPConnector.LoadFromProperties(properties);
		
	}
	
}
