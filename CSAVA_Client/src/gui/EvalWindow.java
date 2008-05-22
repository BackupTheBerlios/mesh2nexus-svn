package gui;

import java.util.LinkedList;

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
import com.sap.mw.jco.JCO;
import client.Client;

/**
 * Fenster zur Anzeige der statistischen Auswertung der
 * Verkaufsbelege
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
	 * Konstruktor für EvalWindow
	 */
	public EvalWindow(MainWindow par) {
		
		parent = par;
		shell = new Shell(parent.shell, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);

		// EvalWindow Initialisierung
		init(shell);

		shell.layout();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!parent.shell.getDisplay().readAndDispatch())
				parent.shell.getDisplay().sleep();
		}
	}
	
	/**
	 * Initialisiert EvalWindow und dessen Elemente
	 */
	private void init(Shell shell) {
	
		// GridLayout setzen
		GridLayout thisLayout = new GridLayout();
	    thisLayout.numColumns = 2;
		shell.setLayout(thisLayout);
		shell.setText("Auswertung");
		
		// Gesamtanzahl der Belege
		CountLabel = new Label(shell, SWT.NONE);
		CountLabel.setText("Gesamtanzahl:");
		CountLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		CountLabel.pack();
		
		// Berechnung
		int rows = Client.sales_orders.getNumRows();
		String numRows = "" + rows;
		
		Count = new Label(shell, SWT.BORDER);
		Count.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Count.setText(numRows);
		Count.pack();
		
		// Belege mit Status 'offen'	
		CountOpenLabel = new Label(shell, SWT.NONE);
		CountOpenLabel.setText("Status offen:");	
		CountOpenLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		CountOpenLabel.pack();
		
		// Berechnung
		int closed = getClosed();
		String numOpen = "" + (rows - closed);
		
		CountOpen = new Label(shell, SWT.BORDER);
		CountOpen.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		CountOpen.setText(numOpen);
		CountOpen.pack();
		
		// Belege mit Status 'erledigt'	
		CountClosedLabel = new Label(shell, SWT.NONE);
		CountClosedLabel.setText("Status abgeschlossen:");
		CountClosedLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		CountClosedLabel.pack();
		
		// Berechnung		
		String numClosed = "" + closed;
		
		CountClosed = new Label(shell, SWT.BORDER);
		CountClosed.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		CountClosed.setText(numClosed);
		CountClosed.pack();
		
		// Anzahl der verschiedenen Kunden
		CountCustLabel = new Label(shell, SWT.NONE);
		CountCustLabel.setText("Verschiedene Kunden:");
		CountCustLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		CountCustLabel.pack();
		
		CountCust = new Label(shell, SWT.BORDER);
		CountCust.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		CountCust.setText("" + getCustomersCount());
		CountCust.pack();
		
		Label empty = new Label(shell, SWT.NONE);		
		
		eval = new Button(shell, SWT.PUSH);
		eval.setText("Ok");
		eval.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		eval.pack();
		
		// Aktion beim Klicken auf OK-Button
		eval.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				
				// schließe EvalWindow
				closeEvalWindow();
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
	 * Berechnet die Anzahl der Verkaufsbelegen mit dem Status 'erledigt'
	 */
	private int getClosed() {
		
		// Zähler für Belege
		int closed = 0;
		
		// Setze Iterator auf die erste Reihe
		Client.sales_orders.firstRow();
		
		// Schleife über alle Reihen der Tabelle
		do {
				
			// Schleife über alle Zellen in einer Reihe
			for (JCO.FieldIterator e = Client.sales_orders.fields(); e
					.hasMoreElements();) {
				JCO.Field field = e.nextField();
				
				// Wenn in Spalte DOC_STATUS 'erledigt' steht
				if (field.getName().equals("DOC_STATUS") &&
						field.getString().equals("erledigt")){
				
					// Addiere 1
					closed++;
				}				
				
			}
		} while (Client.sales_orders.nextRow());
		
		return closed;			
	}
	
	private int getCustomersCount (){
		
		// Liste für alle Einträge der Spalte 'SOLD_TO'
		LinkedList li = new LinkedList();
		
		// Setze Iterator auf die erste Reihe
		Client.sales_orders.firstRow();
		
		// Schleife über alle Reihen der Tabelle
		do {
				
			// Schleife über alle Zellen in einer Reihe
			for (JCO.FieldIterator e = Client.sales_orders.fields(); e
					.hasMoreElements();) {
				JCO.Field field = e.nextField();
				
				// Speichere alle Einträge der Spalte 'SOLD_TO' in der Liste
				if (field.getName().equals("SOLD_TO")){
				
					li.addLast(field.getString());
					
				}				
				
			}
		} while (Client.sales_orders.nextRow());
		
		
		int count = 0;
		boolean equal;
		
		// Eleminiere Dulikate in der Liste der Kundennummer
		for (int i = 0; i < li.size()-1; i++ ){
			
			equal = false;
			for (int j = i+1; j < li.size()-1; j++){
				
				if (((String) li.get(i)).equals((String) li.get(j)) && (i != j)){
					
					equal = true;
					break;
				}
			}
			if (!equal){
				count++;
			}
		}
		
		
		return count;
	}
	
	/**
	 * schließt EvalWindow
	 */
	private void closeEvalWindow() {		
		shell.dispose();		
	}
}
