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

public class MainWindow extends org.eclipse.swt.widgets.Composite {

	Properties appSettings = new Properties();
	Cursor defaultCursor; // To change the cursor to an arrow, use
							// setCursor(defaultCursor);
	Cursor waitCursor; // To change the cursor to an hourglass, use
						// setCursor(waitCursor);
	private Menu menu1;
	private MenuItem aboutMenuItem;
	private Menu helpMenu;
	private MenuItem helpMenuItem;
	private MenuItem exitMenuItem;
	private MenuItem closeFileMenuItem;
	private MenuItem saveFileMenuItem;
	// private MenuItem newFileMenuItem;
	private MenuItem openFileMenuItem;
	// private ToolItem newToolItem;
	private ToolItem saveToolItem;
	private ToolItem openToolItem;
	private ToolBar toolBar;
	@SuppressWarnings("unused")
	private MenuItem fileMenuSep2;
	@SuppressWarnings("unused")
	private MenuItem fileMenuSep1;
	private Composite clientArea;
	private Label txtStatus;
	private Composite statusArea;
	private Menu fileMenu;
	private MenuItem fileMenuItem;
	//
	private MenuItem sapMenuItem;
	private MenuItem querySapMenuItem;
	private Menu sapMenu;
	private MenuItem evalSapMenuItem;
	private MenuItem manMenuItem;
	private MenuItem conSapMenuItem;
	private Table table;
	
	public JCO.Table sales_orders;

	{
		// Register as a resource user - SWTResourceManager will handle the
		// obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	//public static void main(String[] args) {
		public static void run() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		@SuppressWarnings("unused")
		MainWindow inst = new MainWindow(shell, SWT.NULL);
		shell.setLayout(new FillLayout());
		shell.setImage(SWTResourceManager.getImage("images/16x16.png"));
		shell.setText("CSAVA V1.1");
		shell.setBackgroundImage(SWTResourceManager
				.getImage("images/ToolbarBackground.gif"));
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
				GridData toolBarLData = new GridData();
				toolBarLData.grabExcessHorizontalSpace = true;
				toolBarLData.horizontalAlignment = GridData.FILL;
				toolBar = new ToolBar(this, SWT.FLAT);
				toolBar.setLayoutData(toolBarLData);
				toolBar.setBackgroundImage(SWTResourceManager
						.getImage("images/ToolbarBackground.gif"));
				/*
				 * { newToolItem = new ToolItem(toolBar, SWT.NONE);
				 * newToolItem.setImage(SWTResourceManager.getImage("images/new.gif"));
				 * newToolItem.setToolTipText("New"); }
				 */
				{
					openToolItem = new ToolItem(toolBar, SWT.NONE);
					openToolItem.setToolTipText("Open");
					openToolItem.setImage(SWTResourceManager
							.getImage("images/open.gif"));
					openToolItem.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {
							openToolItemWidgetSelected(evt);
						}
					});
				}
				{
					saveToolItem = new ToolItem(toolBar, SWT.NONE);
					saveToolItem.setToolTipText("Save");
					saveToolItem.setImage(SWTResourceManager
							.getImage("images/save.gif"));
					saveToolItem.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {
							// TODO: SAVE
							saveToolItemWidgetSelected(evt);
						}
					});	
				}
			}
			{
				clientArea = new Composite(this, SWT.NONE);
				GridData clientAreaLData = new GridData();
				clientAreaLData.grabExcessHorizontalSpace = true;
				clientAreaLData.grabExcessVerticalSpace = true;
				clientAreaLData.horizontalAlignment = GridData.FILL;
				clientAreaLData.verticalAlignment = GridData.FILL;
				clientArea.setLayoutData(clientAreaLData);
				clientArea.setLayout(null);
				{
					// TODO: Tabelle 
					table = new Table(clientArea, SWT.BORDER 
							| SWT.MULTI | SWT.SCROLL_LINE);
					table.setLinesVisible(true);
					table.setHeaderVisible(true);
					table.setSize(795, 400);

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
				statusArea.setBackground(SWTResourceManager.getColor(239, 237,
						224));
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
						/*
						 * { newFileMenuItem = new MenuItem(fileMenu, SWT.PUSH);
						 * newFileMenuItem.setText("&New");
						 * newFileMenuItem.setImage(SWTResourceManager.getImage("images/new.gif")); }
						 */
						{
							openFileMenuItem = new MenuItem(fileMenu, SWT.PUSH);
							openFileMenuItem.setText("&Open");
							openFileMenuItem.setImage(SWTResourceManager
									.getImage("images/open.gif"));
							openFileMenuItem
									.addSelectionListener(new SelectionAdapter() {
										public void widgetSelected(
												SelectionEvent evt) {
											openFileMenuItemWidgetSelected(evt);
										}
									});
						}
						{
							closeFileMenuItem = new MenuItem(fileMenu,
									SWT.CASCADE);
							closeFileMenuItem.setText("Close");
						}
						{
							fileMenuSep1 = new MenuItem(fileMenu, SWT.SEPARATOR);
						}
						{
							saveFileMenuItem = new MenuItem(fileMenu, SWT.PUSH);
							saveFileMenuItem.setText("&Save");
							saveFileMenuItem.setImage(SWTResourceManager
									.getImage("images/save.gif"));
							saveFileMenuItem.addSelectionListener(new SelectionAdapter() {
								public void widgetSelected(SelectionEvent evt) {
									// TODO: SAVE
									saveToolItemWidgetSelected(evt);
								}
							});	
						}
						{
							fileMenuSep2 = new MenuItem(fileMenu, SWT.SEPARATOR);
						}
						{
							exitMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
							exitMenuItem.setText("E&xit");
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
							conSapMenuItem.setImage(SWTResourceManager
									.getImage("images/new.gif"));
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
							querySapMenuItem.setImage(SWTResourceManager
									.getImage("images/new.gif"));
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
							evalSapMenuItem.setImage(SWTResourceManager
									.getImage("images/new.gif"));
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
				{
					helpMenuItem = new MenuItem(menu1, SWT.CASCADE);
					helpMenuItem.setText("&Help");
					{
						helpMenu = new Menu(helpMenuItem);
						{
							aboutMenuItem = new MenuItem(helpMenu, SWT.CASCADE);
							aboutMenuItem.setText("&About");
							aboutMenuItem
									.addSelectionListener(new SelectionAdapter() {
										public void widgetSelected(
												SelectionEvent evt) {
											
											// About
											aboutMenuItemWidgetSelected(evt);
										}
									});
						}
						{
							manMenuItem = new MenuItem(helpMenu, SWT.CASCADE);
							manMenuItem.setText("&Manual");
							manMenuItem
									.addSelectionListener(new SelectionAdapter() {
										public void widgetSelected(
												SelectionEvent evt) {
											
											// TODO:
											//showManual(evt);
											
										}
									});
						}
						helpMenuItem.setMenu(helpMenu);
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
			getShell().setText(filename);
		}
	}

	private void openToolItemWidgetSelected(SelectionEvent evt) {
		openFileMenuItemWidgetSelected(evt);
	}

	private void aboutMenuItemWidgetSelected(SelectionEvent evt) {
		MessageBox message = new MessageBox(getShell(), SWT.OK
				| SWT.ICON_INFORMATION);
		message.setText("About CSAVA ");
		message.setMessage("Wellcome to \n\nCSAVA v1.0");
		message.open();
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
