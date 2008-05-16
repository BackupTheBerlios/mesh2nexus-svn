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
import org.eclipse.swt.layout.FillLayout;

import control.Controller;

//public class ConnectWindow {
public class ConnectWindow {
	
	private Label ipLabel;
	private Label portLabel;
	private Text ip;
	private Text port;
	private Button connect;	
	private MainWindow parent;
		
	public ConnectWindow(MainWindow mainWindow) {	
		
		parent = mainWindow;
		Shell shell = new Shell(parent.getShell(), SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);	

		// 
		initGUI(shell);
		//
		setPreferences(shell);

		// TODO
		shell.setSize(320, 240);
		shell.setLocation(400, 300);
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		shell.setImage(SWTResourceManager.getImage("images/16x16.png"));
		shell.setText("Connect to Server");
		shell.setBackgroundImage(SWTResourceManager
				.getImage("images/ToolbarBackground.gif"));
		shell.layout();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!mainWindow.getDisplay().readAndDispatch())
				mainWindow.getDisplay().sleep();
		}
		//s.getDisplay().dispose();
	}

	private void setPreferences(Shell shell) {
		// TODO set location and size

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

		// Connect to server
		boolean connected = Controller.ConnectToServer(data);

		if (connected) {
			System.out.println("Verbindung hergestellt!");
			
			this.parent.setStatus("Connected to Server and SAP");

			
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
