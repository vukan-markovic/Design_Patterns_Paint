package commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import model.DrawingModel;
import shapes.Shape;

/**
 * @author Vukan Marković
 *
 * Class that represent command for remove existing shape from the draw. 
 */
public class CmdRemoveShape implements Command {
	private ArrayList<Shape> shapes;
	private DrawingModel model;
	private DefaultListModel<String> log;
	private List<String> commands;
	
	public CmdRemoveShape(ArrayList<Shape> shapes, DrawingModel model, DefaultListModel<String> log) {
		this.shapes = shapes;
		this.model = model;
		this.log = log;
		commands = new ArrayList<String>();
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			commands.add("Deleted point-> " + it.next().toString());	
		}
	}

	/**
	 * Remove shape from the draw and add that command to the log.
	 */
	@Override
	public void execute() { 
		if (!model.getAll().isEmpty()) model.removeMultiple(shapes);
		log.addAll(commands);
	}

	/**
	 * Return previous deleted shape from the draw and remove command from the log.
	 */
	@Override
	public void unexecute() {
		model.addMultiple(shapes);
		Iterator<String> it = commands.iterator();
		while (it.hasNext()) log.removeElement(it.next());
	}
}