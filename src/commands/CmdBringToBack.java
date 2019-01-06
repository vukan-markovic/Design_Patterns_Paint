package commands;

import javax.swing.DefaultListModel;

import model.DrawingModel;
import shapes.Shape;

/**
 * @author Vukan Marković
 *
 * Class that represent command to bring some shape to the back.
 */
public class CmdBringToBack implements Command {
	private DrawingModel model;
	private Shape shape;
	private DefaultListModel<String> log;
	private String command;
	private int index;
	private String shapeType;
	
	public CmdBringToBack(DrawingModel model, Shape shape, DefaultListModel<String> log, String shapeType) {
		this.model = model;
		this.shape = shape;
		this.log = log;
		this.shapeType = shapeType;
	}

	/**
	 * Get index of shape, remove it from that place, add it to the first place and add that command to the log.
	 */
	@Override
	public void execute() {
		index =  model.getIndexOfShape(shape);
		model.removeShapeAtIndex(index);
		model.addShapeToIndex(0, shape);
		command = "Bring to back " + shapeType + ": " + shape.toString();
		log.addElement(command);
	}

	/**
	 * Remove shape from first place, return it to the previous (original) position, and remove command from the log.
	 */
	@Override
	public void unexecute() {
		model.removeShapeAtIndex(0);
		model.addShapeToIndex(index, shape);
		log.removeElement(command);
	}
}