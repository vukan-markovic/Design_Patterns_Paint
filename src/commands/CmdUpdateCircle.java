package commands;

import javax.swing.DefaultListModel;

import shapes.Circle;

/**
 * @author Vukan Marković
 *
 * Class that represent command for update existing circle from the draw.
 */
public class CmdUpdateCircle implements Command {
	private Circle oldState;
	private Circle newState;
	private Circle originalState;
	private DefaultListModel<String> log;
	private String command;
	
	public CmdUpdateCircle(Circle oldState, Circle newState, DefaultListModel<String> log) {
		this.oldState = oldState;
		this.newState = newState;
		this.log = log;
		originalState = oldState.clone();
		command = "Updated circle from-> " + oldState.toString() + " to-> " + newState.toString();
	}
	
	/**
	 * Update circle and add that command to the log.
	 */
	@Override
	public void execute() {
		oldState.setRadius(newState.getRadius());
		oldState.setCenter(newState.getCenter());
		oldState.setInteriorColor(newState.getInteriorColor());
		oldState.setColor(newState.getColor());
		log.addElement(command);
	}

	/**
	 * Undo previous updating, return circle to the original values and remove command from the log.
	 */
	@Override
	public void unexecute() {
		oldState.setRadius(originalState.getRadius());
		oldState.setCenter(originalState.getCenter());
		oldState.setInteriorColor(originalState.getInteriorColor());
		oldState.setColor(originalState.getColor());
		log.removeElement(command);
	}
}