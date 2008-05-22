package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import server.ServerInterface;
import com.sap.mw.jco.JCO;

/**
 * Klasse enth�lt programmstartende main-Mothode sowie
 * Hilfsfunktionen f�r Kommunikation mit dem SAP-Server
 */
public class Client {

	// ServerInterface, erm�glicht Aufrufe entfernter Funktionen
	public static ServerInterface server;
	
	// Tabelle (JCO.Table), Container f�r Verkaufsbelege, die vom
	// SAP-Server geholte werden
	public static JCO.Table sales_orders;
	
	/**
	 *  Programm-Starter, �ffnet das Hauptfenster
	 */
	public static void main(String[] args){		
		new gui.MainWindow();		
	}
	
	/**
	 * Baut eine Verbindung zum Server auf
	 */
	public static boolean ConnectToServer(String ServerURL) {

		// Pr�ft, ob auf dem angegebenem Rechner (ServerURL = IP:Port)
		// RMI-Registry gestartet ist und entsprechnde Interface-Implementierung
		// registriert ist
		try {
			server = (ServerInterface) Naming.lookup("rmi://" + ServerURL
					+ "/ServerFunctions");			

			return true;

		} catch (Exception e) {
			
			return false;
		}		
	}	
	
	/**
	 * Sendet eine Anfrage an SAP-Server und speichert die Ergebnis-Tabelle
	 * in sales_orders vom Datentyp JCO.Table
	 */
	public static String getSapTable(String CustNumber,
			String SalesOrg,
			String DocDate,
			String DocDateTo,
			String TAGroup) {
		
		JCO.Table tmpSales = null;
		
		try {
			
			// Aufruf entfernter Funktion
			tmpSales = server.getSalesOrderList(CustNumber,
					SalesOrg,
					DocDate,
					DocDateTo,
					TAGroup);

			// Aufarbeitung der Ergebnisse
			
			// falls Ergebnistabelle = null ist, liegt ein Problem
			// auf dem Server vor, keine Konnektivit�t zur SAP-DB
			if (tmpSales == null){ 
				
				return "SAP-Server ist nicht mit SAP-Datenbank verbunden";
			}
			// sonst
			else{
				// pr�fe ob die Ergebnistabelle keine Eintr�ge enth�lt
				if (tmpSales.getNumRows() == 0){
					return "Keine Ergebnisse zu dieser Anfrage";
				}
				// alles in Ordnung -> speichere die Tabelle in 'sales_orders'
				else{
					sales_orders = tmpSales;
				}
				
			}
		// falls Verbindung zum SAP-Server nicht mehr vorhanden ist
		} catch (RemoteException e) {
			
			return "SAP-Server ist nicht verf�gbar";
		}
		
		return "OK";
	}
}
