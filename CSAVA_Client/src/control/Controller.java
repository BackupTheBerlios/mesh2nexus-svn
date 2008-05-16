/**
 * 
 */
package control;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.eclipse.swt.widgets.Shell;

import com.sap.mw.jco.JCO;

import communication.ServerInterface;
import gui.ConnectWindow;
import gui.MainWindow;
import gui.QueryWindow;


/**
 * @author
 * 
 */
public class Controller {

	// Public static String ServerURL;
	public static ServerInterface server;


	// Start MainWindow
	public static void Start() {		
		gui.MainWindow.run();		
	}
	

	// Show ConnectWindow
	public static void showConnectWindow(MainWindow mainWindow) {
		new ConnectWindow(mainWindow);
	}
	
	
	// Show QueryWindow
	public static void showQueryWindow(MainWindow mainWindow) {		
		new QueryWindow(mainWindow);
	}	
	
	
	public static boolean ConnectToServer(String ServerURL) {

		// Connect to Server
		try {
			server = (ServerInterface) Naming.lookup("rmi://" + ServerURL
					+ "/ServerFunctions");
			
			String response = server.getString();
			System.out.println(response);	

			return true;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		return false;
	}	
	
	
	public static JCO.Table getSapTable(String CustNumber,
			String SalesOrg,
			String DocDate,
			String DocDateTo,
			String TAGroup) {

		JCO.Table sales_orders = null;
		try {
			// RPC!
			sales_orders = server.getSalesOrderList(CustNumber,
					SalesOrg,
					DocDate,
					DocDateTo,
					TAGroup);

			if (sales_orders == null) {
				System.err
						.println("Funktion konnte nicht ausgeführt werden, siehe Server Log");
			} else {
				System.out.println(sales_orders.getNumRows());
				// return sales_orders;
			}

		} catch (RemoteException e) {
			// 
			e.printStackTrace();
		}

		return sales_orders;
	}

}
