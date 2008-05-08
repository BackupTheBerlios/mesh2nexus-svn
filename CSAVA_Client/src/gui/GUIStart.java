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

public class GUIStart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(800, 600);
		
		// Wellcome to CSAVA!!!
		Label label = new Label(shell, SWT.NONE);
		label.setText("Wellcome to CSAVA!!!");
		label.setLocation(350, 5);
		
		// Menu
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		MenuItem file = new MenuItem(menu, SWT.CASCADE);
		file.setText("File");
		Menu filemenu = new Menu(shell, SWT.DROP_DOWN);
		file.setMenu(filemenu);
		MenuItem actionItem = new MenuItem(filemenu, SWT.PUSH);
		actionItem.setText("Action");
		// By Click
		actionItem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Action performed!");
			}
		});
		
		// Tabelle 
		Table table1 = new Table(shell, SWT.BORDER);
		table1.setLinesVisible(true);
		table1.setHeaderVisible(true);
		
		// Tabelle erzeugen
		TableColumn id = new TableColumn(table1,SWT.LEFT);
		id.setText("id");
		id.setWidth(50);		
		TableColumn text = new TableColumn(table1,SWT.LEFT);
		text.setText("Beschreibung");
		text.setWidth(50);
		
		// Tabelle fuellen
		TableItem item1 = new TableItem(table1,SWT.NONE);
		item1.setText(new String[] {"1","bla"});
		
		//shell.pack();
		label.pack();
		shell.open();
		table1.pack();
		
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
		label.dispose();
		
		
	}

}
