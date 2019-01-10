package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 */
public class Square extends SurfaceShape implements Comparable<Square> {
	private static final long serialVersionUID = 1L;
	protected Point upLeft;
    protected int side;

    /**
     * 
     */
    public Square() {}

    /**
     * 
     * @param upLeft
     * @param side
     */
    public Square(Point upLeft, int side) {
        this.upLeft = upLeft;
        this.side = side;
    }

    /**
     * 
     * @param upLeft
     * @param side
     * @param edgeColor
     * @param interiorColor
     */
    public Square(Point upLeft, int side, Color edgeColor, Color interiorColor) {
        this(upLeft, side);
        setColor(edgeColor);
        setInteriorColor(interiorColor);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Square: x=" + upLeft.getXcoordinate() + "; y=" + upLeft.getYcoordinate() + "; side=" + side + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInteriorColor().toString().substring(14).replace('=', '-');
    }
    
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Square) {
			Square castedObj = (Square) obj;
			return upLeft.equals(castedObj.upLeft) && side == castedObj.side;
		}
		return false;
	}

    /**
     * @return
     */
    public double surface() {
        return side * side;
    }

    /**
     * @param x
     * @param y
     */
    public void moveTo(int x, int y) {
        upLeft.moveTo(x, y);
    }

    /**
     * @return
     */
    public Line diagonal() {
        return new Line(upLeft, new Point(upLeft.getXcoordinate() + side, upLeft.getYcoordinate() + side));
    }

    /**
     * @param graphics
     */
    public void draw(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.drawRect(upLeft.getXcoordinate(), upLeft.getYcoordinate(), side, side);
        if (isSelected()) selected(graphics);
    }

    /**
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
     * @param graphics
     */
    public void fillUpShape(Graphics graphics) {
        graphics.setColor(getInteriorColor());
        graphics.fillRect(upLeft.getXcoordinate() + 1, upLeft.getYcoordinate() + 1, side - 1, side - 1);
    }

    /**
     * @param x
     * @param y
     * @return
     */
    public boolean containsClick(int x, int y) {
        if (this.getUpLeft().getXcoordinate() <= x && x <= (this.getUpLeft().getXcoordinate() + side) && this.getUpLeft().getYcoordinate() <= y && y <= (this.getUpLeft().getYcoordinate() + side)) return true;
        return false;
    }

    /**
     * @param o
     * @return
     */
    public int compareTo(Square square) {
        if (square instanceof Square) return (int) (this.surface() - ((Square) square).surface());
        else return 0;
    }
    
    public Square clone() {
    	return new Square(upLeft.clone(), side, getColor(), getInteriorColor());
    }

    /**
     * @return
     */
    public Point getUpLeft() {
        return upLeft;
    }

    /**
     * @param upLeft
     */
    public void setUpLeft(Point upLeft) {
        this.upLeft = upLeft;
    }

    /**
     * @return
     */
    public int getSide() {
        return side;
    }

    /**
     * @param side
     */
    public void setSide(int side) {
        this.side = side;
    }
}