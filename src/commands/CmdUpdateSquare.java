package commands;

import javax.swing.DefaultListModel;

import shapes.Square;

/**
 * @author Vukan Marković
 *
 * Class that represent command for update existing square from the draw.
 */
public class CmdUpdateSquare implements Command {
	private Square oldState;
	private Square newState;
	private Square originalState;
	private DefaultListModel<String> log;
	private String command;
	
	public CmdUpdateSquare(Square oldState, Square newState, DefaultListModel<String> log) {
		this.oldState = oldState;
		this.newState = newState;
		this.log = log;
		originalState = oldState.clone();
		command = "Updated->" + oldState.toString() + "->" + newState.toString();
	}
	
	/**
	 * Update square and add that command to the log.
	 */
	@Override
	public void execute() {
		oldState.setUpLeft(newState.getUpLeft());
		oldState.setSide(newState.getSide());
		oldState.setColor(newState.getColor());
		oldState.setInteriorColor(newState.getInteriorColor());
		log.addElement(command);
	}

	/**
	 * Undo previous updating, return square to the original values and remove command from the log.
	 */
	@Override
	public void unexecute() {
		oldState.setUpLeft(originalState.getUpLeft());
		oldState.setSide(originalState.getSide());
		oldState.setColor(originalState.getColor());
		oldState.setInteriorColor(originalState.getInteriorColor());
		log.removeElement(command);
	}
}