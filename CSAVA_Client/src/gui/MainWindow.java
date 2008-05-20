package gui;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import client.Client;

import com.sap.mw.jco.JCO;

/*
 * Hauptfenster des Programms
 */
public class MainWindow {

	private Menu menu;
	private MenuItem exit;
	public MenuItem save;
	private MenuItem open;

	private Label txtStatus;

	private Menu fileMenu;
	private MenuItem fileMenuItem;
	private MenuItem sapMenuItem;
	public MenuItem querySapMenuItem;
	private Menu sapMenu;
	public MenuItem evalSapMenuItem;
	public MenuItem conSapMenuItem;
	private Table table;
	//	
	public Shell shell;

	public MainWindow() {

		Display display = Display.getDefault();
		shell = new Shell(display);

		init();

		shell.layout();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/*
	 * 
	 */
	private void init() {

		shell.setText("CSAVA V1.1");

		shell.setSize(640, 480);
		Rectangle screenBounds = shell.getDisplay().getBounds();
		int top = (screenBounds.height - 480) / 2;
		int left = (screenBounds.width - 640) / 2;
		shell.setLocation(left, top);

		GridLayout thisLayout = new GridLayout();
		thisLayout.numColumns = 1;
		shell.setLayout(thisLayout);

		
		// Tabelle
		table = new Table(shell, SWT.BORDER | SWT.MULTI | SWT.SCROLL_LINE);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Status
		txtStatus = new Label(shell, SWT.BORDER);
		txtStatus.setText("Keine Verbindung zum SAP-Server");
		GridData txtStatusLData = new GridData();
		txtStatusLData.horizontalAlignment = GridData.FILL;
		txtStatusLData.grabExcessHorizontalSpace = true;
		txtStatusLData.verticalIndent = 3;
		txtStatus.setLayoutData(txtStatusLData);
		
		// Menu
		menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		fileMenuItem = new MenuItem(menu, SWT.CASCADE);
		fileMenuItem.setText("Datei");
		fileMenu = new Menu(fileMenuItem);
		
		open = new MenuItem(fileMenu, SWT.PUSH);
		open.setText("Öffnen");
		open.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				// FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
				// String filename = dialog.open();
				// if (filename != null) {
				// try {
				// JCO.Table sales = new JCO.Table("BAPIORDERS");
				// sales.appendRow();
				// sales.readXML(filename);
				// sales.writeXML("test.xml");
				// fillTable(sales);
				// // (new InputStreamReader
				// // (new FileInputStream(filename), "UTF-8"));
				//												
				// } catch (Exception e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				//												
				// getShell().setText(filename);
				// }
				// openTable();
			}
		});

		save = new MenuItem(fileMenu, SWT.PUSH);
		save.setText("Speichern");
		save.setEnabled(false);
		save.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				// TODO: SAVE
				//saveTable();
			}
		});

		exit = new MenuItem(fileMenu, SWT.CASCADE);
		exit.setText("Beenden");
		exit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				// TODO
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

				// Show connect window
				showConnectWindow(evt);
			}
		});

		// Anfragen
		querySapMenuItem = new MenuItem(sapMenu, SWT.PUSH);
		querySapMenuItem.setText("Anfragen");
		querySapMenuItem.setEnabled(false);
		querySapMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				// Show query window
				showQueryWindow(evt);
			}
		});

		// Auswerten
		evalSapMenuItem = new MenuItem(sapMenu, SWT.PUSH);
		evalSapMenuItem.setText("Auswerten");
		evalSapMenuItem.setEnabled(false);
		evalSapMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {

				// Show evaluate window
				showEvalWindow(evt);
			}
		});
		sapMenuItem.setMenu(sapMenu);
	}

	/*
	 * 
	 */
	protected void exit() {
		shell.dispose();		
	}

	/*
	 * 
	 */
	protected void saveTable() {
		// TODO
		// Save
//		Client.sales_orders.writeHTML("JCOTable.html");
//		try {
//			Client.sales_orders.writeXML("JCOTable.xml");
//		} catch (IOException e1) {
//			// TODO
//			e1.printStackTrace();
//		}

	}

	/*
	 * 
	 */
	protected void showEvalWindow(SelectionEvent evt) {
		// TODO
		new EvalWindow(this);
	}

	/*
	 * 
	 */
	protected void showQueryWindow(SelectionEvent evt) {
		// TODO
		new QueryWindow(this);
	}

	/*
	 * 
	 */
	protected void showConnectWindow(SelectionEvent evt) {
		// TODO
		new ConnectWindow(this);
	}


	private void openTable() {
//		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
//		String filename = dialog.open();
//		if (filename != null) {
//
//			// fillTable(this.sales_orders);
//			shell.setText(filename);
//		}
	}

	/*
	 * 
	 */
	protected void setStatus(String message) {
		txtStatus.setText(message);
	}

	// TODO FillTable
	public void fillTable() {

		JCO.Table sales_orders = Client.sales_orders;

		// columns
		for (JCO.FieldIterator e = sales_orders.fields(); e.hasMoreElements();) {
			JCO.Field field = e.nextField();
			// Create Column
			TableColumn id = new TableColumn(table, SWT.LEFT);
			id.setText(field.getName());
			id.setWidth(100);
		}
		// rows
		int i;
		do {

			i = 0;
			TableItem item1 = new TableItem(table, SWT.NONE);
			// Loop over all columns in the current row
			for (JCO.FieldIterator e = sales_orders.fields(); e
					.hasMoreElements();) {
				JCO.Field field = e.nextField();
				item1.setText(i, field.getString());
				i++;
			}
		} while (sales_orders.nextRow());
	}
}
