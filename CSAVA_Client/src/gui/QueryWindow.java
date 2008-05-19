/**
 * 
 */
package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import client.Client;

import com.sap.mw.jco.JCO;


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
	private Shell shell;
	
	/**
	 * 
	 */
	public QueryWindow(MainWindow par) {		
		
		parent = par;
		
		shell = new Shell(parent.shell, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);

		// Initialisierung von Anfragefenster
		init(shell);

		shell.layout();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!parent.shell.getDisplay().readAndDispatch())
				parent.shell.getDisplay().sleep();
		}

	}	

	/*
	 * 
	 */
	private void init(Shell shell) {
		
		shell.setSize(220, 185);
		
		// Position von ConnectWindow an Position von MainWindow gebunden (Mitte)
		// Ermittlung und Setzen der Position
        Rectangle shellBounds = parent.shell.getBounds();
        Point dialogSize = shell.getSize();

        shell.setLocation(
          shellBounds.x + (shellBounds.width / 2) - (dialogSize.x / 2),
          shellBounds.y + (shellBounds.height / 2) - (dialogSize.y / 2));
        
		// GridLayout setzen
		GridLayout thisLayout = new GridLayout();
	    thisLayout.numColumns = 2;
		shell.setLayout(thisLayout);
	
		shell.setText("Anfrage");
		
		CustNumberLabel = new Label(shell, SWT.NONE);
		CustNumberLabel.setText("Kundennummer:");	
		CustNumberLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		CustNumber = new Text(shell, SWT.BORDER);
		CustNumber.setText("0000100001");
		CustNumber.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		SalesOrgLabel = new Label(shell, SWT.NONE);
		SalesOrgLabel.setText("Verkaufsorganisation:");
		SalesOrgLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		SalesOrg = new Text(shell, SWT.BORDER);
		SalesOrg.setText("WING");
		SalesOrg.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		DocDateLabel = new Label(shell, SWT.NONE);
		DocDateLabel.setText("Datum:");
		DocDateLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		DocDate = new Text(shell, SWT.BORDER);
		DocDate.setText("2005-05-28");
		DocDate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		
		DocDateToLabel = new Label(shell, SWT.NONE);
		DocDateToLabel.setText("Datum bis:");
		DocDateToLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		DocDateTo = new Text(shell, SWT.BORDER);
		DocDateTo.setText("2005-05-30");
		DocDateTo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		TAGroupLabel = new Label(shell, SWT.NONE);
		TAGroupLabel.setText("Transaktion (0-7):");
		TAGroupLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		TAGroup = new Text(shell, SWT.BORDER);
		TAGroup.setText("0");	
		TAGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label empty = new Label(shell, SWT.NONE);
		
		query = new Button(shell, SWT.PUSH);
		query.setText("Anfragen");
		query.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		query.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				loadTable(evt);
			}
		});		
	}

	protected void loadTable(SelectionEvent evt) {

		// Get JCO.Table and redraw
		String message = Client.getSapTable(CustNumber.getText(),
				SalesOrg.getText(),
				DocDate.getText(),
				DocDateTo.getText(),
				TAGroup.getText());
		if (message.equals("OK")) {

			// FillTable
			parent.fillTable();
			parent.evalSapMenuItem.setEnabled(true);
			parent.save.setEnabled(true);
		}
		else {
			ErrorDialog.show(shell, "Error", message) ;			
		}
		shell.dispose();	
	}		
	
}
