package adapter;

import java.awt.Color;
import java.awt.Graphics;
import hexagon.Hexagon;
import shapes.Shape;
import shapes.SurfaceShape;

/**
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
	 * Determine if two hexagons are equal depend on their x and y coordinates and radius length.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			Hexagon hexaFromObj = ((HexagonAdapter) obj).hexagon;
			return hexagon.getX() == hexaFromObj.getX() && hexagon.getY() == hexaFromObj.getY() && hexagon.getR() == hexaFromObj.getR();
		}
		return false;
	}
	
	/**
	 * Compares two hexagons depend on their radius.
	 */
	@Override
	public int compareTo(Shape hex) {
		if (hex instanceof HexagonAdapter) return hexagon.getR() - ((HexagonAdapter) hex).getR();
		return 0;
	}
	
	/**
	 * Print hexagon values.
	 */
	@Override
	public String toString() {
		return "Hexagon: radius=" + hexagon.getR() + "; x=" + hexagon.getX() + "; y=" + hexagon.getY() + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInteriorColor().toString().substring(14).replace('=', '-');
	}
	
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
	 * Make new instance of this class.
	 * 
	 * @return HexagonAdapter New instance of this class.
	 */
	public HexagonAdapter clone() {
		Hexagon h = new Hexagon(getXcoordinate(), getYcoordinate(), getR());
		h.setBorderColor(getColor());
		h.setAreaColor(getInteriorColor());
		return new HexagonAdapter(hexagon);
	}

	/**
	 * Implemented in {@link #draw(Graphics)}.
	 * 
	 * @param Graphics {@docRoot#Graphics}
	 */
	@Override
	public void fillUpShape(Graphics shapeForFillUp) {}
	
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public void setColor(Color color) {
		super.setColor(color);
		hexagon.setBorderColor(color);
	}
	
	public Color getInteriorColor() {
		return hexagon.getAreaColor();
	}
	
	public void setInteriorColor(Color color) {
		super.setInteriorColor(color);
		hexagon.setAreaColor(color);
	}
	
	public int getR() {
		return hexagon.getR();
	}
	
	public void setR(int r) {
		hexagon.setR(r);
	}
	
	public int getXcoordinate() {
		return hexagon.getX();
	}
	
	public int getYcoordinate() {
		return hexagon.getY();
	}
}