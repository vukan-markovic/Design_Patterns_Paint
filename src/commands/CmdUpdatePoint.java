package commands;

import shapes.Point;

/**
 * Class that represent command for update existing point from the draw.
 */
public class CmdUpdatePoint implements Command {
	private Point oldState;
	private Point newState;
	private Point originalState;
	
	public CmdUpdatePoint(Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	/**
	 * Update point.
	 */
	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.moveTo(newState.getXcoordinate(), newState.getYcoordinate());
		oldState.setColor(newState.getColor());
	}

	/**
	 * Undo previous updating and return point to the original values.
	 */
	@Override
	public void unexecute() {
		oldState.moveTo(originalState.getXcoordinate(), originalState.getYcoordinate());
		oldState.setColor(originalState.getColor());
	}
}