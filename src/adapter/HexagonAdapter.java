package adapter;

import java.awt.Color;
import java.awt.Graphics;
import hexagon.Hexagon;
import shapes.SurfaceShape;

/**
 * @author Vukan Marković
 *
 * Class that is adapter for hexagon.jar library (Hexagon class).
 */
public class HexagonAdapter extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	/**
	 * Draw hexagon and check if it is selected, to mark and draw selected state too.
	 * 
	 * @param Graphics {@docRoot#Graphics}
	 */
	@Override
	public void draw(Graphics graphics) {
		hexagon.paint(graphics);
		hexagon.setSelected(isSelected());
	}

	/**
	 * Implemented in {@link #draw(Graphics)}.
	 * 
	 * @param Graphics {@docRoot#Graphics}
	 */
	@Override
	public void selected(Graphics graphics) {}

	/**
	 * Check if hexagon is clicked.
	 * 
	 * @param X coordinate of click.
	 * @param Y coordinate of click.
	 * @return boolean Indicate if hexagon contains click.
	 */
	@Override
	public boolean containsClick(int xCoordinate, int yCoordinate) {
		return hexagon.doesContain(xCoordinate, yCoordinate);
	}

	/**
	 * Implemented in {@link #draw(Graphics)}.
	 * 
	 * @param Graphics {@docRoot#Graphics}
	 */
	@Override
	public void fillUpShape(Graphics shapeForFillUp) {}

	/**
	 * Move hexagon to given place.
	 * 
	 * @param x X coordinate of place to move.
	 * @param y Y coordinate of place to move.
	 */
	
	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}
	
	/**
	 * Make new instance of this class.
	 * 
	 * @return HexagonAdapter New instance of this class.
	 */
	public HexagonAdapter clone() {
		return new HexagonAdapter(hexagon);
	}
	
	/**
	 * Check if hexagon is selected.
	 * 
	 * @return boolean Indicate if hexagon is selected.
	 */
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	/**
	 * Set hexagon selected state to given value. 
	 * 
	 * @param selected Boolean that indicate if hexagon is selected or not.
	 */
	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}
	
	@Override
	public String toString() {
		return "Radius length: " + hexagon.getR() + ", X: " + hexagon.getX() + ", Y: " + hexagon.getY() + ", edge color: " + getColor().toString().substring(14) + ", area color: " + getInteriorColor().toString().substring(14);
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
	}
	
	public Color getInteriorColor() {
		return hexagon.getAreaColor();
	}
	
	public void setInteriorColor(Color color) {
		hexagon.setAreaColor(color);
	}
	
	public int getXcoordinate() {
		return hexagon.getX();
	}
	
	public int getYcoordinate() {
		return hexagon.getY();
	}
	
	public int getR() {
		return hexagon.getR();
	}
	
	public void setR(int r) {
		hexagon.setR(r);
	}
}