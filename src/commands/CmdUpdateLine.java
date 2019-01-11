package commands;

import shapes.Line;

/**
 * Class that represent command for update existing line from the draw.
 */
public class CmdUpdateLine implements Command {
	private Line oldState;
	private Line newState;
	private Line originalState;
	
	public CmdUpdateLine(Line oldState, Line newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	/**
	 * Update line and add that command to the log.
	 */
	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setInitial(newState.getInitial().clone());
		oldState.setLast(newState.getLast().clone());
		oldState.setColor(newState.getColor());
	}

	/**
	 * Undo previous updating, return line to the original values and remove command from the log.
	 */
	@Override
	public void unexecute() {
		oldState.setInitial(originalState.getInitial());
		oldState.setLast(originalState.getLast());
		oldState.setColor(originalState.getColor());
	}
}