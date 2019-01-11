package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class that represent square shape.
 */
public class Square extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	protected Point upLeft;
    protected int side;

    public Square() {}

    public Square(Point upLeft, int side) {
        this.upLeft = upLeft;
        this.side = side;
    }

    public Square(Point upLeft, int side, Color edgeColor, Color interiorColor) {
        this(upLeft, side);
        setColor(edgeColor);
        setInteriorColor(interiorColor);
    }
    
    /**
     * Draw square.
     * 
     * @param graphics
     */
    public void draw(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.drawRect(upLeft.getXcoordinate(), upLeft.getYcoordinate(), side, side);
        if (isSelected()) selected(graphics);
    }
    
    /**
     * Check if two squares are equal depend on their up left points and side lengths.
     */
    @Override
	public boolean equals(Object obj) {
		if (obj instanceof Square) {
			Square castedObj = (Square) obj;
			return upLeft.equals(castedObj.upLeft) && side == castedObj.side;
		}
		return false;
	}

    /**
     * Print square values.
     */
    @Override
    public String toString() {
        return "Square: x=" + upLeft.getXcoordinate() + "; y=" + upLeft.getYcoordinate() + "; side=" + side + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInteriorColor().toString().substring(14).replace('=', '-');
    }

    /**
     * Move up left point of square to forwarded coordinates.
     * 
     * @param x Represent x coordinate of place where to move.
     * @param y Represent y coordinate of place where to move.
     */
    public void moveTo(int x, int y) {
        upLeft.moveTo(x, y);
    }
    
    /**
     * Select square.
     * 
     * @param graphics
     */
    public void selected(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        new Line(getUpLeft(), new Point(upLeft.getXcoordinate() + side, upLeft.getYcoordinate())).selected(graphics);
        new Line(upLeft, new Point(upLeft.getXcoordinate(), upLeft.getYcoordinate() + side)).selected(graphics);
        new Line(new Point(upLeft.getXcoordinate() + side, upLeft.getYcoordinate()), diagonal().getLast()).selected(graphics);
        new Line(new Point(upLeft.getXcoordinate(), upLeft.getYcoordinate() + side), diagonal().getLast()).selected(graphics);
    }
    
    /**
     * Check if this square contains user click.
     * 
     * @param x Represent x coordinate of user click.
     * @param y Represent y coordinate of user click.
     * @return Boolean that indicate if this square contains click.
     */
    public boolean containsClick(int x, int y) {
        if (this.getUpLeft().getXcoordinate() <= x && x <= (this.getUpLeft().getXcoordinate() + side) && this.getUpLeft().getYcoordinate() <= y && y <= (this.getUpLeft().getYcoordinate() + side)) return true;
        return false;
    }
    
    /**
     * Create new instance of this square.
     */
    public Square clone() {
    	return new Square(upLeft.clone(), side, getColor(), getInteriorColor());
    }
    
    /**
     * Fill up square.
     * 
     * @param graphics
     */
    public void fillUpShape(Graphics graphics) {
        graphics.setColor(getInteriorColor());
        graphics.fillRect(upLeft.getXcoordinate() + 1, upLeft.getYcoordinate() + 1, side - 1, side - 1);
    }
    
    /**
     * Calculate surface of square.
     * 
     * @return surface of square.
     */
    public double surface() {
        return side * side;
    }

    /**
     * Calculate diagonal of square.
     * 
     * @return Line which represent square diagonal.
     */
    public Line diagonal() {
        return new Line(upLeft, new Point(upLeft.getXcoordinate() + side, upLeft.getYcoordinate() + side));
    }

    public Point getUpLeft() {
        return upLeft;
    }

    public void setUpLeft(Point upLeft) {
        this.upLeft = upLeft;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }
}