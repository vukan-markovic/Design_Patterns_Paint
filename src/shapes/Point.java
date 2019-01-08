package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 */
public class Point extends Shape {
	private static final long serialVersionUID = 1L;
	private int xCoordinate;
    private int yCoordinate;

    /**
     * 
     */
    public Point() {}

    /**
     * 
     * @param xCoordinate
     * @param yCoordinate
     */
    public Point(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * 
     * @param xCoordinate
     * @param yCoordinate
     * @param color
     */
    public Point(int xCoordinate, int yCoordinate, Color color) {
        this(xCoordinate, yCoordinate);
        setColor(color);
    }

    /**
     * @param point
     * @return
     */
    public double distance(Point point) {
        return Math.sqrt(Math.pow(xCoordinate - point.xCoordinate, 2) + Math.pow(yCoordinate - point.yCoordinate, 2));
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Point: x = " + xCoordinate + ", y = " + yCoordinate + ", color = " + getColor().toString().substring(14);
    }

    /**
     * @param graphics
     */
    public void draw(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.drawLine(xCoordinate - 2, yCoordinate, xCoordinate + 2, yCoordinate);
        graphics.drawLine(xCoordinate, yCoordinate + 2, xCoordinate, yCoordinate - 2);
        if (isSelected()) selected(graphics);
    }

    /**
     * @param graphics
     */
    public void selected(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.drawRect(xCoordinate - 3, yCoordinate - 3, 6, 6);
    }

    /**
     * @param xCoordinate
     * @param yCoordinate
     * @return
     */
    public boolean containsClick(int xCoordinate, int yCoordinate) {
        if (new Point(xCoordinate, yCoordinate).distance(this) <= 2)
            return true;
        return false;
    }

    /**
     * @param xCoordinate
     * @param yCoordinate
     */
    public void moveTo(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    
    public Point clone() {
    	return new Point(xCoordinate, yCoordinate, getColor());
    }

    /**
     * @return
     */
    public int getXcoordinate() {
        return xCoordinate;
    }

    /**
     * @param xCoordinate
     */
    public void setXcoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * @return
     */
    public int getYcoordinate() {
        return yCoordinate;
    }

    /**
     * @param yCoordinate
     */
    public void setYcoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}