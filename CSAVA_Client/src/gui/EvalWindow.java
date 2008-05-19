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
		
		CountLabel = new Label(shell, SWT.NONE);
		CountLabel.setText("Gesamtanzahl:");
		CountLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		Count = new Label(shell, SWT.BORDER);
		Count.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		CountOpenLabel = new Label(shell, SWT.NONE);
		CountOpenLabel.setText("Status offen:");	
		CountOpenLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		CountOpen = new Label(shell, SWT.BORDER);
		CountOpen.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		CountClosedLabel = new Label(shell, SWT.NONE);
		CountClosedLabel.setText("Status abgeschlossen:");
		CountClosedLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		CountClosed = new Label(shell, SWT.BORDER);
		CountClosed.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		CountCustLabel = new Label(shell, SWT.NONE);
		CountCustLabel.setText("Verschiedene Kunden:");
		CountCustLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		CountCust = new Label(shell, SWT.BORDER);
		CountCust.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		

		Label empty = new Label(shell, SWT.NONE);
		
		
		eval = new Button(shell, SWT.PUSH);
		eval.setText("Ok");
		eval.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		eval.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				close();
			}
		});
		
		// Test Auswertung..
//		String numColumns = Integer.toString(this.parent.sales_orders.getNumColumns());
//		CustNumberLabel.setText("NumColumns:  " + numColumns);	
//		String numRows = "" + this.parent.sales_orders.getNumRows();
//		SalesOrgLabel.setText("NumRows:  " + numRows);	
	}

	protected void close() {

		shell.dispose();
		
	}

}
