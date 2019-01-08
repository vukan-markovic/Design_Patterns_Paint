package commands;

import javax.swing.DefaultListModel;

import model.DrawingModel;
import shapes.Shape;

/**
 * @author Vukan Marković
 *
 * Class that represent command for add new shape to the draw.
 */
public class CmdAddShape implements Command {
	private Shape shape;
	private DrawingModel model;
	private DefaultListModel<String> log;
	private String command;
	
	public CmdAddShape(Shape shape, DrawingModel model, DefaultListModel<String> log) {
		this.shape = shape;
		this.model = model;
		this.log = log;
		command = "Added " + shape.toString();
	}
	
	/**
	 * Add new shape to the draw and add that command to the log.
	 */
	@Override
	public void execute() {
		model.add(shape);
		log.addElement(command);
	}

	/**
	 * Remove previous added shape from the draw and add that command to the log.
	 */
	@Override
	public void unexecute() {	
		model.remove(shape);
		log.removeElement(command);
	}
}