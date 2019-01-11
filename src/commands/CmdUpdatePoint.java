package commands;

import javax.swing.DefaultListModel;
import shapes.Point;

/**
 * Class that represent command for update existing point from the draw.
 */
public class CmdUpdatePoint implements Command {
	private Point oldState;
	private Point newState;
	private Point originalState;
	private DefaultListModel<String> log;
	private String command;
	
	public CmdUpdatePoint(Point oldState, Point newState, DefaultListModel<String> log) {
		this.oldState = oldState;
		this.newState = newState;
		this.log = log;
		originalState = oldState.clone();
		command = "Updated->" + oldState.toString() + "->" + newState.toString();
	}
	
	/**
	 * Update point and add that command to the log.
	 */
	@Override
	public void execute() {
		oldState.moveTo(newState.getXcoordinate(), newState.getYcoordinate());
		oldState.setColor(newState.getColor());
		log.addElement(command);
	}

	/**
	 * Undo previous updating, return point to the original values and remove command from the log.
	 */
	@Override
	public void unexecute() {
		oldState.setXcoordinate(originalState.getXcoordinate());
		oldState.setYcoordinate(originalState.getYcoordinate());
		oldState.setColor(originalState.getColor());
		log.removeElement(command);
	}
}