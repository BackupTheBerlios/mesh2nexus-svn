package server;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerStart {
	
	public static Integer Port;
	public static Registry registry;

	public static void main(String[] args){
		
		System.out.println("> Server Start...");
		// lade Properties
		// Pool anlegen, verbindung zu Sap testen
		// RMI registry starten
		// Interface Impl binden
		
		Port = 21;
	
		try {
			// Neue Registry am Port erstellen
			registry = LocateRegistry.createRegistry(Port);
			System.out.println("> Registry am Port " + Port + " erstellt");
	
			// Implementation vom ServerInterface bei der Registry anmelden
			registry.rebind("ServerFunctions", new communication.ServerInterfaceImpl());
			System.out.println("> ServerFunctions bei der Registry angemeldet");
		
			
			
			System.out.println("> Server gestartet");
			
		} catch (AccessException e) {
			// TODO
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO
			e.printStackTrace();
		}
	
	
	}
}
