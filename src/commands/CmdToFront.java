package commands;

import model.DrawingModel;
import shapes.Shape;

/**
 * Class that represent command for bring some shape one position to the front.
 */
public class CmdToFront implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;

	public CmdToFront(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	/**
	 * Get index of shape, remove it from that position, add it to position at index greater by one and add command to the log.
	 */
	@Override
	public void execute() {
		index =  model.getIndexOfShape(shape);
		model.removeShapeAtIndex(index);
		model.addShapeToIndex(index + 1, shape);
	}

	/**
	 * Get index of shape, remove it from previous position, return it to old position and remove command from the log.
	 */
	@Override
	public void unexecute() {
		model.removeShapeAtIndex(index + 1);
		model.addShapeToIndex(index, shape);
	}
}