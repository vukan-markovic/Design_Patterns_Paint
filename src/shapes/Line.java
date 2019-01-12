package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class that represent line shape.
 */
public class Line extends Shape {
	private static final long serialVersionUID = 1L;
	private Point initial;
    private Point last;

    public Line() {}

    public Line(Point initial, Point last) {
        this.initial = initial;
        this.last = last;
    }

    public Line(Point initial, Point last, Color color) {
        this(initial, last);
        setColor(color);
    }
    
    /**
     * Draw line.
     * 
     * @param graphics
     */
    public void draw(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.drawLine(initial.getXcoordinate(), initial.getYcoordinate(), last.getXcoordinate(), last.getYcoordinate());
        if (isSelected()) selected(graphics);
    }
    
    /**
     * Determine if two lines are equal depend on initial and last point.
     */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line line = (Line) obj;
			return initial.equals(line.initial) && last.equals(line.last);
		}
		return false;
	}
	
    /**
     * Compare two lines depend by length.
     */
	@Override
	public int compareTo(Shape shape) {
		if (shape instanceof Line) return (int) distance() - (int) ((Line) shape).distance();
		return 0;
	}
	
	/**
	 * Print line values.
	 */
	 @Override
	    public String toString() {
	    	return "Line: start point x=" + initial.getXcoordinate() + "; start point y=" + initial.getYcoordinate() + "; end point x=" + last.getXcoordinate() + "; end point y=" + last.getYcoordinate() + "; color=" + getColor().toString().substring(14).replace('=', '-');
	    }

    /**
     * Not implemented.
     */
    public void moveTo(int x, int y) {}
    
    /**
     * Select line.
     * 
     * @param graphics
     */
    public void selected(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        initial.selected(graphics);
        last.selected(graphics);
        centerOfLine().selected(graphics);
    }

    /**
     * Determine if this line contains user click.
     * 
     * @param xCoordinate Represent x coordinate of user click.
     * @param yCoordinate Represent y coordinate of user click.
     * @return Boolean that indicate if this line contain user click.
     */
    public boolean containsClick(int xCoordinate, int yCoordinate) {
        if ((initial.distance(new Point(xCoordinate, yCoordinate)) + last.distance(new Point(xCoordinate, yCoordinate))) - distance() <= 1) return true;
        return false;
    }
    
    /**
     * Create new instance of this line.
     */
    public Line clone() {
		return new Line(initial.clone(), last.clone(), getColor());
	}

    /**
     * Calculate length of line (distance between initial and last point).
     * 
     * @return Represent length of line.
     */
    public double distance() {
        return initial.distance(last);
    }

    /**
     * Return center of line.
     * 
     * @return Center of line.
     */
    public Point centerOfLine() {
        return new Point((initial.getXcoordinate() + last.getXcoordinate()) / 2, (initial.getYcoordinate() + last.getYcoordinate()) / 2);
    }

    public Point getInitial() {
        return initial;
    }

    public void setInitial(Point initial) {
        this.initial = initial;
    }

    public Point getLast() {
        return last;
    }

    public void setLast(Point last) {
        this.last = last;
    }
}