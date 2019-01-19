package commands;

import shapes.Circle;

/**
 * Class that represent command for update existing circle from the draw.
 */
public class CmdUpdateCircle implements Command {
	private Circle oldState;
	private Circle newState;
	private Circle originalState;
	
	public CmdUpdateCircle(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	/**
	 * Update circle.
	 */
	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setRadius(newState.getRadius());
		oldState.setCenter(newState.getCenter().clone());
		oldState.setInteriorColor(newState.getInteriorColor());
		oldState.setColor(newState.getColor());
	}

	/**
	 * Undo previous updating and return circle to the original values.
	 */
	@Override
	public void unexecute() {
		oldState.setRadius(originalState.getRadius());
		oldState.setCenter(originalState.getCenter());
		oldState.setInteriorColor(originalState.getInteriorColor());
		oldState.setColor(originalState.getColor());
	}
}