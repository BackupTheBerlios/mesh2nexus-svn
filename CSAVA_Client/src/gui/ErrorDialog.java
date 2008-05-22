package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog zur Fehleranzeige
 */
public class ErrorDialog {
	
	// öffnet einen MessageBox zur Fehleranzeige
	protected static void show(Shell parent, String title, String text) {
		MessageBox mb = new MessageBox(parent, SWT.OK | SWT.ICON_ERROR);
		
		mb.setText(title);
		mb.setMessage(text);
		mb.open();
	}

}
