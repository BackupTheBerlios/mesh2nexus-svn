/**
 * 
 */
package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.sap.mw.jco.JCO;

import control.Controller;


/**
 * @author 
 *
 */
public class QueryGUI {

	// 
	public Text text;	

	public QueryGUI() {
				
		Shell qShell = new Shell(LogonGUI.display);

		qShell.setSize(320, 240);
		qShell.setText("CSAVA V0.1");
		qShell.setLocation(400, 300);
		
		// Wellcome to CSAVA!!!
		Label label = new Label(qShell, SWT.NONE);
		label.setText("Hier Anfragedaten eingeben:");
		label.setLocation(90, 5);	
		
		// Server URL
		text = new Text(qShell, SWT.BORDER);
		text.setText("Kundennumer");
		text.setBounds(10,10,200,20);
		text.setTextLimit(30);
		text.setLocation(50, 50);		
		
		// Password
		Text text2 = new Text(qShell, SWT.NONE);
		text2.setEchoChar('*');
		text2.setBounds(10,50,200,20);
		text2.setText("Password");
		text2.setLocation(50, 100);
		
		// Verbinden
		Button button1 = new Button(qShell,SWT.PUSH);
		button1.setText("Anfragen");
		button1.setLocation(100,150);
		button1.setSize(100,20);
		

		
		// By Click
		button1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				System.out.println("Anfrage wird verschickt..");
				Controller.CloseQueryGui();
				// Get JCO.Table and redraw
				JCO.Table result = Controller.CreateTable("ura");
				
				// Test..
				//String html = "html";
				//result.writeHTML(html);
				//System.out.println(html);			
			}
		});
				
		//shell.pack();
		label.pack();
		qShell.open();

	}

}
