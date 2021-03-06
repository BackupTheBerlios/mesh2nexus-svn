package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import server.SAPConnector;

/**
 * Kontrolliert den Server-Start
 */
public class Server {
	
	public static Integer Port;
	public static Registry registry;
	public static Properties properties;
	

	/**
	 * Startet den Server
	 */
	public static void main(String args[]){		
		
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

		try {
			// Neue Registry am Port erstellen
			registry = LocateRegistry.createRegistry(Port);
			System.out.println("> RMI Registry am Port " + Port + " erstellt");
			
			try {
				// Implementation vom ServerInterface bei der Registry anmelden
				registry.rebind("ServerFunctions", new server.ServerInterfaceImpl());
				System.out.println("> ServerFunctions bei der RMI Registry angemeldet");
				
			} catch (Exception e) {
				
				System.err.println("> ServerFunctions konnten bei RMI Registry nicht angemeldet werden: \n" + e);
			}
				
		} catch (Exception e) {
			System.err.println("> RMI Registry konnte nicht gestartet werden: \n" + e);
		}
		
		// Pool anlegen
		SAPConnector.StartConnectionPool();
		
		// Verbindung zu SAP testen
		SAPConnector.TestConnection();
			
		System.out.println("> Server gestartet");	

	}
	
	/**
	 * Funktion zum Laden der Einstellungen 
	 */
	private static void LoadFromProperties (){
		
		Port = (new Integer (properties.getProperty("Port"))).intValue();
		SAPConnector.LoadFromProperties(properties);
		
	}	
}
