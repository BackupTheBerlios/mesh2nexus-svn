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

import control.Controller;

public class LogonGUI {
	
	// Display
	public static Display display;
	
	// Server URL
	public Text text;	

	public LogonGUI() {
		
		// Display
		display = new Display();
		
		Shell shell = new Shell(display);
		shell.setSize(320, 240);		
		shell.setText("CSAVA V0.1");
		
		// Wellcome to CSAVA!!!
		Label label = new Label(shell, SWT.NONE);
		label.setText("Hier Server URL eingeben:");
		label.setLocation(90, 10);	
		
		// Server URL
		text = new Text(shell, SWT.BORDER);
		text.setText("localhost:4711");
		text.setBounds(10,10,200,20);
		text.setTextLimit(30);
		text.setLocation(50, 50);	
		
		// On Enter
		text.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR){
					//System.out.println("1");
				}
			}			
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					//System.out.println("2");
					String response = Controller.ConnectToServer(text.getText());
					text.setText(response);
				}
					
			}
		});
		
		// Password
//		Text text2 = new Text(shell, SWT.NONE);
//		text2.setEchoChar('*');
//		text2.setBounds(10,50,200,20);
//		text2.setText("Password");
//		text2.setLocation(50, 100);
		
		// Verbinden
		Button button1 = new Button(shell,SWT.PUSH);
		button1.setText("Verbinden");
		button1.setLocation(100,150);
		button1.setSize(100,20);
		//button1.setSelection(true);
		//button1.setEnabled(true);
		
		
		// Status
//		ProgressBar progressBar1 = new ProgressBar(shell ,SWT.HORIZONTAL);
//		progressBar1.setMinimum(0);
//		progressBar1.setMaximum(100);
//		progressBar1.setSelection(30);
//		progressBar1.setBounds(10,10,200,20);
//		progressBar1.setLocation(50,180);
		
		// By Click
		button1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				System.out.println("Verbindung wird hergestellt..");	
				// // Serverdaten auslesen	
				//Controller.setServerURL(text1.getText());
				// Verbindung zum Server aufbauen
				String response = Controller.ConnectToServer(text.getText());
				text.setText(response);
				
				
				// if ok - mainGUI, else ErrorDialog
				if (response.equals("OK")) {
					// Starte MainGUI
					Controller.StartMainGui();
				}					
				else {
					Controller.ShowErrorDialog();
					//Controller.StartMainGui();
				}
					
			}

		});
		

		//shell.pack();
		label.pack();
		shell.open();
		
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		
		display.dispose();
		label.dispose();
		//text.dispose(); // ???
		

	}

}
