package gui;

import java.io.*;
import java.util.*;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import com.sap.mw.jco.JCO;
import control.Controller;

/*
 * Hauptfenster des Programms
 */
public class MainWindow extends org.eclipse.swt.widgets.Composite {

	Properties appSettings = new Properties();
	Cursor defaultCursor; // To change the cursor to an arrow, use
							// setCursor(defaultCursor);
	Cursor waitCursor; // To change the cursor to an hourglass, use
						// setCursor(waitCursor);
	
	private Menu menu1;
	private MenuItem exitMenuItem;
	private MenuItem saveFileMenuItem;
	private MenuItem openFileMenuItem;
	private Composite clientArea;
	private Label txtStatus;
	private Composite statusArea;
	private Menu fileMenu;
	private MenuItem fileMenuItem;
	private MenuItem sapMenuItem;
	private MenuItem querySapMenuItem;
	private Menu sapMenu;
	private MenuItem evalSapMenuItem;
	private MenuItem conSapMenuItem;
	
	private Table table;
	public JCO.Table sales_orders;
	public String tmp = "<?xml version=1.0 encoding=UTF-8?><rfc:BAPIORDERS xmlns:rfc=\"urn:sap-com:document:sap:rfc:functions\"><item><SD_DOC>0000000019</SD_DOC><ITM_NUMBER>000010</ITM_NUMBER><MATERIAL>000000000000000026</MATERIAL><SHORT_TEXT>Lottozahlengenerator</SHORT_TEXT><DOC_TYPE>BV</DOC_TYPE><DOC_DATE>2005-05-30</DOC_DATE><REQ_QTY>1.000</REQ_QTY><REQ_DATE>2005-05-30</REQ_DATE><PURCH_NO>2005lz050005</PURCH_NO><BATCH></BATCH><VALID_FROM>0000-00-00</VALID_FROM><VALID_TO>9999-12-31</VALID_TO><BILL_BLOCK></BILL_BLOCK><DLV_BLOCK></DLV_BLOCK><SOLD_TO>0000100001</SOLD_TO><NAME>7</NAME><EXCHG_RATE>1.00000</EXCHG_RATE><DLV_QTY>1.000</DLV_QTY><BASE_UOM>ST</BASE_UOM><NET_PRICE>4.31</NET_PRICE><COND_P_UNT>1</COND_P_UNT><COND_UNIT>ST</COND_UNIT><NET_VAL_HD>4.31</NET_VAL_HD><NET_VALUE>4.31</NET_VALUE><DIVISION>01</DIVISION><DOC_STATUS>erledigt</DOC_STATUS><SALES_GRP>001</SALES_GRP><SALES_OFF>WING</SALES_OFF><SALES_ORG>WING</SALES_ORG><SALES_UNIT>ST</SALES_UNIT><SHIP_POINT>WING</SHIP_POINT><DISTR_CHAN>03</DISTR_CHAN><GI_DATE>2005-05-30</GI_DATE><CURRENCY>EUR</CURRENCY><PLANT>GP1</PLANT><STORE_LOC></STORE_LOC><ORD_REASON></ORD_REASON><REASON_REJ></REASON_REJ><B_UOM_ISO>C62</B_UOM_ISO><CD_UNT_ISO>C62</CD_UNT_ISO><S_UNIT_ISO>C62</S_UNIT_ISO><CURR_ISO>EUR</CURR_ISO><PURCH_NO_C>2005lz050005</PURCH_NO_C><EXCHG_RATE_V>0</EXCHG_RATE_V><MAT_EXT></MAT_EXT><MAT_GUID></MAT_GUID><MAT_VERS></MAT_VERS><CREATION_DATE>2005-05-30</CREATION_DATE><CREATION_TIME>13:18:12</CREATION_TIME></item><item><SD_DOC>0000000004</SD_DOC><ITM_NUMBER>000010</ITM_NUMBER><MATERIAL>000000000000000026</MATERIAL><SHORT_TEXT>Lottozahlengenerator</SHORT_TEXT><DOC_TYPE>SO</DOC_TYPE><DOC_DATE>2005-05-28</DOC_DATE><REQ_QTY>1.000</REQ_QTY><REQ_DATE>2005-05-28</REQ_DATE><PURCH_NO>4</PURCH_NO><BATCH></BATCH><VALID_FROM>0000-00-00</VALID_FROM><VALID_TO>9999-12-31</VALID_TO><BILL_BLOCK></BILL_BLOCK><DLV_BLOCK></DLV_BLOCK><SOLD_TO>0000100001</SOLD_TO><NAME>3</NAME><EXCHG_RATE>1.00000</EXCHG_RATE><DLV_QTY>1.000</DLV_QTY><BASE_UOM>ST</BASE_UOM><NET_PRICE>4.31</NET_PRICE><COND_P_UNT>1</COND_P_UNT><COND_UNIT>ST</COND_UNIT><NET_VAL_HD>4.31</NET_VAL_HD><NET_VALUE>4.31</NET_VALUE><DIVISION>01</DIVISION><DOC_STATUS>erledigt</DOC_STATUS><SALES_GRP>001</SALES_GRP><SALES_OFF>WING</SALES_OFF><SALES_ORG>WING</SALES_ORG><SALES_UNIT>ST</SALES_UNIT><SHIP_POINT>WING</SHIP_POINT><DISTR_CHAN>03</DISTR_CHAN><GI_DATE>2005-05-28</GI_DATE><CURRENCY>EUR</CURRENCY><PLANT>GP1</PLANT><STORE_LOC></STORE_LOC><ORD_REASON></ORD_REASON><REASON_REJ></REASON_REJ><B_UOM_ISO>C62</B_UOM_ISO><CD_UNT_ISO>C62</CD_UNT_ISO><S_UNIT_ISO>C62</S_UNIT_ISO><CURR_ISO>EUR</CURR_ISO><PURCH_NO_C>4</PURCH_NO_C><EXCHG_RATE_V>0</EXCHG_RATE_V><MAT_EXT></MAT_EXT><MAT_GUID></MAT_GUID><MAT_VERS></MAT_VERS><CREATION_DATE>2005-05-28</CREATION_DATE><CREATION_TIME>14:48:30</CREATION_TIME></item><item><SD_DOC>0000000003</SD_DOC><ITM_NUMBER>000010</ITM_NUMBER><MATERIAL>000000000000000026</MATERIAL><SHORT_TEXT>Lottozahlengenerator</SHORT_TEXT><DOC_TYPE>SO</DOC_TYPE><DOC_DATE>2005-05-28</DOC_DATE><REQ_QTY>1.000</REQ_QTY><REQ_DATE>2005-05-28</REQ_DATE><PURCH_NO>3</PURCH_NO><BATCH></BATCH><VALID_FROM>0000-00-00</VALID_FROM><VALID_TO>9999-12-31</VALID_TO><BILL_BLOCK></BILL_BLOCK><DLV_BLOCK></DLV_BLOCK><SOLD_TO>0000100001</SOLD_TO><NAME>3</NAME><EXCHG_RATE>1.00000</EXCHG_RATE><DLV_QTY>0</DLV_QTY><BASE_UOM>ST</BASE_UOM><NET_PRICE>4.31</NET_PRICE><COND_P_UNT>1</COND_P_UNT><COND_UNIT>ST</COND_UNIT><NET_VAL_HD>4.31</NET_VAL_HD><NET_VALUE>4.31</NET_VALUE><DIVISION>01</DIVISION><DOC_STATUS>nicht beliefert</DOC_STATUS><SALES_GRP>001</SALES_GRP><SALES_OFF>WING</SALES_OFF><SALES_ORG>WING</SALES_ORG><SALES_UNIT>ST</SALES_UNIT><SHIP_POINT>WING</SHIP_POINT><DISTR_CHAN>03</DISTR_CHAN><GI_DATE>2005-05-28</GI_DATE><CURRENCY>EUR</CURRENCY><PLANT>GP1</PLANT><STORE_LOC></STORE_LOC><ORD_REASON></ORD_REASON><REASON_REJ></REASON_REJ><B_UOM_ISO>C62</B_UOM_ISO><CD_UNT_ISO>C62</CD_UNT_ISO><S_UNIT_ISO>C62</S_UNIT_ISO><CURR_ISO>EUR</CURR_ISO><PURCH_NO_C>3</PURCH_NO_C><EXCHG_RATE_V>0</EXCHG_RATE_V><MAT_EXT></MAT_EXT><MAT_GUID></MAT_GUID><MAT_VERS></MAT_VERS><CREATION_DATE>2005-05-28</CREATION_DATE><CREATION_TIME>14:43:47</CREATION_TIME></item><item><SD_DOC>0000000002</SD_DOC><ITM_NUMBER>000010</ITM_NUMBER><MATERIAL>000000000000000026</MATERIAL><SHORT_TEXT>Lottozahlengenerator</SHORT_TEXT><DOC_TYPE>BV</DOC_TYPE><DOC_DATE>2005-05-28</DOC_DATE><REQ_QTY>1.000</REQ_QTY><REQ_DATE>2005-05-28</REQ_DATE><PURCH_NO>0002</PURCH_NO><BATCH></BATCH><VALID_FROM>0000-00-00</VALID_FROM><VALID_TO>9999-12-31</VALID_TO><BILL_BLOCK></BILL_BLOCK><DLV_BLOCK></DLV_BLOCK><SOLD_TO>0000100001</SOLD_TO><NAME>2</NAME><EXCHG_RATE>1.00000</EXCHG_RATE><DLV_QTY>1.000</DLV_QTY><BASE_UOM>ST</BASE_UOM><NET_PRICE>4.31</NET_PRICE><COND_P_UNT>1</COND_P_UNT><COND_UNIT>ST</COND_UNIT><NET_VAL_HD>4.31</NET_VAL_HD><NET_VALUE>4.31</NET_VALUE><DIVISION>01</DIVISION><DOC_STATUS>erledigt</DOC_STATUS><SALES_GRP>001</SALES_GRP><SALES_OFF>WING</SALES_OFF><SALES_ORG>WING</SALES_ORG><SALES_UNIT>ST</SALES_UNIT><SHIP_POINT>WING</SHIP_POINT><DISTR_CHAN>03</DISTR_CHAN><GI_DATE>2005-05-28</GI_DATE><CURRENCY>EUR</CURRENCY><PLANT>GP1</PLANT><STORE_LOC></STORE_LOC><ORD_REASON></ORD_REASON><REASON_REJ></REASON_REJ><B_UOM_ISO>C62</B_UOM_ISO><CD_UNT_ISO>C62</CD_UNT_ISO><S_UNIT_ISO>C62</S_UNIT_ISO><CURR_ISO>EUR</CURR_ISO><PURCH_NO_C>0002</PURCH_NO_C><EXCHG_RATE_V>0</EXCHG_RATE_V><MAT_EXT></MAT_EXT><MAT_GUID></MAT_GUID><MAT_VERS></MAT_VERS><CREATION_DATE>2005-05-28</CREATION_DATE><CREATION_TIME>12:49:18</CREATION_TIME></item><item><SD_DOC>0000000001</SD_DOC><ITM_NUMBER>000010</ITM_NUMBER><MATERIAL>000000000000000026</MATERIAL><SHORT_TEXT>Lottozahlengenerator</SHORT_TEXT><DOC_TYPE>BV</DOC_TYPE><DOC_DATE>2005-05-28</DOC_DATE><REQ_QTY>1.000</REQ_QTY><REQ_DATE>2005-05-28</REQ_DATE><PURCH_NO>0001</PURCH_NO><BATCH></BATCH><VALID_FROM>0000-00-00</VALID_FROM><VALID_TO>9999-12-31</VALID_TO><BILL_BLOCK></BILL_BLOCK><DLV_BLOCK></DLV_BLOCK><SOLD_TO>0000100001</SOLD_TO><NAME>1</NAME><EXCHG_RATE>1.00000</EXCHG_RATE><DLV_QTY>1.000</DLV_QTY><BASE_UOM>ST</BASE_UOM><NET_PRICE>4.31</NET_PRICE><COND_P_UNT>1</COND_P_UNT><COND_UNIT>ST</COND_UNIT><NET_VAL_HD>4.31</NET_VAL_HD><NET_VALUE>4.31</NET_VALUE><DIVISION>01</DIVISION><DOC_STATUS>erledigt</DOC_STATUS><SALES_GRP>001</SALES_GRP><SALES_OFF>WING</SALES_OFF><SALES_ORG>WING</SALES_ORG><SALES_UNIT>ST</SALES_UNIT><SHIP_POINT>WING</SHIP_POINT><DISTR_CHAN>03</DISTR_CHAN><GI_DATE>2005-05-28</GI_DATE><CURRENCY>EUR</CURRENCY><PLANT>GP1</PLANT><STORE_LOC></STORE_LOC><ORD_REASON></ORD_REASON><REASON_REJ></REASON_REJ><B_UOM_ISO>C62</B_UOM_ISO><CD_UNT_ISO>C62</CD_UNT_ISO><S_UNIT_ISO>C62</S_UNIT_ISO><CURR_ISO>EUR</CURR_ISO><PURCH_NO_C>0001</PURCH_NO_C><EXCHG_RATE_V>0</EXCHG_RATE_V><MAT_EXT></MAT_EXT><MAT_GUID></MAT_GUID><MAT_VERS></MAT_VERS><CREATION_DATE>2005-05-28</CREATION_DATE><CREATION_TIME>12:46:33</CREATION_TIME></item></rfc:BAPIORDERS>";

	
	public static void run() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		@SuppressWarnings("unused")
		MainWindow inst = new MainWindow(shell, SWT.NULL);
		shell.setLayout(new FillLayout());

