package commands;

import javax.swing.DefaultListModel;

import shapes.Line;

/**
 * @author Vukan Marković
 *
 * Class that represent command for update existing line from the draw.
 */
public class CmdUpdateLine implements Command {
	private Line oldState;
	private Line newState;
	private Line originalState;
	private DefaultListModel<String> log;
	private String command;
	
	public CmdUpdateLine(Line oldState, Line newState, DefaultListModel<String> log) {
		this.oldState = oldState;
		this.newState = newState;
		this.log = log;
		originalState = oldState.clone();
		command = "Updated line from-> " + oldState.toString() + " to-> " + newState.toString();
	}
	
	/**
	 * Update line and add that command to the log.
	 */
	@Override
	public void execute() {
		oldState.setInitial(newState.getInitial());
		oldState.setLast(newState.getLast());
		oldState.setColor(newState.getColor());
		log.addElement(command);
	}

	/**
	 * Undo previous updating, return line to the original values and remove command from the log.
	 */
	@Override
	public void unexecute() {
		oldState.setInitial(originalState.getInitial());
		oldState.setLast(originalState.getLast());
		oldState.setColor(originalState.getColor());
		log.removeElement(command);
	}
}