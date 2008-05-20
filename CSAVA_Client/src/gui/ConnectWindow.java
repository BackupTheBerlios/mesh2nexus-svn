package gui;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import client.Client;

/*
 * Fenster f�r Aufbau einer Verbindung zum Server
 */
public class ConnectWindow {
	
	private Label ipLabel;
	private Label portLabel;
	private Text ip;
	private Text port;
	private Button connect;	
	private MainWindow parent;	
	private Shell shell;
		
	/*
	 * Konstruktor f�r ConnectWindow
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

	// Initialisiert ConnectWindow und dessen Elemente
	private void init(Shell shell) {
		
		// Gr��e setzen 
		shell.setSize(180, 115);		
		
        // Position von ConnectWindow an Position von MainWindow gebunden (Mitte)
		// Ermittlung und Setzen der Position
        Rectangle shellBounds = parent.shell.getBounds();
        Point dialogSize = shell.getSize();

        shell.setLocation(
          shellBounds.x + (shellBounds.width / 2) - (dialogSize.x / 2),
          shellBounds.y + (shellBounds.height / 2) - (dialogSize.y / 2));

        // GridLayout setzen
		GridLayout thisLayout = new GridLayout();
	    thisLayout.numColumns = 2;
		shell.setLayout(thisLayout);

		shell.setText("Serververbindung");		

		// ConnectWindow-Elemente initialisieren		
		ipLabel = new Label(shell, SWT.NONE);
		ipLabel.setText("Server IP:");
		ipLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		ip = new Text(shell, SWT.BORDER);
		ip.setText("localhost");
		ip.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		portLabel = new Label(shell, SWT.NONE);
		portLabel.setText("Server Port:");
		portLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
	
		port = new Text(shell, SWT.BORDER);
		port.setText("4711");
		port.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		@SuppressWarnings("unused")
		Label empty = new Label(shell, SWT.NONE);

		connect = new Button(shell, SWT.PUSH);
		connect.setText("Verbinden");
		connect.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		// Aktion beim Klicken auf connect-Button
		connect.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				// TODO:
				// Connect to server
				ConnectToServer(evt);
			}
		});

	}

	protected void ConnectToServer(SelectionEvent evt) {

		// Server Daten auslesen
		String data = ip.getText() + ":" + port.getText();
		
		// Connect to server
		boolean connected = Client.ConnectToServer(data);

		if (connected) {
			// TODO
			// SetStatus
			parent.setStatus("Verbunden mit SAP-Server");
			
			parent.conSapMenuItem.setEnabled(false);
			parent.querySapMenuItem.setEnabled(true);
			
			// Close ConnectWindow
			shell.dispose();
			
		} else {
			ErrorDialog.show(shell, "Error", "SAP-Server ist nicht verf�gbar") ;
		}
	}
}
