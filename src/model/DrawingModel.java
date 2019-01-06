package model;

import java.io.Serializable;
import java.util.ArrayList;

import shapes.Shape;

public class DrawingModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes;
	
	public DrawingModel() {
		shapes = new ArrayList<Shape>();
	}
	
	public void add(Shape shape) {
		shapes.add(shape);
	}
	
	public ArrayList<Shape> getAll() {
		return shapes;
	}
	
	public void addShapeToIndex(int index, Shape shape) {
		shapes.add(index, shape);
	}
	
	public Shape getShapeByIndex(int index) {
		return shapes.get(index);
	}
	
	public void remove(Shape shape) {
		shapes.remove(shape);
	}
	
	public void removeMultiple(ArrayList<Shape> shapes) {
		this.shapes.removeAll(shapes);
	}
	
	public void removeShapeAtIndex(int index) {
		shapes.remove(index);
	}

	public void addMultiple(ArrayList<Shape> list) {
		shapes.addAll(list);
	}
	
	public void removeAll() {
		shapes.removeAll(shapes);
	}

	public int getIndexOfShape(Shape selectedShape) {
		return shapes.indexOf(selectedShape);
	}

	public void addToIndex(Shape selectedShape, int index) {
		shapes.add(index, selectedShape);
	}
}