package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class that represent rectangle shape.
 */
public class Rectangle extends Square {
	private static final long serialVersionUID = 1L;
	private int width;

    public Rectangle() {}

    public Rectangle(Point upLeft, int width, int height, Color edgeColor, Color interiorColor) {
        this.upLeft = upLeft;
        this.width = width;
        super.side = height;
        setColor(edgeColor);
        setInteriorColor(interiorColor);
    }
    
    /**
     * Draw rectangle.
     * 
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawRect(upLeft.getXcoordinate(), upLeft.getYcoordinate(), side, width);
        fillUpShape(g);
        if (isSelected()) selected(g);
    }
    
    /**
     * Determine if two rectangles are equal depend on their up left point and width.
     */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle castedObj = (Rectangle) obj;
			return upLeft.equals(castedObj.upLeft) && width == castedObj.getWidth() && side == castedObj.side;
		}
		return false;
	}
	
	/**
	 * Print rectangle values.
	 */
    @Override
    public String toString() {
    	return "Rectangle: x=" + upLeft.getXcoordinate() + "; y=" + upLeft.getYcoordinate() + "; height=" + side + "; width=" + width + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInteriorColor().toString().substring(14).replace('=', '-');
    }

    /**
     * Select rectangle.
     * 
     * @param graphics
     */
    @Override
    public void selected(Graphics graphics) {
    	graphics.setColor(Color.BLUE);
        new Line(getUpLeft(), new Point(upLeft.getXcoordinate() + super.side, upLeft.getYcoordinate())).selected(graphics);
        new Line(getUpLeft(), new Point(upLeft.getXcoordinate(), upLeft.getYcoordinate() + width)).selected(graphics);
        new Line(new Point(getUpLeft().getXcoordinate() + super.side, upLeft.getYcoordinate()), diagonal().getLast()).selected(graphics);
        new Line(new Point(upLeft.getXcoordinate(), upLeft.getYcoordinate() + width), diagonal().getLast()).selected(graphics);
    }
    
    /**
     * @param xCoordinate Represent x coordinate of user click.
     * @param yCoordinate Represent y coordinate of user click.
     * @return Boolean indicating if rectangle coints click.
     */
    @Override
    public boolean containsClick(int xCoordinate, int yCoordinate) {
        if (upLeft.getXcoordinate() <= xCoordinate && xCoordinate <= (upLeft.getXcoordinate() + width) && upLeft.getYcoordinate() <= yCoordinate && yCoordinate <= upLeft.getYcoordinate() + side) return true;
        return false;
    }

    /**
     * Create new instance of this rectangle.
     */
    public Rectangle clone() {
    	return new Rectangle(upLeft.clone(), width, side, getColor(), getInteriorColor());
    }

    /**
     * Fill up rectangle.
     * 
     * @param graphics
     */
    @Override
    public void fillUpShape(Graphics graphics) {
        graphics.setColor(getInteriorColor());
        graphics.fillRect(upLeft.getXcoordinate() + 1, upLeft.getYcoordinate() + 1, super.side - 1, width - 1);
    }
    
    /**
     * Calculate diagonal of rectangle.
     * 
     * @return Line which represent diagonal of rectangle.
     */
    @Override
    public Line diagonal() {
        return new Line(upLeft, new Point(upLeft.getXcoordinate() + side, upLeft.getYcoordinate() + width));
    }
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}