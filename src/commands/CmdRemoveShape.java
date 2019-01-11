package commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import model.DrawingModel;
import shapes.Point;
import shapes.Shape;

/**
 * Class that represent command for remove existing shape from the draw. 
 */
public class CmdRemoveShape implements Command {
	private ArrayList<Shape> shapes;
	private Shape shape;
	private DrawingModel model;
	private DefaultListModel<String> log;
	private List<String> commands;
	
	public CmdRemoveShape(ArrayList<Shape> shapes, DrawingModel model, DefaultListModel<String> log) {
		this.shapes = shapes;
		this.model = model;
		this.log = log;
		commands = new ArrayList<String>();
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) commands.add("Deleted->" + it.next().toString());	
	}
	
	public CmdRemoveShape(Shape shape, DrawingModel model, DefaultListModel<String> log) {
		this.shape = shape;
		this.model = model;
		this.log = log;
		commands = new ArrayList<String>();
		commands.add("Deleted->" + shape.toString());	
	}

	/**
	 * Remove shape from the draw and add that command to the log.
	 */
	@Override
	public void execute() { 
		if (!model.getAll().isEmpty()) {
			if (shapes != null) model.removeMultiple(shapes);
			else model.remove((Point)shape);
		}
		log.addAll(commands);
	}

	/**
	 * Return previous deleted shape from the draw and remove command from the log.
	 */
	@Override
	public void unexecute() {
		if (shapes != null) model.addMultiple(shapes);
		else model.add(shape);
		Iterator<String> it = commands.iterator();
		while (it.hasNext()) log.removeElement(it.next());
	}
}