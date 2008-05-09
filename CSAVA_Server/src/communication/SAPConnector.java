package communication;

import com.sap.mw.jco.*;
import java.util.Properties;


/**
 * SAP Connector, Funktionen zur Aufbau der Verbindung zu SAP
 * Realisierung der Anfragen an SAP
 */
public class SAPConnector {

	
  IRepository repository;
  
  // SAP Verbindungsdaten
  static String PoolName;
  static String MaxConnections;
  static String SAPClient;
  static String UserId;
  static String Pass;
  static String Language;
  static String HostName;
  static String SystemNumber;
  

  /**
   * Konstruktor. Erzeugt einen Connection-Pool
   *
   */
  public SAPConnector()
  {
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
		  System.err.println("Es konnte keine Verbindung zu SAP aufgebaut werden \n " + ex);
	  }
  }

  // Retrieves and prints information about the remote system
  public void systemInfo()
  {
    try {

      // Get a function template from the repository
      IFunctionTemplate ftemplate = repository.getFunctionTemplate("RFC_SYSTEM_INFO");

      // if the function definition was found in backend system
      if(ftemplate != null) {

        // Create a function from the template
        JCO.Function function = ftemplate.getFunction();

        // Get a client from the pool
        JCO.Client client = JCO.getClient(PoolName);

        // We can call 'RFC_SYSTEM_INFO' directly since it does not need any input parameters
        client.execute(function);

        // The export parameter 'RFCSI_EXPORT' contains a structure of type 'RFCSI'
        JCO.Structure s = function.getExportParameterList().getStructure("RFCSI_EXPORT");

        // Use enumeration to loop over all fields of the structure
        System.out.println("System info for " + PoolName + ":\n" +
        				   "--------------------");

        for (JCO.FieldIterator e = s.fields(); e.hasMoreElements(); ) {
          JCO.Field field = e.nextField();
          System.out.println(field.getName() + ":\t" + field.getString());
        }//for

        System.out.println("\n\n");

        // Release the client into the pool
        JCO.releaseClient(client);
      }
      else {
	    System.out.println("Function RFC_SYSTEM_INFO not found in backend system.");
	  }
    }
	catch (Exception ex) {
	  System.out.println("Caught an exception: \n" + ex);
	}

  }

  // Retrieves and displays a sales order list
  public void salesOrders()
  {
	JCO.Client client = null;

    try {
      // Get a function template from the repository
      IFunctionTemplate ftemplate = repository.getFunctionTemplate("BAPI_SALESORDER_GETLIST");

      // if the function definition was found in backend system
      if(ftemplate != null) {

		// Create a function from the template
		JCO.Function function = ftemplate.getFunction();

		// Get a client from the pool
		client = JCO.getClient(PoolName);

		// Fill in input parameters
		JCO.ParameterList input = function.getImportParameterList();

//		input.setValue("0000100001", "CUSTOMER_NUMBER"   );
		input.setValue(      "WING", "SALES_ORGANIZATION");
		input.setValue(         "0", "TRANSACTION_GROUP" );
		input.setValue(         "2005-05-28", "DOCUMENT_DATE" );
//		input.setValue(         "2005-05-28", "DOCUMENT_DATE_TO" );
		
		
		

		// Call the remote system
		client.execute(function);

		// Print return message
		JCO.Structure ret = function.getExportParameterList().getStructure("RETURN");
		System.out.println("BAPI_SALES_ORDER_GETLIST RETURN: " + ret.getString("MESSAGE"));

		// Get table containing the orders
		JCO.Table sales_orders = function.getTableParameterList().getTable("SALES_ORDERS");

		// Print results
		if (sales_orders.getNumRows() > 0) {

		  // Loop over all rows
		  do {

		    System.out.println("-----------------------------------------");

		    // Loop over all columns in the current row
		    for (JCO.FieldIterator e = sales_orders.fields(); e.hasMoreElements(); ) {
			  JCO.Field field = e.nextField();
			  
			  System.out.println(field.getName() + ":\t" + field.getString());
			  
			  
		    }//for
		  } while(sales_orders.nextRow());

		}
		else {
		  System.out.println("No results found");
		}//if
      }
      else {
	    System.out.println("Function BAPI_SALESORDER_GETLIST not found in backend system.");
	  }//if
    }
    catch (Exception ex) {
      System.out.println("Caught an exception: \n" + ex);
    }
    finally {
	  // Release the client to the pool
      JCO.releaseClient(client);
	}
  }

  /**
   * Pool muss explizit geloescht werden
   */
  public void cleanUp() {
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
