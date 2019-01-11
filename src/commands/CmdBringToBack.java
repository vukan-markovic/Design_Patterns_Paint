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
	 * Get index of shape, remove it from that place, add it to the first place and add that command to the log.
	 */
	@Override
	public void execute() {
		index =  model.getIndexOfShape(shape);
		model.removeShapeAtIndex(index);
		model.addShapeToIndex(0, shape);
	}

	/**
	 * Remove shape from first place, return it to the previous (original) position, and remove command from the log.
	 */
	@Override
	public void unexecute() {
		model.removeShapeAtIndex(0);
		model.addShapeToIndex(index, shape);
	}
}