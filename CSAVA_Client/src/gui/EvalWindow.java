/**
 * 
 */
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
import org.eclipse.swt.widgets.TableItem;

import com.sap.mw.jco.JCO;

import client.Client;

/**
 * @author 
 *
 */
public class EvalWindow {
	
	private Label CountLabel;
	private Label CountOpenLabel;
	private Label CountClosedLabel;
	private Label CountCustLabel;	

	private Label Count;
	private Label CountOpen;
	private Label CountClosed;
	private Label CountCust;
	
	private Button eval;	
	private MainWindow parent;	
	private Shell shell;
	
	/**
	 * 
	 */
	public EvalWindow(MainWindow par) {
		
		parent = par;
		shell = new Shell(parent.shell, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);

		init(shell);

		shell.layout();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!parent.shell.getDisplay().readAndDispatch())
				parent.shell.getDisplay().sleep();
		}
	}
	
	private void init(Shell shell) {
		
		shell.setSize(170, 145);
		
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
		
		shell.setText("Auswerten");
		
		// Gesamtanzahl
		CountLabel = new Label(shell, SWT.NONE);
		CountLabel.setText("Gesamtanzahl:");
		CountLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		int rows = Client.sales_orders.getNumRows();
		String numRows = "" + rows;
		Count = new Label(shell, SWT.BORDER);
		Count.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Count.setText(numRows);		
		
		
		// Status offen	
		CountOpenLabel = new Label(shell, SWT.NONE);
		CountOpenLabel.setText("Status offen:");	
		CountOpenLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		int open = getOpen();
		String numOpen = "" + open;
		CountOpen = new Label(shell, SWT.BORDER);
		CountOpen.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		CountOpen.setText(numOpen);
		
		
		// Status abgeschlossen		
		CountClosedLabel = new Label(shell, SWT.NONE);
		CountClosedLabel.setText("Status abgeschlossen:");
		CountClosedLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		int closed = rows - open;		
		String numClosed = "" + closed;		
		CountClosed = new Label(shell, SWT.BORDER);
		CountClosed.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		CountClosed.setText(numClosed);
		
		
		// Verschiedene Kunden
		CountCustLabel = new Label(shell, SWT.NONE);
		CountCustLabel.setText("Verschiedene Kunden:");
		CountCustLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		CountCust = new Label(shell, SWT.BORDER);
		CountCust.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		

		@SuppressWarnings("unused")
		Label empty = new Label(shell, SWT.NONE);		
		
		eval = new Button(shell, SWT.PUSH);
		eval.setText("Ok");
		eval.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		eval.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				close();
			}
		});
	}
	
	/*
	 * 
	 */
	protected int getOpen() {
		int open = 0;
		int i;
		do {
			i = 0;			
			// Loop over all columns in the current row
			for (JCO.FieldIterator e = Client.sales_orders.fields(); e
					.hasMoreElements();) {
				JCO.Field field = e.nextField();
				
				// Wenn in Spalte DOC_STATUS nicht erledigt
//				if (field.getName().equals("DOC_STATUS") ){
				if (field.getString().equals("erledigt") ){
					open++;
				}				
				i++;
			}
		} while (Client.sales_orders.nextRow());		
		return open;			
	}
	
	/*
	 * 
	 */
	protected void close() {		
		shell.dispose();		
	}
}
