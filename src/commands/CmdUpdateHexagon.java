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
	}
	
	/**
	 * Update hexagon.
	 */
	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.moveTo(newState.getXcoordinate(), newState.getYcoordinate());
		oldState.setColor(newState.getColor());
		oldState.setInteriorColor(newState.getInteriorColor());
		oldState.setR(newState.getR());
	}

	/**
	 * Undo previous updating and return hexagon to the original values.
	 */
	@Override
	public void unexecute() {
		oldState.moveTo(originalState.getXcoordinate(), newState.getYcoordinate());
		oldState.setColor(originalState.getColor());
		oldState.setInteriorColor(originalState.getInteriorColor());
		oldState.setR(originalState.getR());
	}
}