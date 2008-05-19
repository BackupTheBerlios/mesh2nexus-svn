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
	private Shell self;
	
	/**
	 * 
	 */
	public EvalWindow(MainWindow mainWindow) {
		
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
		// TODO
		shell.setSize(320, 240);
		shell.setLocation(400, 300);
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		shell.setText("Evaliate");
		

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
				closeInfo();
			}
		});
		
		// Test Auswertung..
		String numColumns = Integer.toString(this.parent.sales_orders.getNumColumns());
		CustNumberLabel.setText("NumColumns:  " + numColumns);	
		String numRows = "" + this.parent.sales_orders.getNumRows();
		SalesOrgLabel.setText("NumRows:  " + numRows);	
	}

	protected void closeInfo() {

		this.self.dispose();
		
	}

}
