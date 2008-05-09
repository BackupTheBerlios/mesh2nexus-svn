package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.sap.mw.jco.JCO;

/**
 * Server Interface, definiert Funktionen, die den Clients
 * vom Server zur Verfügung gestellt werden
 */
public interface ServerInterface extends Remote{

	/**
	 * Test Funktion
	 */
	public String getString () throws RemoteException;
	
	/**
	 * Liefert Verkaufsbelege von SAP-Datenbank
	 */
	public JCO.Table getSalesOrderList (	String CustNumber,
										String SalesOrg,
										String DocDate,
										String DocDateTo,
										String TAGroup) throws RemoteException;
	
}	
