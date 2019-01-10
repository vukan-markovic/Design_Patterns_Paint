package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import commands.Command;
import shapes.Shape;

public class DrawingModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes;
	private Stack<Command> commands;
	private Stack<Command> undoCommands;
	
	public DrawingModel() {
		shapes = new ArrayList<Shape>();
		commands = new Stack<Command>();
		undoCommands = new Stack<Command>();
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

	public void addCommand(Command command) {
		commands.push(command);
	}
	
	public void addUndoCommand(Command command) {
		undoCommands.add(command);
	}
	
	public Command removeCommand() {
		return commands.pop();
	}
	
	public Command removeUndoCommand() {
		return undoCommands.pop();
	}

	public Command getLastCommand() {
		return commands.peek();
	}
	
	public Command getLastUndoCommand() {
		return undoCommands.peek();
	}
	
	public Stack<Command> getUndoCommands() {
		return undoCommands;
	}
	
	public Stack<Command> getCommands() {
		return commands;
	}
}