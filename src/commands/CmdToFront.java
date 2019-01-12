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
	 * Get index of shape, remove it from that position and add it to position at index greater by one.
	 */
	@Override
	public void execute() {
		index =  model.getIndexOf(shape);
		model.removeAtIndex(index);
		model.addToIndex(index + 1, shape);
	}

	/**
	 * Get index of shape, remove it from previous position and return it to old position.
	 */
	@Override
	public void unexecute() {
		model.removeAtIndex(index + 1);
		model.addToIndex(index, shape);
	}
}