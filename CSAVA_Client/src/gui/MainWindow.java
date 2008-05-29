package gui;

import org.eclipse.swt.*;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import client.Client;
import com.sap.mw.jco.JCO;

/**
 * Hauptfenster des Programms
 */
public class MainWindow {

	private Menu menu;
	private MenuItem exit;
	public MenuItem export;
	private MenuItem search;
	private Menu fileMenu;
	private MenuItem fileMenuItem;
	private MenuItem sapMenuItem;
	public MenuItem querySapMenuItem;
	private Menu sapMenu;
	public MenuItem evalSapMenuItem;
	public MenuItem conSapMenuItem;
	private Table table;

	public Shell shell;
	
	/**
	 * Konstruktor für MainWindow
	 */
	public MainWindow() {

		Display display = Display.getDefault();
		shell = new Shell(display);

		// MainWindow Initialisierung
		init();

		shell.layout();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * Initialisiert MainWindow und dessen Elemente
	 */
	private void init() {

		shell.setText("CSAVA  [Offline]");
		shell.setSize(640, 480);

		// GridLayout setzen
		GridLayout thisLayout = new GridLayout();
		thisLayout.numColumns = 1;
		shell.setLayout(thisLayout);

		// Tabelle
		table = new Table(shell, SWT.BORDER | SWT.MULTI | SWT.SCROLL_LINE);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		
		// Menu
		menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		fileMenuItem = new MenuItem(menu, SWT.CASCADE);
		fileMenuItem.setText("Datei");
		fileMenu = new Menu(fileMenuItem);

//		search = new MenuItem(fileMenu, SWT.PUSH);
//		search.setText("Suchen");
//		search.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent evt) {
//
//				// Dialog zum Öffnen einer Datei
//				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
//				String filename = dialog.open();
//				
//				// falls Datei nicht geöffnet werden konnte
//				if (!openTable(filename)) {
//					
//					// Fehlermeldung
//					ErrorDialog.show(shell, "Error",
//							"Datei konnte nicht geöffnet werden");
//
//				}
//			}
//		});

		export = new MenuItem(fileMenu, SWT.PUSH);
		export.setText("HTML-Export");
		export.setEnabled(false);
		export.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				// Dialog zum Abspeichern der Tabelle in einer Datei
				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[]{"*.html"});
				String filename = dialog.open();
				
				// falls Tabelle nicht gespeichert werden konnte
				if (!saveTable(filename)) {
					
					// Fehlermeldung
					ErrorDialog.show(shell, "Error",
							"Tabelle konnte nicht exportiert werden");
				}
			}
		});

		exit = new MenuItem(fileMenu, SWT.CASCADE);
		exit.setText("Beenden");
		exit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				
				// Beenden des Programms
				exit();
			}
		});

		fileMenuItem.setMenu(fileMenu);
		sapMenuItem = new MenuItem(menu, SWT.CASCADE);
		sapMenuItem.setText("SAP");
		sapMenu = new Menu(sapMenuItem);

		// Verbinden
		conSapMenuItem = new MenuItem(sapMenu, SWT.PUSH);
		conSapMenuItem.setText("Verbinden");

		conSapMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				// zeige ConnectWindow an
				showConnectWindow();
			}
		});

		// Anfragen
		querySapMenuItem = new MenuItem(sapMenu, SWT.PUSH);
		querySapMenuItem.setText("Anfragen");
		querySapMenuItem.setEnabled(false);
		querySapMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				// zeige QueryWindow an
				showQueryWindow();
			}
		});

		// Auswerten
		evalSapMenuItem = new MenuItem(sapMenu, SWT.PUSH);
		evalSapMenuItem.setText("Auswerten");
		evalSapMenuItem.setEnabled(false);
		evalSapMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				// zeige EvalWindow an
				showEvalWindow();
			}
		});
		sapMenuItem.setMenu(sapMenu);
	}

	/**
	 * schließt das Hauptfenster
	 */
	private void exit() {
		shell.dispose();
	}

	/**
	 * Speichert die Tabelle in eine HTML-Datei
	 */
	private boolean saveTable(String path) {
		
		try{
			Client.sales_orders.writeHTML(path);
			return true;
			
		}catch(Exception e){
			
			return false;
		}
	

	}

	/**
	 * Erstellt ein neues EvalWindow mit MainWindow als parent
	 */
	private void showEvalWindow() {
		new EvalWindow(this);
	}

	/**
	 * Erstellt ein neues QueryWindow mit MainWindow als parent
	 */
	private void showQueryWindow() {
		new QueryWindow(this);
	}

	/**
	 * Erstellt ein neues ConnectWindow mit MainWindow als parent
	 */
	private void showConnectWindow() {
		new ConnectWindow(this);
	}


	/**
	 * Setzt Status-Meldungen in der Titel-Leiste des Hauptfensters
	 */
	protected void setStatus(String message) {
		shell.setText("CSAVA " + "[" + message + "]");
	}

	/**
	 * Füllt die Tabelle mit Werten aus Client.sales_orders (JCO.Table)
	 */
	public void fillTable() {

		JCO.Table sales_orders = Client.sales_orders;

		// Lösche Tabelle, die evtl. zuvor geladen wurde
		if (table.getColumnCount() != 0) {
			table.removeAll();
		} else {

			// Erstelle Spalten
			for (JCO.FieldIterator e = sales_orders.fields(); e
					.hasMoreElements();) {
				JCO.Field field = e.nextField();
				
				TableColumn id = new TableColumn(table, SWT.LEFT);
				id.setText(field.getName());
				id.setWidth(100);
			}

		}
		
		// Fülle Tabelle mit Werten
		int i;
		do {

			i = 0;
			TableItem item1 = new TableItem(table, SWT.NONE);
			
			for (JCO.FieldIterator e = sales_orders.fields(); e
					.hasMoreElements();) {
				
				JCO.Field field = e.nextField();
				item1.setText(i, field.getString());
				i++;
				
			}
		} while (sales_orders.nextRow());

	}

}
