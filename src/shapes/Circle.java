package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class that represent circle shape.
 */
public class Circle extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Point center;
    private int radius;

    public Circle() {}

    public Circle(Point center, int r, Color edgeColor, Color interiorColor) {
        this.center = center;
        this.radius = r;
        setColor(edgeColor);
        setInteriorColor(interiorColor);
    }
    
    /**
     * Draw circle.
     * 
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, 2 * radius, 2 * radius);
        fillUpShape(g);
        if (isSelected()) selected(g);
    }
    
    /**
     * Determine if two circles are equal by center and radius.
     */
    @Override
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle castedObj = (Circle) obj;
			return center.equals(castedObj.getCenter()) && radius == castedObj.getRadius();
		}
		return false;
	}
    
    /**
     * Compare two circles by radius length.
     */
    @Override
	public int compareTo(Shape o) {
		if (o instanceof Circle) return radius - ((Circle) o).getRadius();
		return 0;
	}
    
    /**
     * Print cicle values.
     */
    @Override
    public String toString() {
    	return "Circle: radius=" + radius + "; x=" + center.getXcoordinate() + "; y=" + center.getYcoordinate() + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInteriorColor().toString().substring(14).replace('=', '-');
    }
    
    /**
     * Move center of circle to forwarded coordinates.
     */
    public void moveTo(int xCoordinate, int yCoordinate) {
        center.moveTo(xCoordinate, yCoordinate);
    }

    /**
     * Select circle.
     * 
     * @param g
     */
    public void selected(Graphics g) {
        new Line(new Point(center.getXcoordinate(), center.getYcoordinate() - radius), new Point(center.getXcoordinate(), center.getYcoordinate() + radius)).selected(g);
        new Line(new Point(center.getXcoordinate() - radius, center.getYcoordinate()), new Point(center.getXcoordinate() + radius, center.getYcoordinate())).selected(g);
    }
    
    /**
     * Determine if circle contains click.
     * 
     * @param xCoordinate Represent x coordinate of user click.
     * @param yCoordinate Represent y coordinate of user click.
     * @return Boolean which indicate if circle contains click.
     */
    public boolean containsClick(int xCoordinate, int yCoordinate) {
        if (new Point(xCoordinate, yCoordinate).distance(getCenter()) <= radius)
            return true;
        return false;
    }

    /**
     * Create new instance of this circle.
     */
    public Circle clone() {
    	return new Circle(center.clone(), radius, getColor(), getInteriorColor());
    }
    
    /**
     * Fill up circle.
     * 
     * @param g
     */
    public void fillUpShape(Graphics g) {
        g.setColor(getInteriorColor());
        g.fillOval(center.getXcoordinate() - radius + 1, center.getYcoordinate() - radius + 1, 2 * radius - 2, 2 * radius - 2);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int r) {
        radius = r;
    }
}