package commands;

import javax.swing.DefaultListModel;

import model.DrawingModel;
import shapes.Shape;

/**
 * @author Vukan Marković
 *
 * Class that represent command for bring some shape one position to the front.
 */
public class CmdToFront implements Command {
	private DrawingModel model;
	private Shape shape;
	private DefaultListModel<String> log;
	private int index;
	private String command;
	private String shapeType;

	public CmdToFront(DrawingModel model, Shape shape, DefaultListModel<String> log, String shapeType) {
		this.model = model;
		this.shape = shape;
		this.log = log;
		this.shapeType = shapeType;
	}

	/**
	 * Get index of shape, remove it from that position, add it to position at index greater by one and add command to the log.
	 */
	@Override
	public void execute() {
		index =  model.getIndexOfShape(shape);
		model.removeShapeAtIndex(index);
		model.addShapeToIndex(index + 1, shape);
		command = "To front " + shapeType + "-> " + shape.toString();
		log.addElement(command);
	}

	/**
	 * Get index of shape, remove it from previous position, return it to old position and remove command from the log.
	 */
	@Override
	public void unexecute() {
		model.removeShapeAtIndex(index + 1);
		model.addShapeToIndex(index, shape);
		log.removeElement(command);
	}
}