package communication;

import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;

public class ServerInterfaceImpl extends UnicastRemoteObject implements ServerInterface, Serializable {

	/*
	 * Konstruktor
	 */
	public ServerInterfaceImpl() throws RemoteException {
	}
	
	public String getString ()throws RemoteException {
		
		return "Eto servernyj string))";
	}
	
	
}
