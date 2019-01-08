package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 *
 *
 */
public class Rectangle extends Square {
	private static final long serialVersionUID = 1L;
	private int width;

    /**
     * 
     */
    public Rectangle() {}

    /**
     * 
     * @param upLeft
     * @param width
     * @param height
     * @param edgeColor
     * @param interiorColor
     */
    public Rectangle(Point upLeft, int width, int height, Color edgeColor, Color interiorColor) {
        this.upLeft = upLeft;
        this.width = width;
        super.side = height;
        setColor(edgeColor);
        setInteriorColor(interiorColor);
    }

    /**
     * @return
     */
    @Override
    public Line diagonal() {
        return new Line(upLeft, new Point(upLeft.getXcoordinate() + side, upLeft.getYcoordinate() + width));
    }

    /**
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawRect(upLeft.getXcoordinate(), upLeft.getYcoordinate(), side, width);
        if (isSelected()) selected(g);
    }

    /**
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
     * @param graphics
     */
    @Override
    public void fillUpShape(Graphics graphics) {
        graphics.setColor(getInteriorColor());
        graphics.fillRect(upLeft.getXcoordinate() + 1, upLeft.getYcoordinate() + 1, super.side - 1, width - 1);
    }

    /**
     * @param xCoordinate
     * @param yCoordinate
     * @return
     */
    @Override
    public boolean containsClick(int xCoordinate, int yCoordinate) {
        if (upLeft.getXcoordinate() <= xCoordinate && xCoordinate <= (upLeft.getXcoordinate() + width) && upLeft.getYcoordinate() <= yCoordinate && yCoordinate <= upLeft.getYcoordinate() + side)
            return true;
        return false;
    }

    public Rectangle clone() {
    	return new Rectangle(upLeft.clone(), width, side, getColor(), getInteriorColor());
    }
    
    /**
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    @Override
    public String toString() {
    	return "Rectangle: up left point = " + upLeft + ", height = " + side + ", width = " + width + ", edge color = " + getColor().toString().substring(14) + ", area color = " + getInteriorColor().toString().substring(14);
    }
}