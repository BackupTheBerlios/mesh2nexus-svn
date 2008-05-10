/**
 * 
 */
package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


/**
 * @author 
 *
 */
public class ErrorDialog {

	/**
	 * 
	 */
	public ErrorDialog() {
		
		Shell errShell = new Shell(LogonGUI.display);

		errShell.setSize(200, 100);
		
		// ERROR to CSAVA!!!
		Label label = new Label(errShell, SWT.NONE);
		label.setText("ERROR!!!");
		label.setLocation(70, 30);	
			
		
		//shell.pack();
		label.pack();
		errShell.open();
		

		

	}
	
}
