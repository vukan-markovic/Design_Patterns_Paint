package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Vukan Markovic
 */
public class Circle extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Point center;
    private int radius;

    /**
     * 
     */
    public Circle() {}

    /**
     * 
     * @param center
     * @param r
     * @param edgeColor
     * @param interiorColor
     */
    public Circle(Point center, int r, Color edgeColor, Color interiorColor) {
        this.center = center;
        this.radius = r;
        setColor(edgeColor);
        setInteriorColor(interiorColor);
    }

    /**
     * @param xCoordinate
     * @param yCoordinate
     */
    public void moveTo(int xCoordinate, int yCoordinate) {
        center.moveTo(xCoordinate, yCoordinate);
    }
    

    /**
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, 2 * radius, 2 * radius);
        if (isSelected())
            selected(g);
    }

    /**
     * @param g
     */
    public void selected(Graphics g) {
        new Line(new Point(center.getXcoordinate(), center.getYcoordinate() - radius), new Point(center.getXcoordinate(), center.getYcoordinate() + radius)).selected(g);
        new Line(new Point(center.getXcoordinate() - radius, center.getYcoordinate()), new Point(center.getXcoordinate() + radius, center.getYcoordinate())).selected(g);
    }

    /**
     * @param g
     */
    public void fillUpShape(Graphics g) {
        g.setColor(getInteriorColor());
        g.fillOval(center.getXcoordinate() - radius + 1, center.getYcoordinate() - radius + 1, 2 * radius - 2, 2 * radius - 2);
    }

    /**
     * @param xCoordinate
     * @param yCoordinate
     * @return
     */
    public boolean containsClick(int xCoordinate, int yCoordinate) {
        if (new Point(xCoordinate, yCoordinate).distance(getCenter()) <= radius)
            return true;
        return false;
    }
    
    public Circle clone() {
    	return new Circle(center, radius, getColor(), getInteriorColor());
    }

    /**
     * @return
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @param center
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * @return
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param r
     */
    public void setRadius(int r) {
        radius = r;
    }
    
    @Override
    public String toString() {
    	return "Radius length: " + radius + ", Center: " + center.toString() + ", edge color: " + getColor().toString().substring(14) + ", area color: " + getInteriorColor().toString().substring(14);
    }
}