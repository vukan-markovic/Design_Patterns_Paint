package observer;

import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import controller.DrawingController;
import frame.DrawingFrame;
import model.DrawingModel;

public class DrawingObserver implements PropertyChangeListener {
	DrawingFrame frame;
	
	public DrawingObserver(DrawingFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Method that add listener to some button and enable it.
	 * 
	 * @param button Represent button which need to be updated.
	 * @param adapter Represent adapter for that button.
	 */
	public void addListener(JButton button, MouseAdapter adapter) {
		if (!button.isEnabled()) {
			button.setEnabled(true);
			button.addMouseListener(adapter);
		}
	}
	
	/**
	 * Method that remove listener from some button and disable it.
	 * 
	 * @param button Represent button which need to be updated.
	 * @param adapter Represent adapter for that button.
	 */
	public void removeListener(JButton button, MouseAdapter adapter) {
		if (button.isEnabled()) {
			button.removeMouseListener(adapter);
			button.setEnabled(false);
		}
	}
	
	/**
	 * Listen to changes from {@link DrawingModel} that sends Subject - {@link DrawingController} to update buttons depend on state of draw in {@link DrawingFrame}.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch(evt.getPropertyName()) {
			case "shape selected":
				addListener(frame.getBtnDelete(), frame.getMouseAdapterDelete());
				break;
			case "shape exist":
				frame.getTglBtnSelect().setEnabled(true);
				break;
			case "shape unselected":
			case "shape don't exist":
				removeListener(frame.getBtnUpdate(), frame.getMouseAdapterUpdate());
				removeListener(frame.getBtnDelete(), frame.getMouseAdapterDelete());
				break;
			case "update/move turn off":
				removeListener(frame.getBtnToBack(), frame.getMouseAdapterToBack());
				removeListener(frame.getBtnToFront(), frame.getMouseAdapterToFront());
				removeListener(frame.getBtnBringToBack(), frame.getMouseAdapterBringToBack());
				removeListener(frame.getBtnBringToFront(), frame.getMouseAdapterBringToFront());
				removeListener(frame.getBtnUpdate(), frame.getMouseAdapterUpdate());
				break;
			case "update/move turn on": 
				addListener(frame.getBtnToBack(), frame.getMouseAdapterToBack());
				addListener(frame.getBtnToFront(), frame.getMouseAdapterToFront());
				addListener(frame.getBtnBringToBack(), frame.getMouseAdapterBringToBack());
				addListener(frame.getBtnBringToFront(), frame.getMouseAdapterBringToFront());
				addListener(frame.getBtnUpdate(), frame.getMouseAdapterUpdate());
				break;
			case "redo turn off": 
				removeListener(frame.getBtnRedo(), frame.getMouseAdapterRedo());
				break;
			case "redo turn on": 
				addListener(frame.getBtnRedo(), frame.getMouseAdapterRedo());
				break;
			case "draw is not empty":
				addListener(frame.getBtnSaveDraw(), frame.getMouseAdapterSaveDrawing());
				addListener(frame.getBtnNewDraw(), frame.getMouseAdapterNewDraw());
				addListener(frame.getBtnUndo(), frame.getMouseAdapterUndo());
				addListener(frame.getBtnLog(), frame.getMouseAdapterLog());
				break;
			case "draw is empty":
				removeListener(frame.getBtnSaveDraw(), frame.getMouseAdapterSaveDrawing());
				removeListener(frame.getBtnNewDraw(), frame.getMouseAdapterNewDraw());
				removeListener(frame.getBtnUndo(), frame.getMouseAdapterUndo());
				removeListener(frame.getBtnLog(), frame.getMouseAdapterLog());
				addListener(frame.getBtnEdgeColor(), frame.getMouseAdapterEdgeColor());
				addListener(frame.getBtnInteriorColor(), frame.getMouseAdapterInteriorColor());
				frame.getTglBtnDrawCircle().setEnabled(true);
				frame.getTglBtnDrawLine().setEnabled(true);
				frame.getTglBtnDrawPoint().setEnabled(true);
				frame.getTglBtnDrawSquare().setEnabled(true);
				frame.getTglBtnDrawRectangle().setEnabled(true);
				frame.getTglBtnDrawHexagon().setEnabled(true);
				break;
			case "serialized draw opened":
				addListener(frame.getBtnNewDraw(), frame.getMouseAdapterNewDraw());
				removeListener(frame.getBtnSaveDraw(), frame.getMouseAdapterSaveDrawing());
				removeListener(frame.getBtnUndo(), frame.getMouseAdapterUndo());
				removeListener(frame.getBtnLog(), frame.getMouseAdapterLog());
				removeListener(frame.getBtnUpdate(), frame.getMouseAdapterUpdate());
				removeListener(frame.getBtnDelete(), frame.getMouseAdapterDelete());
				removeListener(frame.getBtnRedo(), frame.getMouseAdapterRedo());
				removeListener(frame.getBtnToBack(), frame.getMouseAdapterToBack());
				removeListener(frame.getBtnToFront(), frame.getMouseAdapterToFront());
				removeListener(frame.getBtnBringToBack(), frame.getMouseAdapterBringToBack());
				removeListener(frame.getBtnBringToFront(), frame.getMouseAdapterBringToFront());
				removeListener(frame.getBtnUpdate(), frame.getMouseAdapterUpdate());
				removeListener(frame.getBtnEdgeColor(), frame.getMouseAdapterEdgeColor());
				removeListener(frame.getBtnInteriorColor(), frame.getMouseAdapterInteriorColor());
				frame.getTglBtnDrawCircle().setEnabled(false);
				frame.getTglBtnDrawLine().setEnabled(false);
				frame.getTglBtnDrawPoint().setEnabled(false);
				frame.getTglBtnDrawSquare().setEnabled(false);
				frame.getTglBtnDrawRectangle().setEnabled(false);
				frame.getTglBtnDrawHexagon().setEnabled(false);
				frame.getTglBtnSelect().setEnabled(false);
				break;
		}
	}
}