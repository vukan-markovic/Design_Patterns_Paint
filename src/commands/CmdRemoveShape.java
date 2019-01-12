package commands;

import java.util.ArrayList;
import model.DrawingModel;
import shapes.Shape;

/**
 * Class that represent command for remove existing shape from the draw. 
 */
public class CmdRemoveShape implements Command {
	private ArrayList<Shape> shapes;
	private Shape shape;
	private DrawingModel model;
	
	public CmdRemoveShape(ArrayList<Shape> shapes, DrawingModel model) {
		this.shapes = shapes;
		this.model = model;
	}
	
	public CmdRemoveShape(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;	
	}

	/**
	 * Remove shape from the draw.
	 */
	@Override
	public void execute() { 
		if (shapes != null) model.removeMultiple(shapes);
		else model.remove(shape);
	}

	/**
	 * Return previous deleted shape to the draw.
	 */
	@Override
	public void unexecute() {
		if (shapes != null) model.addMultiple(shapes);
		else model.add(shape);
	}
	
	/**
	 * Return number of deleted shapes to undo/redo multiple commands from log at once if multiple shapes are deleted at once.
	 * @return
	 */
	public int getSize() {
		return shapes.size();
	}
}