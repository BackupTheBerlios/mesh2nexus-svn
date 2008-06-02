package gui;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import client.Client;
import client.ClientException;

/**
 * Fenster zur Aufbau einer Verbindung zum Server
 */
public class ConnectWindow {
	
	
	private Label ipLabel;
	private Label portLabel;
	private Text ip;
	private Text port;
	private Button connect;	
	private MainWindow parent;	
	private Shell shell;
		
	/**
	 * Konstruktor für ConnectWindow
	 */
	public ConnectWindow(MainWindow par) {	
		
		parent = par;
		shell = new Shell(parent.shell, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM );
		
		// ConnectWindow Initialisierung
		init(shell);
		
		shell.layout();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!parent.shell.getDisplay().readAndDispatch())
				parent.shell.getDisplay().sleep();
		}

	}

	/**
	 * Initialisiert ConnectWindow und dessen Elemente
	 */
	private void init(Shell shell) {
		
        // GridLayout setzen
		GridLayout thisLayout = new GridLayout();
	    thisLayout.numColumns = 2;
		shell.setLayout(thisLayout);

		shell.setText("Serververbindung");		

		// ConnectWindow-Elemente initialisieren		
		ipLabel = new Label(shell, SWT.NONE);
		ipLabel.setText("Server IP:");
		ipLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		ipLabel.pack();
		
		ip = new Text(shell, SWT.BORDER);
		ip.setText("255.255.255.255");
		ip.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ip.pack();
		
		portLabel = new Label(shell, SWT.NONE);
		portLabel.setText("Server Port:");
		portLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		portLabel.pack();
	
		port = new Text(shell, SWT.BORDER);
		port.setText("4711");
		port.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		port.pack();
	
		Label empty = new Label(shell, SWT.NONE);
		empty.pack();

		connect = new Button(shell, SWT.PUSH);
		connect.setText("Verbinden");
		connect.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		connect.pack();
		
		// Aktion beim Klicken auf connect-Button
		connect.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				
				// Verbindung zum Server wird aufgebaut
				ConnectToServer();
			}
		});
		
		shell.pack();
		
		// Position von ConnectWindow wird an Position von MainWindow gebunden (Mitte)
		// Ermittlung und Setzen der Position
        Rectangle shellBounds = parent.shell.getBounds();
        Point dialogSize = shell.getSize();

        shell.setLocation(
        		shellBounds.x + (shellBounds.width / 2) - (dialogSize.x / 2),
        		shellBounds.y + (shellBounds.height / 2) - (dialogSize.y / 2));
        
        ip.setText("localhost");

	}

	/**
	 * Benutzereingaben für Server IP/Port werden ausgelesen
	 * und an Client.ConnectToServer übergeben. 
	 */
	private void ConnectToServer() {

		// Benutzereingaben auslesen
		String data = ip.getText() + ":" + port.getText();
		
		
		try{
			// versuche eine Verbindung zum Server herzustellen
			Client.ConnectToServer(data);

			// falls keine ClientException geworfen wurde

			// Setze Status
			parent.setStatus("Verbunden mit SAP-Server");
			
			// Ein- Ausschalten entsprechender Menueinträge
			parent.conSapMenuItem.setEnabled(false);
			parent.querySapMenuItem.setEnabled(true);
			
			// schließe ConnectWindow
			shell.dispose();
		
		}
		// falls keine Verbindung hergestellt wurde
		catch (ClientException e){
			
			//zeige einen Fehler-MessageBox mit entsprechender Meldung
			ErrorDialog.show(shell, "Error", e.getMessage()) ;
		}
			
		
		
	}
}
