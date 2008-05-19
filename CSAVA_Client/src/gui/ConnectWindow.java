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
 * Fenster für Aufbau einer Verbindung zum Server
 */
public class ConnectWindow {
	
	private Label ipLabel;
	private Label portLabel;
	private Text ip;
	private Text port;
	private Button connect;	
	private MainWindow parent;	
	private Shell self;
	
	/*
	 * Konstruktor für ConnectWindow
	 */
	public ConnectWindow(MainWindow mainWindow) {	
		
		parent = mainWindow;
		Shell shell = new Shell(parent.getShell(), SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM );
		this.self = shell;
		
		// ConnectWindow Initialisierung
		init(shell);
		
		shell.layout();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!mainWindow.getDisplay().readAndDispatch())
				mainWindow.getDisplay().sleep();
		}

	}

	// Initialisiert ConnectWindow und dessen Elemente
	private void init(Shell shell) {
		
		// Größe setzen 
		shell.setSize(180, 170);		
		
        // Position von ConnectWindow an Position von MainWindow gebunden (Mitte)
		// Ermittlung und Setzen der Position
        Rectangle shellBounds = parent.getParent().getBounds();
        Point dialogSize = shell.getSize();

        shell.setLocation(
          shellBounds.x + (shellBounds.width / 2) - (dialogSize.x / 2),
          shellBounds.y + (shellBounds.height / 2) - (dialogSize.y / 2));

        // GridLayout setzen
		GridLayout thisLayout = new GridLayout();
	    thisLayout.numColumns = 1;
		shell.setLayout(thisLayout);

		shell.setText("Serververbindung");
		

		// ConnectWindow-Elemente initialisieren
		
		ipLabel = new Label(shell, SWT.NONE);
		ipLabel.setText("Server IP:");
		ipLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		
		ip = new Text(shell, SWT.BORDER);
		ip.setText("localhost");
		ip.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		portLabel = new Label(shell, SWT.NONE);
		portLabel.setText("Server Port:");
		portLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
	
		port = new Text(shell, SWT.BORDER);
		port.setText("4711");
		port.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		connect = new Button(shell, SWT.PUSH);
		connect.setText("Verbinden");
		connect.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		
		// Aktion beim Klicken auf connect-Button
		connect.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				System.out.println("Connecting..");
				// TODO:
				// Connect to server
				ConnectToServer(evt);
				

			}
		});

	}

	protected void ConnectToServer(SelectionEvent evt) {

		// read server data
		String data = ip.getText() + ":" + port.getText();
		
		// TODO: String uberprufen.. WarningsDialog..
		
		// Connect to server
		boolean connected = Client.ConnectToServer(data);

		if (connected) {
			System.out.println("Verbindung hergestellt!");
			
			this.parent.setStatus("Connected to Server and SAP");
			//this.self.close();
			this.self.dispose();
			
		} else {
			System.out.println("ERROR!");
			
			ShowErrorDialog();

		}
	}

	private void ShowErrorDialog() {

		MessageBox messageBox = new MessageBox(this.parent.getShell(),
				SWT.OK | SWT.ICON_ERROR);

		messageBox.setText("Error");
		messageBox.setMessage("Es ist ein Fehler aufgetreten");
		messageBox.open();

	}

}
