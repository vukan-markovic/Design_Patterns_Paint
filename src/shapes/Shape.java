package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Abstract class which all shapes extend. Allows that other elements of application are not depend on all shapes individually but on this class.
 */
public abstract class Shape implements Movable, Cloneable, Serializable, Comparable<Shape> {
	private static final long serialVersionUID = 1L;
	private boolean selected;
    private Color color = Color.BLACK;

    public Shape() {}

    /**
     * Abstract method that must implements all shapes to draw itself.
     * 
     * @param graphics
     */
    public abstract void draw(Graphics graphics);

    /**
     * Abstract method that must implements all shapes to select itself.
     * 
     * @param graphics
     */
    public abstract void selected(Graphics graphics);

    /**
     * Abstract method that must implements all shapes to determine if is selected.
     * 
     * @param xCoordinate Represent x coordinate of user click.
     * @param yCoordinate Represent y coordinate of user click.
     * @return Represent Boolean that indicate if shape contains click.
     */
    public abstract boolean containsClick(int xCoordinate, int yCoordinate);

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}