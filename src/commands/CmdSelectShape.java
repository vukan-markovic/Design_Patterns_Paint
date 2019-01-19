package commands;

import shapes.Shape;

/**
 * Class that represent command to set shape to selected or unselected state.
 */
public class CmdSelectShape implements Command {
	private Shape shape;
	private boolean selectedState;
	
	public CmdSelectShape(Shape shape, boolean selectedState) {
		this.shape = shape;
		this.selectedState = selectedState;
	}
	
	/**
	 * Set shape selected state to true or false.
	 */
	@Override
	public void execute() {
		shape.setSelected(selectedState);
	}

	/**
	 * Undo previous selection/unselection.
	 */
	@Override
	public void unexecute() {	
		if (shape.isSelected()) shape.setSelected(false);
		else shape.setSelected(true);
	}
}