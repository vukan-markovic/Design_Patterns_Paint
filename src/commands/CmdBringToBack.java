package commands;

import model.DrawingModel;
import shapes.Shape;

/**
 * Class that represent command to bring some shape to the back.
 */
public class CmdBringToBack implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	public CmdBringToBack(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	/**
	 * Get index of shape, remove it from that place and add it to the first place.
	 */
	@Override
	public void execute() {
		index =  model.getIndexOf(shape);
		model.removeAtIndex(index);
		model.addToIndex(0, shape);
	}

	/**
	 * Remove shape from first place and return it to the previous (original) position.
	 */
	@Override
	public void unexecute() {
		model.removeAtIndex(0);
		model.addToIndex(index, shape);
	}
}