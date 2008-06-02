package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import server.ServerInterface;
import com.sap.mw.jco.JCO;

/**
 * Klasse enthält programmstartende main-Methode sowie
 * Hilfsfunktionen für Kommunikation mit dem SAP-Server
 */
public class Client {

	// ServerInterface, ermöglicht Aufrufe entfernter Funktionen
	public static ServerInterface server;
	
	// Tabelle (JCO.Table), Container für Verkaufsbelege, die vom
	// SAP-Server geholt werden
	public static JCO.Table sales_orders;
	
	/**
	 *  Programm-Starter, öffnet das Hauptfenster
	 */
	public static void main(String[] args){		
		new gui.MainWindow();		
	}
	
	/**
	 * Baut eine Verbindung zum Server auf, falls die Verbindung nicht hergestellt
	 * werden kann, wird ClientException geworfen
	 */
	public static void ConnectToServer(String ServerURL) throws ClientException {

		// Prüft, ob auf dem angegebenem Rechner (ServerURL = IP:Port)
		// RMI-Registry gestartet ist und entsprechnde Interface-Implementierung
		// registriert ist
		try {
			server = (ServerInterface) Naming.lookup("rmi://" + ServerURL
					+ "/ServerFunctions");			

		} catch (Exception e) {
			throw new ClientException("SAP-Server ist nicht verfügbar");
		}		
	}	
	
	/**
	 * Sendet eine Anfrage an SAP-Server und speichert die Ergebnis-Tabelle
	 * in sales_orders vom Datentyp JCO.Table
	 */
	public static void getSapTable(String CustNumber,
			String SalesOrg,
			String DocDate,
			String DocDateTo,
			String TAGroup) throws ClientException{
		
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
			// auf dem Server vor, keine Konnektivität zur SAP-DB
			if (tmpSales == null){ 
				
				throw new ClientException("SAP-Server ist nicht mit SAP-Datenbank verbunden");
			}
			// sonst
			else{
				// prüfe ob die Ergebnistabelle keine Einträge enthält
				if (tmpSales.getNumRows() == 0){
					throw new ClientException("Keine Ergebnisse zu dieser Anfrage");
				}
				// alles in Ordnung -> speichere die Tabelle in 'sales_orders'
				else{
					sales_orders = tmpSales;
				}
				
			}
		// falls Verbindung zum SAP-Server nicht mehr vorhanden ist
		} catch (RemoteException e) {
			
			throw new ClientException("SAP-Server ist nicht verfügbar");
		}
		
	}
}
