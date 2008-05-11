/**
 * 
 */
package gui;

/**
 * @author 
 *
 */

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

import control.Controller;
import gui.LogonGUI;

public class MainGUI {
	
	// static Table
	public static Table table;
	
	public MainGUI() {
		
		//Display display = new Display();
		Shell mainShell = new Shell(LogonGUI.display);
		mainShell.setSize(800, 600);
		mainShell.setText("CSAVA V0.1");
		// Wellcome to CSAVA!!!
//		Label label = new Label(mainShell, SWT.NONE);
//		label.setText("Wellcome to CSAVA!!!");
//		label.setLocation(350, 5);
		
		// Menu
		Menu menu = new Menu(mainShell, SWT.BAR);
		mainShell.setMenuBar(menu);
		MenuItem file = new MenuItem(menu, SWT.CASCADE);
		file.setText("SAP");
		Menu filemenu = new Menu(mainShell, SWT.DROP_DOWN);
		file.setMenu(filemenu);
		MenuItem actionItem = new MenuItem(filemenu, SWT.PUSH);
		actionItem.setText("Anfrage");
		
		// By Click
		actionItem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {				
				System.out.println("Anfragen..");
				
				Controller.StartQueryGui();
				// TODO: MainGui Disable
				
				
			}
		});
		
		// Tabelle 
		table = new Table(mainShell, SWT.BORDER | SWT.MULTI | SWT.SCROLL_LINE);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		table.setSize(795, 400);
		
		// Tabelle erzeugen
/*		TableColumn id = new TableColumn(table,SWT.LEFT);
		id.setText("id");
		id.setWidth(50);		
		TableColumn text = new TableColumn(table,SWT.LEFT);
		text.setText("Beschreibung");
		text.setWidth(50);	  */  
	    
		// Tabelle fuellen
/*		TableItem item1 = new TableItem(table,SWT.NONE);
		item1.setText(new String[] {"1","bla"});*/
		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		//shell.pack();
		//label.pack();
		mainShell.open();
		
		//table.pack();
		//table.redraw();
		
//		while (!mainShell.isDisposed())
//			if (!display.readAndDispatch())
//				display.sleep();
//		display.dispose();
//		label.dispose();
		
		
	}

}
