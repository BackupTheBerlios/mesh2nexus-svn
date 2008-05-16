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
import org.eclipse.swt.widgets.Text;

import com.sap.mw.jco.JCO;

import control.Controller;

/**
 * @author 
 *
 */
public class QueryWindow {
	
	private Text CustNumber;
	private Text SalesOrg;
	private Text DocDate;
	private Text DocDateTo;
	private Text TAGroup;
	
	private Label CustNumberLabel;
	private Label SalesOrgLabel;
	private Label DocDateLabel;
	private Label DocDateToLabel;
	private Label TAGroupLabel;
	
	private Button query;
	
	private MainWindow parent;
	
	/**
	 * 
	 */
	public QueryWindow(MainWindow mainWindow) {		
		
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
		shell.setText("Query");
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
		CustNumber = new Text(shell, SWT.BORDER);
		CustNumber.setText("0000100001");	
	
		SalesOrgLabel = new Label(shell, SWT.NONE);
		SalesOrgLabel.setText("SalesOrg:");		
		SalesOrg = new Text(shell, SWT.BORDER);
		SalesOrg.setText("WING");	
		
		DocDateLabel = new Label(shell, SWT.NONE);
		DocDateLabel.setText("DocDate:");		
		DocDate = new Text(shell, SWT.BORDER);
		DocDate.setText("2005-05-28");	
		
		DocDateToLabel = new Label(shell, SWT.NONE);
		DocDateToLabel.setText("DocDateTo:");		
		DocDateTo = new Text(shell, SWT.BORDER);
		DocDateTo.setText("2005-05-30");	
		
		TAGroupLabel = new Label(shell, SWT.NONE);
		TAGroupLabel.setText("TAGroup:");		
		TAGroup = new Text(shell, SWT.BORDER);
		TAGroup.setText("0");	
		
		
		query = new Button(shell, SWT.PUSH);
		query.setText("Query");
		
		query.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				System.out.println("Query..");
				// TODO:
				loadTable(evt);
			}
		});
		
	}

	protected void loadTable(SelectionEvent evt) {
		System.out.println("Anfrage wird verschickt..");

		// Get JCO.Table and redraw
		JCO.Table result = Controller.getSapTable(CustNumber.getText(),
				SalesOrg.getText(),
				DocDate.getText(),
				DocDateTo.getText(),
				TAGroup.getText());
		
		// FillTable
		this.parent.fillTable(result);
		
	}		
	
}
