package server;

import com.sap.mw.jco.*;
import java.util.Properties;


/**
 * SAP Connector, Funktionen zur Aufbau der Verbindung zu SAP
 * Realisierung der Anfragen an SAP
 */
public class SAPConnector {

	
  private static IRepository repository;
  
  // SAP Verbindungsdaten
  private static String PoolName;
  private static String MaxConnections;
  private static String SAPClient;
  private static String UserId;
  private static String Pass;
  private static String Language;
  private static String HostName;
  private static String SystemNumber;
  

  /**
   * Konstruktor. 
   */
  public SAPConnector(){}
  
  /**
   * Legt einen ConnectionPool an
   */
  public static void StartConnectionPool(){
	  try {
      
		  // Ein neuer Connection-Pool wird erzeugt. Muss am beim Anhalten
		  // des Servers explizit gelöscht werden
	      JCO.addClientPool(
	    		  PoolName,         							// Pool Name
	    		  (new Integer(MaxConnections)).intValue(),   	// Max. Anzahl der Verbindungen
	    		  SAPClient,									// Mandant
	    		  UserId,   									// Benutzer
	              Pass,     									// Passwort
	              Language,        								// Sprache
	              HostName, 									// Hostname (SAProuter-String + Anwendungsserver
	              SystemNumber									// Systemnummer
	              );

	      System.out.println("> JCO Connection Pool angelegt");
	      
	      // Neues Repository wird erstellt. Enthällt alle Definitionen
	      // von Funktionen und Strukturen
	      repository = JCO.createRepository("MYRepository", PoolName);
	      
	      System.out.println("> JCO Repository erstellt");
	      
	  }
	  catch (JCO.Exception ex) {
		  System.err.println("> Fehler in 'SAPConnector.StartConnectionPool()': \n " + ex);
	  }
  }

  /**
   * Testet Verbindung zu SAP
   */
  public static void TestConnection(){
	  
	  try{
		  System.out.println("> Teste Verbindung zu SAP...");
		  JCO.getClient(PoolName);
		  System.out.println("> Verbindung steht");
		  
	  }
	  catch(Exception e){
		  System.err.println("> Es konnte keine Verbindung zu SAP aufgebaut werden: \n" + e);
	  }
      
  }
  
  /**
   * Lädt SalesOrderList von SAP und gibt die Ergebnistabelle zurück
   */
  @SuppressWarnings("finally")
public static JCO.Table getSalesOrders(
		  	String CustNumber,
			String SalesOrg,
			String DocDate,
			String DocDateTo,
			String TAGroup){
	
	  JCO.Client client = null;
	  JCO.Table result = null;

	  try {
		  // Funktionstemplate vom Repository anlegen
		  IFunctionTemplate ftemplate = repository.getFunctionTemplate("BAPI_SALESORDER_GETLIST");

		  // falls Funktionsdefinition vorhanden
		  if(ftemplate != null) {

			  // Erstelle eine neue Funktion vom Template
			  JCO.Function function = ftemplate.getFunction();

			  // Fordere eine Verbindung vom Pool an
			  client = JCO.getClient(PoolName);

			  // Eingabeparameter
			  JCO.ParameterList input = function.getImportParameterList();

			  // Funktionsparameter setzen
			  if (CustNumber != ""){
				  input.setValue(CustNumber, "CUSTOMER_NUMBER");
			  }
			  if (SalesOrg != ""){
				  input.setValue(SalesOrg, "SALES_ORGANIZATION");
			  }
			  if (DocDate != ""){
				  input.setValue(DocDate, "DOCUMENT_DATE");
			  }
			  if (DocDateTo != ""){
				  input.setValue(DocDateTo, "DOCUMENT_DATE_TO");
			  }
			  if (TAGroup != ""){
				  input.setValue(TAGroup, "TRANSACTION_GROUP" );
			  }

			  // Führe die entfernte Funktion aus
			  System.out.println("> Führe BAPI_SALES_ORDER_GETLIST aus");
			  client.execute(function);

			  // Print return message
//			  JCO.Structure ret = function.getExportParameterList().getStructure("RETURN");
//			  System.out.println("\t RETURN MESSAGE: " + ret.getString("MESSAGE"));

			  // Get table containing the orders
			  result = function.getTableParameterList().getTable("SALES_ORDERS");

		  }
		  else {
			  System.err.println("> Funktion BAPI_SALESORDER_GETLIST wurde nicht gefunden");
		  }
	  }
	  catch (Exception ex) {
		  System.err.println("> Problem beim Ausführen von BAPI_SALESORDER_GETLIST: \n" + ex);
	  }
    
	  finally {
		  // Verbindung wieder freigeben
		  JCO.releaseClient(client);
		  
		  return result;
	  }
  }

  /**
   * Pool muss explizit geloescht werden
   */
  public static void cleanUp() {
    JCO.removeClientPool(PoolName);
  }
  
  /**
   * SAP Verbindungsdaten werden aus einer Property-Datei geladen
   */
  public static void LoadFromProperties(Properties pr){
	  
	  PoolName = pr.getProperty("PoolName");
	  MaxConnections = pr.getProperty("MaxConnections");
	  SAPClient = pr.getProperty("SAPClient");
	  UserId = pr.getProperty("UserId");
	  Pass = pr.getProperty("Pass");
	  Language = pr.getProperty("Language");
	  HostName = pr.getProperty("HostName");
	  SystemNumber = pr.getProperty("SystemNumber");
	  
  }
 
}
