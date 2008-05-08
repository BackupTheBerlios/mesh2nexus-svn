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

public class LogonGUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(320, 240);
		
		// Wellcome to CSAVA!!!
		Label label = new Label(shell, SWT.NONE);
		label.setText("Wellcome to CSAVA!!!");
		label.setLocation(90, 5);	
		
		// Server URL
		Text text1 = new Text(shell, SWT.BORDER);
		text1.setText("Server URL");
		text1.setBounds(10,10,200,20);
		text1.setTextLimit(30);
		text1.setLocation(50, 50);		
		
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
		

	}

}
