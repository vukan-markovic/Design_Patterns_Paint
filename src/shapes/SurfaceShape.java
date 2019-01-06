package shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 */
public abstract class SurfaceShape extends Shape {
	private static final long serialVersionUID = 1L;
	private Color interiorColor = Color.WHITE;

    /**
     * @param shapeForFillUp
     */
    public abstract void fillUpShape(Graphics shapeForFillUp);

    /**
     * @return
     */
    public Color getInteriorColor() {
        return interiorColor;
    }

    /**
     * @param interiorColor
     */
    public void setInteriorColor(Color interiorColor) {
        this.interiorColor = interiorColor;
    }
}