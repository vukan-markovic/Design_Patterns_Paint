package commands;

import shapes.Square;

/**
 * Class that represent command for update existing square from the draw.
 */
public class CmdUpdateSquare implements Command {
	private Square oldState;
	private Square newState;
	private Square originalState;
	
	public CmdUpdateSquare(Square oldState, Square newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	/**
	 * Update square and add that command to the log.
	 */
	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setUpLeft(newState.getUpLeft().clone());
		oldState.setSide(newState.getSide());
		oldState.setColor(newState.getColor());
		oldState.setInteriorColor(newState.getInteriorColor());
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
	}
}