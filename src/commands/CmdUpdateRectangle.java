package commands;

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
	
	public CmdUpdateRectangle(Rectangle oldState, Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	/**
	 * Update rectangle and add that command to the log.
	 */
	@Override
	public void execute() {
		originalState = (Rectangle) oldState.clone();
		oldState.setUpLeft(newState.getUpLeft().clone());
		oldState.setWidth(newState.getWidth());
		oldState.setSide(newState.getSide());
		oldState.setColor(newState.getColor());
		oldState.setInteriorColor(newState.getInteriorColor());
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
	}
}