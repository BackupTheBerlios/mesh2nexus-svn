package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Server Interface, definiert Funktionen, die den Clients
 * vom Server zur Verf�gung gestellt werden
 */
public interface ServerInterface extends Remote{

	/**
	 * Test Funktion
	 */
	public String getString () throws RemoteException;
	
	
}
