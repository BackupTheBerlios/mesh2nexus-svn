/**
 * 
 */
package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


/**
 * @author 
 *
 */
public class EvalWindow {
	
	private Label CustNumberLabel;
	private Label SalesOrgLabel;
	private Label DocDateLabel;
	private Label DocDateToLabel;
	private Label TAGroupLabel;
	
	private Button eval;
	
	private MainWindow parent;
	/**
	 * 
	 */
	public EvalWindow(MainWindow mainWindow) {
		
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
		shell.setText("Evaliate");
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

		CustNumberLabel = new Label(shell, SWT.NONE);
		CustNumberLabel.setText("CustNumber:");		
	
		SalesOrgLabel = new Label(shell, SWT.NONE);
		SalesOrgLabel.setText("SalesOrg:");		
		
		DocDateLabel = new Label(shell, SWT.NONE);
		DocDateLabel.setText("DocDate:");		
		
		DocDateToLabel = new Label(shell, SWT.NONE);
		DocDateToLabel.setText("DocDateTo:");		
		
		TAGroupLabel = new Label(shell, SWT.NONE);
		TAGroupLabel.setText("TAGroup:");				
		
		eval = new Button(shell, SWT.PUSH);
		eval.setText("Ok");
		
		eval.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				System.out.println("Info..");
				// TODO:				
			}
		});
		
	}

}
