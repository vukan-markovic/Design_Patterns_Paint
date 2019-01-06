package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 */
public class Line extends Shape {
	private static final long serialVersionUID = 1L;
	private Point initial;
    private Point last;

    /**
     * 
     */
    public Line() {}

    /**
     * 
     * @param initial
     * @param last
     */
    public Line(Point initial, Point last) {
        this.initial = initial;
        this.last = last;
    }

    /**
     * 
     * @param initial
     * @param last
     * @param color
     */
    public Line(Point initial, Point last, Color color) {
        this(initial, last);
        setColor(color);
    }

    /**
     * @param x
     * @param y
     */
    public void moveTo(int x, int y) {
    }

    /**
     * @return
     */
    public double distance() {
        return initial.distance(last);
    }

    /**
     * @return
     */
    public Point centerOfLine() {
        return new Point((initial.getXcoordinate() + last.getXcoordinate()) / 2, (initial.getYcoordinate() + last.getYcoordinate()) / 2);
    }

    /**
     * @param graphics
     */
    public void draw(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.drawLine(initial.getXcoordinate(), initial.getYcoordinate(), last.getXcoordinate(), last.getYcoordinate());
        if (isSelected()) selected(graphics);
    }

    /**
     * @param graphics
     */
    public void selected(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        initial.selected(graphics);
        last.selected(graphics);
        centerOfLine().selected(graphics);
    }

    /**
     * @param xCoordinate
     * @param yCoordinate
     * @return
     */
    public boolean containsClick(int xCoordinate, int yCoordinate) {
        if ((initial.distance(new Point(xCoordinate, yCoordinate)) + last.distance(new Point(xCoordinate, yCoordinate))) - distance() <= 1) return true;
        return false;
    }
    
    public Line clone() {
		return new Line(new Point(this.getInitial().getXcoordinate(), this.getInitial().getYcoordinate(), this.getInitial().getColor()), new Point(this.getLast().getXcoordinate(), this.getLast().getYcoordinate(), this.getLast().getColor()), getColor());
	}

    /**
     * @return
     */
    public Point getInitial() {
        return initial;
    }

    /**
     * @param initial
     */
    public void setInitial(Point initial) {
        this.initial = initial;
    }

    /**
     * @return
     */
    public Point getLast() {
        return last;
    }

    /**
     * @param last
     */
    public void setLast(Point last) {
        this.last = last;
    }
    
    @Override
    public String toString() {
    	return "Initial point: " + initial.toString() + ", Last point: " + last.toString() + ", color: " + getColor().toString().substring(14);
    }
}