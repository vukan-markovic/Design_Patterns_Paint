package view;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import model.DrawingModel;
import shapes.Shape;
import shapes.SurfaceShape;

public class DrawingView extends JPanel {
	private static final long serialVersionUID = 1L;
	private DrawingModel model;
	
	public DrawingView() {}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public DrawingModel getModel() {
		return model;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			Iterator<Shape> it = model.getAll().iterator();

			while (it.hasNext()) {
				Shape shapeForDraw = it.next();
				if (shapeForDraw instanceof SurfaceShape) ((SurfaceShape) shapeForDraw).fillUpShape(g);
				shapeForDraw.draw(g);
			}
		}
	}
}