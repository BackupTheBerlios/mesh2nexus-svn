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
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(320, 240);
		
		// ERROR to CSAVA!!!
		Label label = new Label(shell, SWT.NONE);
		label.setText("ERROR!!!");
		label.setLocation(90, 5);	
			
		
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
