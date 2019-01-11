package commands;

import adapter.HexagonAdapter;

/**
 * Class that represent command for update existing hexagon from the draw.
 */
public class CmdUpdateHexagon implements Command {
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalState;
	
	public CmdUpdateHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
		this.oldState = oldState;
		this.newState = newState;
		originalState = oldState.clone();
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
	}
}