package commands;

import javax.swing.DefaultListModel;
import shapes.Rectangle;

/**
 * @author Vukan Marković
 *
 * Class that represent command for update existing rectangle from the draw.
 */
public class CmdUpdateRectangle implements Command {
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalState;
	private DefaultListModel<String> log;
	private String command;
	
	public CmdUpdateRectangle(Rectangle oldState, Rectangle newState, DefaultListModel<String> log) {
		this.oldState = oldState;
		this.newState = newState;
		this.log = log;
		originalState = (Rectangle) oldState.clone();
		command = "Updated->" + oldState.toString() + "->" + newState.toString();
	}
	
	/**
	 * Update rectangle and add that command to the log.
	 */
	@Override
	public void execute() {
		oldState.setUpLeft(newState.getUpLeft().clone());
		oldState.setWidth(newState.getWidth());
		oldState.setSide(newState.getSide());
		oldState.setColor(newState.getColor());
		oldState.setInteriorColor(newState.getInteriorColor());
		log.addElement(command);
	}

	/**
	 * Undo previous updating, return rectangle to the original values and remove command from the log.
	 */
	@Override
	public void unexecute() {
		oldState.setUpLeft(originalState.getUpLeft());
		oldState.setWidth(originalState.getWidth());
		oldState.setSide(originalState.getSide());
		oldState.setColor(originalState.getColor());
		oldState.setInteriorColor(originalState.getInteriorColor());
		log.removeElement(command);
	}
}