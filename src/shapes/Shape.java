package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 */
public abstract class Shape implements Movable, Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private boolean selected;
    private Color color = Color.BLACK;

    /**
     * 
     */
    public Shape() {
    }

    /**
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @param graphics
     */
    public abstract void draw(Graphics graphics);

    /**
     * @param graphics
     */
    public abstract void selected(Graphics graphics);

    /**
     * @param xCoordinate
     * @param yCoordinate
     * @return
     */
    public abstract boolean containsClick(int xCoordinate, int yCoordinate);

    /**
     * @return
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}