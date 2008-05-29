package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import client.Client;


/**
 * Fenster zur Abfrage der Parameter und zum 
 * Ausführen einer Anfrage
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
	 * Konstruktor für QueryWindow
	 */
	public QueryWindow(MainWindow par) {		
		
		parent = par;
		shell = new Shell(parent.shell, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);

		// QueryWindow Initialisierung
		init(shell);

		shell.layout();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!parent.shell.getDisplay().readAndDispatch())
				parent.shell.getDisplay().sleep();
		}

	}	

	/**
	 * Initialisiert QueryWindow und dessen Elemente
	 */
	private void init(Shell shell) {
		
		// GridLayout setzen
		GridLayout thisLayout = new GridLayout();
	    thisLayout.numColumns = 2;
		shell.setLayout(thisLayout);
	
		shell.setText("Anfrage");
		
		// Elemente intialisieren
		CustNumberLabel = new Label(shell, SWT.NONE);
		CustNumberLabel.setText("Kundennummer:");	
		CustNumberLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		CustNumberLabel.pack();
		
		CustNumber = new Text(shell, SWT.BORDER);
		CustNumber.setText("0000100001");
		CustNumber.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		CustNumber.pack();
	
		SalesOrgLabel = new Label(shell, SWT.NONE);
		SalesOrgLabel.setText("Verkaufsorganisation:");
		SalesOrgLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		SalesOrgLabel.pack();
		
		SalesOrg = new Text(shell, SWT.BORDER);
		SalesOrg.setText("WING");
		SalesOrg.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		SalesOrg.pack();
		
		DocDateLabel = new Label(shell, SWT.NONE);
		DocDateLabel.setText("Datum:");
		DocDateLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		DocDateLabel.pack();
		
		DocDate = new Text(shell, SWT.BORDER);
		DocDate.setText("2005-05-28");
		DocDate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		DocDate.pack();
		
		DocDateToLabel = new Label(shell, SWT.NONE);
		DocDateToLabel.setText("Datum bis:");
		DocDateToLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		DocDateToLabel.pack();
		
		DocDateTo = new Text(shell, SWT.BORDER);
		DocDateTo.setText("2005-05-30");
		DocDateTo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		DocDateTo.pack();
		
		TAGroupLabel = new Label(shell, SWT.NONE);
		TAGroupLabel.setText("Transaktion (0-7):");
		TAGroupLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		TAGroupLabel.pack();
		
		TAGroup = new Text(shell, SWT.BORDER);
		TAGroup.setText("0");	
		TAGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		TAGroup.pack();
		
		Label empty = new Label(shell, SWT.NONE);
		empty.pack();
		
		query = new Button(shell, SWT.PUSH);
		query.setText("Anfragen");
		query.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		query.pack();
		
		 // Aktion beim Anklicken von query-Button
		query.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				// Führe die Anfrage aus
				executeQuery();
			}
		});	
		
		shell.pack();
		
		// Position von ConnectWindow an Position von MainWindow gebunden (Mitte)
		// Ermittlung und Setzen der Position
        Rectangle shellBounds = parent.shell.getBounds();
        Point dialogSize = shell.getSize();

        shell.setLocation(
        		shellBounds.x + (shellBounds.width / 2) - (dialogSize.x / 2),
        		shellBounds.y + (shellBounds.height / 2) - (dialogSize.y / 2));
		
	}

	/**
	 * Benutzereingaben für die Anfrage werden eingelesen
	 * und an Client.getSapTable übergeben. 
	 */
	private void executeQuery() {

		// Führe die Anfrage aus
		String message = Client.getSapTable(CustNumber.getText(),
				SalesOrg.getText(),
				DocDate.getText(),
				DocDateTo.getText(),
				TAGroup.getText());
		
		// falls die Ausführung erfolgreich war
		if (message.equals("OK")) {

			// Fülle die Tabelle mit Werten aus der Ergebnis-JCO.Table
			parent.fillTable();
			
			// Ein- Ausschalten entsprechender Menueinträge
			parent.evalSapMenuItem.setEnabled(true);
			parent.export.setEnabled(true);
			
		// falls die Ausführung nicht erfolgreich war oder 
		// die Ergebnis-Tablle leer ist
		}else {
			
			// zeige einen Fehler-MessageBox mit entsprechender Meldung
			ErrorDialog.show(shell, "Error", message) ;
			
			// falls verbindung zum SAP-Server unterbrochen wurde
			if (!message.equals("Keine Ergebnisse zu dieser Anfrage")){
				
				// Setze status
				parent.setStatus("Offline");
				// Ein- Ausschalten entsprechender Menueinträge
				parent.querySapMenuItem.setEnabled(false);
				parent.conSapMenuItem.setEnabled(true);
				
			}
			
		}
		
		// schließe QueryWindow
		shell.dispose();	
	}			
}
