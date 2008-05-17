/**
 * 
 */
package gui;

/**
 * @author
 * 
 */

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;

import control.Controller;

//public class ConnectWindow {
public class ConnectWindow {
	
	private Label ipLabel;
	private Label portLabel;
	private Text ip;
	private Text port;
	private Button connect;	
	private MainWindow parent;	
	private Shell self;
		
	public ConnectWindow(MainWindow mainWindow) {	
		
		parent = mainWindow;
		Shell shell = new Shell(parent.getShell(), SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);	
		// for close..
		this.self = shell;
		// 
		initGUI(shell);
		//
		setPreferences(shell);

		shell.layout();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!mainWindow.getDisplay().readAndDispatch())
				mainWindow.getDisplay().sleep();
		}

	}

	private void setPreferences(Shell shell) {

		shell.setSize(320, 240);		
		
        // Move the dialog to the center of the top level shell.
        Rectangle shellBounds = parent.getBounds();
        Point dialogSize = shell.getSize();

        shell.setLocation(
          shellBounds.x + (shellBounds.width - dialogSize.x) / 2,
          shellBounds.y + (shellBounds.height - dialogSize.y) / 2);
        
		//Rectangle mainWindowBounds = parent.getDisplay().getBounds();
		//Rectangle mainWindowBounds = parent.getBounds();		
		//int x = shell.getSize().x;
		//int y = shell.getSize().y;
		//int defaultTop = mainWindowBounds.x + (mainWindowBounds.width - x) / 2;
		//int defaultLeft = mainWindowBounds.y + (mainWindowBounds.height - y) / 2;
		//int defaultTop = (x - mainWindowBounds.height / 2) - (thisBounds.height / 2);
		//int defaultLeft = (y - mainWindowBounds.width / 2) - (thisBounds.width /2);
        //shell.setLocation(defaultLeft, defaultTop);		
		shell.setLocation(400, 300);
		
		
		FillLayout thisLayout = new FillLayout(SWT.VERTICAL);
		shell.setLayout(thisLayout);
		
		shell.setImage(SWTResourceManager.getImage("images/16x16.png"));
		shell.setText("Connect to Server");
		shell.setBackgroundImage(SWTResourceManager
				.getImage("images/ToolbarBackground.gif"));

	}

	private void initGUI(Shell shell) {

		ipLabel = new Label(shell, SWT.NONE);
		ipLabel.setText("Server ip adress:");
		// label.setLocation(90, 10);
		
		ip = new Text(shell, SWT.BORDER);
		ip.setText("localhost");
		// ip.setBounds(10,10,200,20);
		// ip.setTextLimit(30);
		// ip.setLocation(50, 50);
		
		
		portLabel = new Label(shell, SWT.NONE);
		portLabel.setText("Server port:");
		// label.setLocation(90, 10);

		port = new Text(shell, SWT.BORDER);
		port.setText("4711");
		// port.setBounds(10,10,200,20);
		// port.setTextLimit(30);
		// port.setLocation(50, 50);

		
		connect = new Button(shell, SWT.PUSH);
		connect.setText("Connect");
		// connect.setLocation(100,150);
		// connect.setSize(100,20);

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
		boolean connected = Controller.ConnectToServer(data);

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