		shell.setText("CSAVA V1.1");

		shell.layout();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	public MainWindow(Composite parent, int style) {
		super(parent, style);
		initGUI();
		setPreferences();
		waitCursor = getDisplay().getSystemCursor(SWT.CURSOR_WAIT);
		defaultCursor = getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
		clientArea.setFocus();
	}

	// Load application settings from file
	private void setPreferences() {
		try {
			appSettings.load(new FileInputStream("settings.ini"));
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		// By default, center window
		int width = Integer.parseInt(appSettings.getProperty("width", "640"));
		int height = Integer.parseInt(appSettings.getProperty("height", "480"));
		Rectangle screenBounds = getDisplay().getBounds();
		int defaultTop = (screenBounds.height - height) / 2;
		int defaultLeft = (screenBounds.width - width) / 2;
		int top = Integer.parseInt(appSettings.getProperty("top", String
				.valueOf(defaultTop)));
		int left = Integer.parseInt(appSettings.getProperty("left", String
				.valueOf(defaultLeft)));
		getShell().setSize(width, height);
		getShell().setLocation(left, top);
		saveShellBounds();
	}

	/**
	 * Initializes the GUI.
	 */
	private void initGUI() {
		try {
			getShell().addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent evt) {
					shellWidgetDisposed(evt);
				}
			});

