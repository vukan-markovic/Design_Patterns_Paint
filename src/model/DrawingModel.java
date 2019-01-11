package model;

import java.io.Serializable;
import java.util.ArrayList;
import shapes.Shape;

/**
 * Represent model in MVC architectural pattern. Contains application data.
 *
 */
public class DrawingModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes;
	
	public DrawingModel() {
		shapes = new ArrayList<Shape>();
	}
	
	/**
	 * Add new shape.
	 * 
	 * @param shape Represent shape which will be added.
	 */
	public void add(Shape shape) {
		shapes.add(shape);
	}
	
	/**
	 * Add new shape to specified index.
	 * @param index Represent index on which shape will be added.
	 * @param shape Represent shape which will be added.
	 */
	public void addShapeToIndex(int index, Shape shape) {
		shapes.add(index, shape);
	}
	
	/**
	 * Remove shapes from list of shapes.
	 * 
	 * @param shape Shape to be removed.
	 */
	public void remove(Shape shape) {
		shapes.remove(shape);
	}
	
	/**
	 * Remove multiple shapes from list.
	 * 
	 * @param shapes Shapes to be removed.
	 */
	public void removeMultiple(ArrayList<Shape> shapes) {
		this.shapes.removeAll(shapes);
	}
	
	/**
	 * Remove shape at specified index.
	 * 
	 * @param index Represent index of shape that will be removed.
	 */
	public void removeShapeAtIndex(int index) {
		shapes.remove(index);
	}

	/**
	 * Add multiple elements to list of shapes.
	 * 
	 * @param list Elements that are be added.
	 */
	public void addMultiple(ArrayList<Shape> list) {
		shapes.addAll(list);
	}
	
	/**
	 * Remove all shapes from list of shapes.
	 */
	public void removeAll() {
		shapes.removeAll(shapes);
	}

	/**
	 * Add shape to specified index.
	 * 
	 * @param selectedShape Shape to be added.
	 * @param index Index where shape will be added.
	 */
	public void addToIndex(Shape selectedShape, int index) {
		shapes.add(index, selectedShape);
	}
	
	public Shape getShapeByIndex(int index) {
		return shapes.get(index);
	}
	
	public ArrayList<Shape> getAll() {
		return shapes;
	}
	
	public int getIndexOfShape(Shape selectedShape) {
		return shapes.indexOf(selectedShape);
	}
}