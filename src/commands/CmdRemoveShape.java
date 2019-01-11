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
	 * Remove shape from the draw and add that command to the log.
	 */
	@Override
	public void execute() { 
		if (shapes != null) model.removeMultiple(shapes);
		else model.remove(shape);
	}

	/**
	 * Return previous deleted shape from the draw and remove command from the log.
	 */
	@Override
	public void unexecute() {
		if (shapes != null) model.addMultiple(shapes);
		else model.add(shape);
	}
}