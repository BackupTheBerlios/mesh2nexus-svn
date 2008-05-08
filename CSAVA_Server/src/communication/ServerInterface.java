package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote{

	/*
	 * Test Funktion
	 */
	public String getString () throws RemoteException;
	
	
}
