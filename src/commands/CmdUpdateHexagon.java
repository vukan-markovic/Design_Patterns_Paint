package commands;

import javax.swing.DefaultListModel;

import adapter.HexagonAdapter;

/**
 * @author Vukan Marković
 *
 * Class that represent command for update existing hexagon from the draw.
 */
public class CmdUpdateHexagon implements Command {
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalState;
	private DefaultListModel<String> log;
	private String command;
	
	public CmdUpdateHexagon(HexagonAdapter oldState, HexagonAdapter newState, DefaultListModel<String> log) {
		this.oldState = oldState;
		this.newState = newState;
		this.log = log;
		originalState = oldState.clone();
		command = "Updated->" + oldState.toString() + "->" + newState.toString();
	}
	
	/**
	 * Update hexagon and add that command to the log.
	 */
	@Override
	public void execute() {
		oldState.moveTo(newState.getXcoordinate(), newState.getYcoordinate());
		oldState.setColor(newState.getColor());
		oldState.setInteriorColor(newState.getInteriorColor());
		oldState.setR(newState.getR());
		log.addElement(command);
	}

	/**
	 * Undo previous updating, return hexagon to the original values and remove command from the log.
	 */
	@Override
	public void unexecute() {
		oldState.moveTo(originalState.getXcoordinate(), newState.getYcoordinate());
		oldState.setColor(originalState.getColor());
		oldState.setInteriorColor(originalState.getInteriorColor());
		oldState.setR(originalState.getR());
		log.removeElement(command);
	}
}