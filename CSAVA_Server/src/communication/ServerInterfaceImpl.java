package communication;

import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;

import com.sap.mw.jco.JCO;

/**
 * Implementierung der Methoden des ServerInterfaces
 */
public class ServerInterfaceImpl extends UnicastRemoteObject implements ServerInterface, Serializable {

	/**
	 * Konstruktor
	 */
	public ServerInterfaceImpl() throws RemoteException {
	}
	
	/**
	 * Test
	 */
	public String getString () throws RemoteException {
		
		return "Eto servernyj string))";
	}
	
	/**
	 * Liefert Verkaufsbelege von SAP-Datenbank.
	 * Funktion wird im eigenen Thread ausgeführt. Ein RMI-Aufruf erzeugt
	 * automatisch einen neuen Thread auf der Serverseite. 
	 */
	public JCO.Table getSalesOrderList (	String CustNumber,
										String SalesOrg,
										String DocDate,
										String DocDateTo,
										String TAGroup) throws RemoteException {
		
		
		JCO.Table tmp = SAPConnector.getSalesOrders(CustNumber, SalesOrg, DocDate, DocDateTo, TAGroup);
		// TODO problem mit cleanUp lösen
		SAPConnector.cleanUp();
		return tmp;
		
	}

	
	
}