			getShell().addControlListener(new ControlAdapter() {
				public void controlResized(ControlEvent evt) {
					shellControlResized(evt);
				}
			});

			getShell().addControlListener(new ControlAdapter() {
				public void controlMoved(ControlEvent evt) {
					shellControlMoved(evt);
				}
			});

			GridLayout thisLayout = new GridLayout();
			this.setLayout(thisLayout);
			
			
			{
				clientArea = new Composite(this, SWT.NONE);
				GridData clientAreaLData = new GridData();
				clientAreaLData.grabExcessHorizontalSpace = true;
				clientAreaLData.grabExcessVerticalSpace = true;
				clientAreaLData.horizontalAlignment = GridData.FILL;
				clientAreaLData.verticalAlignment = GridData.FILL;
				clientArea.setLayoutData(clientAreaLData);
				clientArea.setLayout(new GridLayout());
				{
					
					table = new Table(clientArea, SWT.BORDER 
							| SWT.MULTI | SWT.SCROLL_LINE);
					table.setLinesVisible(true);
					table.setHeaderVisible(true);
					
					table.setLayoutData(new GridData(GridData.FILL_BOTH));

				}
			}
			{
				statusArea = new Composite(this, SWT.NONE);
				GridLayout statusAreaLayout = new GridLayout();
				statusAreaLayout.makeColumnsEqualWidth = true;
				statusAreaLayout.horizontalSpacing = 0;
				statusAreaLayout.marginHeight = 0;
				statusAreaLayout.marginWidth = 0;
				statusAreaLayout.verticalSpacing = 0;
				statusArea.setLayout(statusAreaLayout);
				GridData statusAreaLData = new GridData();
				statusAreaLData.horizontalAlignment = GridData.FILL;
				statusAreaLData.grabExcessHorizontalSpace = true;
				statusArea.setLayoutData(statusAreaLData);
//				statusArea.setBackground(SWTResourceManager.getColor(239, 237,
//						224));
				{
					txtStatus = new Label(statusArea, SWT.BORDER);
					txtStatus.setText("No Connection to Server and SAP");
					GridData txtStatusLData = new GridData();
					txtStatusLData.horizontalAlignment = GridData.FILL;
					txtStatusLData.grabExcessHorizontalSpace = true;
					txtStatusLData.verticalIndent = 3;
					txtStatus.setLayoutData(txtStatusLData);
				}
			}
			thisLayout.verticalSpacing = 0;
			thisLayout.marginWidth = 0;
			thisLayout.marginHeight = 0;
			thisLayout.horizontalSpacing = 0;
			thisLayout.marginTop = 3;
			this.setSize(474, 312);
			{
				menu1 = new Menu(getShell(), SWT.BAR);
				getShell().setMenuBar(menu1);
				{
					fileMenuItem = new MenuItem(menu1, SWT.CASCADE);
					fileMenuItem.setText("&File");
					{
						fileMenu = new Menu(fileMenuItem);
						
						{
							openFileMenuItem = new MenuItem(fileMenu, SWT.PUSH);
							openFileMenuItem.setText("&Open");
							
							openFileMenuItem
									.addSelectionListener(new SelectionAdapter() {
										public void widgetSelected(
												SelectionEvent evt) {
											
											FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
											String filename = dialog.open();
											if (filename != null) {
												try {
													JCO.Table sales = new JCO.Table("BAPIORDERS");
													sales.appendRow();
													sales.readXML(filename);
													sales.writeXML("test.xml");
													fillTable(sales);
//													(new InputStreamReader
//															(new FileInputStream(filename), "UTF-8"));
												
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
												getShell().setText(filename);
											}
//											openFileMenuItemWidgetSelected(evt);
										}
									});
						}
						
						
						{
							saveFileMenuItem = new MenuItem(fileMenu, SWT.PUSH);
							saveFileMenuItem.setText("&Save");
//							saveFileMenuItem.setEnabled(false);
						
							saveFileMenuItem.addSelectionListener(new SelectionAdapter() {
								public void widgetSelected(SelectionEvent evt) {
									// TODO: SAVE
									saveToolItemWidgetSelected(evt);
								}
							});	
						}
						
						{
							exitMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
							exitMenuItem.setText("&Exit");
							exitMenuItem
									.addSelectionListener(new SelectionAdapter() {
										public void widgetSelected(
												SelectionEvent evt) {
											exitMenuItemWidgetSelected(evt);
										}
									});
						}
						fileMenuItem.setMenu(fileMenu);
					}
					
					sapMenuItem = new MenuItem(menu1, SWT.CASCADE);
					sapMenuItem.setText("&SAP");
					{
						sapMenu = new Menu(sapMenuItem);
						// Verbinden
						{
							conSapMenuItem = new MenuItem(sapMenu, SWT.PUSH);
							conSapMenuItem.setText("&Connect");
						
							conSapMenuItem
									.addSelectionListener(new SelectionAdapter() {
										public void widgetSelected(
												SelectionEvent evt) {

											// Show connect window
											showConnectWindow(evt);
										}


									});
						}
						// Anfragen
						{
							querySapMenuItem = new MenuItem(sapMenu, SWT.PUSH);
							querySapMenuItem.setText("&Query");
//							querySapMenuItem.setEnabled(false);
							
							querySapMenuItem
									.addSelectionListener(new SelectionAdapter() {
										public void widgetSelected(
												SelectionEvent evt) {

											// Show query window
											showQueryWindow(evt);
											
										}
									});
						}
						// Auswerten
						{
							evalSapMenuItem = new MenuItem(sapMenu, SWT.PUSH);
							evalSapMenuItem.setText("&Evaluate");
							evalSapMenuItem.setEnabled(false);
							
							evalSapMenuItem
									.addSelectionListener(new SelectionAdapter() {
										public void widgetSelected(
												SelectionEvent evt) {

											// Show evaluate window
											showEvalWindow(evt);
										}
									});
						}
						sapMenuItem.setMenu(sapMenu);
					}
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void saveToolItemWidgetSelected(SelectionEvent evt) {
		// TODO 
		// Save
		this.sales_orders.writeHTML("JCOTable.html");		
		try {
			this.sales_orders.writeXML("JCOTable.xml");
		} catch (IOException e1) {
			// TODO 
			e1.printStackTrace();
		}
		
	}

	protected void showEvalWindow(SelectionEvent evt) {
		// TODO 
		Controller.showEvalWindow(this);
	}

	protected void showQueryWindow(SelectionEvent evt) {
		// TODO 
		Controller.showQueryWindow(this);
		
	}

	protected void showConnectWindow(SelectionEvent evt) {		
		// TODO 
		setStatus("Connecting to Server and SAP");	

		Controller.showConnectWindow(this);	
		
		//setStatus("Connected to Server and SAP");	

	}

	private void exitMenuItemWidgetSelected(SelectionEvent evt) {
		try {
			// Save settings to file
			appSettings.store(new FileOutputStream("settings.ini"), "");
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		getShell().dispose();
	}

	private void openFileMenuItemWidgetSelected(SelectionEvent evt) {
		FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
		String filename = dialog.open();
		if (filename != null) {
			
//			fillTable(this.sales_orders);
			getShell().setText(filename);
		}
	}

	private void openToolItemWidgetSelected(SelectionEvent evt) {
		openFileMenuItemWidgetSelected(evt);
	}

	private void shellWidgetDisposed(DisposeEvent evt) {
		try {
			// Save settings to file
			appSettings.store(new FileOutputStream("settings.ini"), "");
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	private void shellControlResized(ControlEvent evt) {
		saveShellBounds();
	}

	// Save window location in appSettings hash table
	private void saveShellBounds() {
		// Save window bounds in settings
		Rectangle bounds = getShell().getBounds();
		appSettings.setProperty("top", String.valueOf(bounds.y));
		appSettings.setProperty("left", String.valueOf(bounds.x));
		appSettings.setProperty("width", String.valueOf(bounds.width));
		appSettings.setProperty("height", String.valueOf(bounds.height));
	}

	private void shellControlMoved(ControlEvent evt) {
		saveShellBounds();
	}

	@SuppressWarnings("unused")
	protected void setStatus(String message) {
		txtStatus.setText(message);
	}
	
	// TODO FillTable
	public void fillTable(JCO.Table sales_orders ) {
		
		this.sales_orders = sales_orders;
		
		// columns
		for (JCO.FieldIterator e = sales_orders.fields(); e.hasMoreElements();) {
			JCO.Field field = e.nextField();
			// Create Column
			TableColumn id = new TableColumn(table, SWT.LEFT);
			id.setText(field.getName());
			id.setWidth(100);
		}

		System.out.println("Anzahl von Spalten" + ":\t"
				+ table.getColumnCount());

		// rows
		int i;
		do {
			System.out.println("-----------------------------------------");
			//
			i = 0;
			TableItem item1 = new TableItem(table, SWT.NONE);
			// Loop over all columns in the current row
			for (JCO.FieldIterator e = sales_orders.fields(); e
					.hasMoreElements();) {
				JCO.Field field = e.nextField();

				// Output
				System.out.println(field.getName() + ":\t" + field.getString());

				item1.setText(i, field.getString());
				i++;

			}
		} while (sales_orders.nextRow());
		//table.redraw();
	}
}
