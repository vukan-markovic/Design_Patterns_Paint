package commands;

import model.DrawingModel;
import shapes.Shape;

/**
 * Class that represent command for add new shape to the draw.
 */
public class CmdAddShape implements Command {
	private Shape shape;
	private DrawingModel model;
	
	public CmdAddShape(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	/**
	 * Add new shape to the draw.
	 */
	@Override
	public void execute() {
		model.add(shape);
	}

	/**
	 * Remove previous added shape to the draw.
	 */
	@Override
	public void unexecute() {	
		model.remove(shape);
	}
}