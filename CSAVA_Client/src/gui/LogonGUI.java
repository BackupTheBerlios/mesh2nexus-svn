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
	
	// Server URL
	public Text text;	

	public LogonGUI() {
				
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(320, 240);
		
		// Wellcome to CSAVA!!!
		Label label = new Label(shell, SWT.NONE);
		label.setText("Wellcome to CSAVA!!!");
		label.setLocation(90, 5);	
		
		// Server URL
		text = new Text(shell, SWT.BORDER);
		text.setText("localhost:21");
		text.setBounds(10,10,200,20);
		text.setTextLimit(30);
		text.setLocation(50, 50);		
		
		// Password
		Text text2 = new Text(shell, SWT.NONE);
		text2.setEchoChar('*');
		text2.setBounds(10,50,200,20);
		text2.setText("Password");
		text2.setLocation(50, 100);
		
		// Verbinden
		Button button1 = new Button(shell,SWT.PUSH);
		button1.setText("Verbinden..");
		button1.setLocation(100,150);
		button1.setSize(100,20);
		
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
				
				System.out.println("Verbinden..");	
				// // Serverdaten auslesen	
				//Controller.setServerURL(text1.getText());
				// Verbindung zum Server aufbauen
				String response = Controller.connectToServer(text.getText());
				text.setText(response);
				
				
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
