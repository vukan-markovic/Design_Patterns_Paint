package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import adapter.HexagonAdapter;
import commands.CmdAddShape;
import commands.CmdBringToBack;
import commands.CmdBringToFront;
import commands.CmdRemoveShape;
import commands.CmdToBack;
import commands.CmdToFront;
import commands.CmdUpdateCircle;
import commands.CmdUpdateHexagon;
import commands.CmdUpdateLine;
import commands.CmdUpdatePoint;
import commands.CmdUpdateRectangle;
import commands.CmdUpdateSquare;
import commands.Command;
import dialogs.DlgCircle;
import dialogs.DlgHexagon;
import dialogs.DlgLine;
import dialogs.DlgPoint;
import dialogs.DlgRectangle;
import dialogs.DlgSquare;
import frame.DrawingFrame;
import hexagon.Hexagon;
import model.DrawingModel;
import shapes.Circle;
import shapes.Square;
import strategy.FileDraw;
import strategy.FileLog;
import strategy.FileManager;
import strategy.FilePicture;
import view.DrawingView;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

/**
 * @author Vukan Marković
 * 
 * Class that represent controller in MVC architectural pattern. 
 * Called by the {@link DrawingView} when user click something and act depending on the command (usually update {@link DrawingModel}).
 */
public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Point initialPointOfLine;
	private Color edgeColor = Color.BLACK;
	private Color interiorColor = Color.WHITE;
	private Color currentEdgeColor = Color.BLACK;
	private Color currentInteriorColor = Color.WHITE;
	private PropertyChangeSupport propertyChangeSupport;
	private int counter = 0;
	private boolean selected;
	private FileManager fileManager;
	private DefaultListModel<String> log;
	private JFileChooser chooser;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		initialPointOfLine = null;
		propertyChangeSupport = new PropertyChangeSupport(this);
		chooser = new JFileChooser();
		log = frame.getList();
	}
	
	/**
	 * Add listener that will listen (observe) to the changes in this class.
	 * 
	 * @param propertyChangeListener Represent {@link DrawingView} that listen to the changes.
	 */
	public void addPropertyChangedListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}
	
	/**
	 * User clicked to choose edge color, show {@inheritDoc JColorChooser}.
	 * 
	 * @return Color that user choose.
	 */
	public Color btnEdgeColorClicked() {
		edgeColor = JColorChooser.showDialog(null, "Colors pallete", currentEdgeColor);
		currentEdgeColor = edgeColor;
		return currentEdgeColor;
	}
	
	/**
	 * User clicked to choose area color, show {@inheritDoc JColorChooser}.
	 * 
	 * @return Color that user choose.
	 */
	public Color btnInteriorColorClicked() {
		interiorColor = JColorChooser.showDialog(null, "Colors pallete", currentInteriorColor);
		currentInteriorColor = interiorColor;
		return currentInteriorColor;
	}
	
	/**
	 * Called when user click to add new {@link Point}. 
	 * Create {@link CmdAddShape}, add {@link Point} to that command and execute it.
	 * Fire change that now shape exist, repaint {@link DrawingView} and add command to the list of commands.
	 * 
	 * @param click Represent place where user clicked.
	 */
	public void btnAddPointClicked(MouseEvent click) {
		unselect();
		executeCommand(new CmdAddShape(new Point(click.getX(), click.getY(), edgeColor), model, log));
	}
	
	/**
	 * Called when user click to add new {@link Line}. 
	 * Create {@link CmdAddShape}, add {@link Line} to that command and execute it.
	 * Fire change that now shape exist, repaint {@link DrawingView} and add command to the list of commands.
	 * 
	 * @param click Represent place where user clicked.
	 */
	public void btnAddLineClicked(MouseEvent click) {
		unselect();
		if(initialPointOfLine == null) initialPointOfLine = new Point(click.getX(), click.getY(), edgeColor);
		else {
			executeCommand(new CmdAddShape(new Line(initialPointOfLine, new Point(click.getX(), click.getY()), edgeColor), model, log));
			initialPointOfLine = null;
		}
	}
	
	/**
	 * Called when user click to add new {@link Square}. 
	 * Create {@link CmdAddShape}, add {@link Square} to that command and execute it, ensuring that {@link Square} don't go out of draw. 
	 * Fire change that now shape exist, repaint {@link DrawingView} and add command to the list of commands.
	 * 
	 * @param click Represent place where user clicked.
	 */
	public void btnAddSquareClicked(MouseEvent click) {
		unselect();
		DlgSquare square = new DlgSquare();
		square.write(click.getX(),click.getY());
		square.deleteButtons();
		square.setVisible(true);
		if (square.isConfirmed()) {
			if (square.getWidth() + click.getX() > frame.getView().getWidth() || square.getWidth() + click.getY() > frame.getView().getHeight() || click.getY() - square.getWidth() <= 0 || click.getX() - square.getWidth() < 0)
				JOptionPane.showMessageDialog(null, "The square goes out of drawing!");
			else 
				executeCommand(new CmdAddShape(new Square(new Point(click.getX(), click.getY()), square.getSideLength(), edgeColor, interiorColor), model, log));
		}
	}
	
	/**
	 * Called when user click to add new {@link Rectangle}. 
	 * Create {@link CmdAddShape}, add {@link Rectangle} to that command and execute it, ensuring that {@link Rectangle} don't go out of draw. 
	 * Fire change that now shape exist, repaint {@link DrawingView} and 
	 * add command to the list of commands.
	 * 
	 * @param click Represent place where user clicked.
	 */
	public void btnAddRectangleClicked(MouseEvent click) {
		unselect();
		DlgRectangle rectangle = new DlgRectangle();
		rectangle.write(click.getX(), click.getY());
		rectangle.deleteButtons();
		rectangle.setVisible(true);
		if (rectangle.isConfirmed()) {
			if (rectangle.getWidth() + click.getX() > frame.getView().getWidth() || rectangle.getHeight() + click.getY() > frame.getView().getHeight() || click.getY() - rectangle.getHeight() <= 0 || click.getX() - rectangle.getWidth() < 0)
				JOptionPane.showMessageDialog(null, "The rectangle goes out of drawing!");
			else
				executeCommand(new CmdAddShape(new Rectangle(new Point(click.getX(), click.getY()), rectangle.getRectangleWidth(), rectangle.getRectangleHeight(), edgeColor, interiorColor), model, log));
		}
	}
	
	/**
	 * Called when user click to add new {@link Circle}. 
	 * Create {@link CmdAddShape}, add {@link Circle} to that command and execute it, ensuring that {@link Circle} don't go out of draw. 
	 * Fire change that now shape exist, repaint {@link DrawingView} and 
	 * add command to the list of commands.
	 * 
	 * @param click Represent place where user clicked.
	 */
	public void btnAddCircleClicked(MouseEvent click) {
		unselect();
		DlgCircle circle = new DlgCircle();
		circle.write(click.getX(), click.getY());
		circle.deleteButtons();
		circle.setVisible(true);
		if (circle.isConfirmed()) {
			if (circle.getRadiusLength() + click.getX() > frame.getView().getWidth() || circle.getRadiusLength() + click.getY() > frame.getView().getHeight() || click.getY() - circle.getRadiusLength() <= 0 || click.getX() - circle.getRadiusLength() < 0)
				JOptionPane.showMessageDialog(null, "The circle goes out of drawing!");
			else 
				executeCommand(new CmdAddShape(new Circle(new Point(click.getX(), click.getY()), circle.getRadiusLength(), edgeColor, interiorColor), model, log));
		}
	}
	
	/**
	 * Called when user click to add new {@link Hexagon}. 
	 * Create {@link CmdAddShape}, add {@link Hexagon} to that command and execute it, ensuring that {@link Hexagon} don't go out of draw. 
	 * Fire change that now shape exist, repaint {@link DrawingView} and 
	 * add command to the list of commands.
	 * 
	 * @param click Represent place where user clicked.
	 */
	public void btnAddHexagonClicked(MouseEvent click) {
		unselect();
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.write(click.getX(), click.getY());
		dlgHexagon.deleteButtons();
		dlgHexagon.setVisible(true);
		if (dlgHexagon.isConfirmed()) {
			if (dlgHexagon.getRadiusLength() + click.getX() > frame.getView().getWidth() || dlgHexagon.getRadiusLength() + click.getY() > frame.getView().getHeight() || click.getY() - dlgHexagon.getRadiusLength() <= 0 || click.getX() - dlgHexagon.getRadiusLength() < 0)
				JOptionPane.showMessageDialog(null, "The hexagon goes out of drawing!");
			else {
				Hexagon hexagon = new Hexagon(click.getX(), click.getY(), dlgHexagon.getRadiusLength());
				hexagon.setBorderColor(edgeColor);
				hexagon.setAreaColor(interiorColor);
				executeCommand(new CmdAddShape(new HexagonAdapter(hexagon), model, log));
			}
		}
	}

	public void btnSelectShapeClicked(MouseEvent click) {
		Iterator <Shape> iterator = model.getAll().iterator();	
		ArrayList<Integer> tempListOfShapes = new ArrayList<>();
		
		while(iterator.hasNext()) {
			Shape shapeForSelection = iterator.next();
			if(shapeForSelection.containsClick(click.getX(), click.getY())) tempListOfShapes.add(model.getIndexOfShape(shapeForSelection));
		}
		
		if (!tempListOfShapes.isEmpty()) {
			Shape shape = model.getShapeByIndex(Collections.max(tempListOfShapes));

			if (!shape.isSelected()) {
				++counter;
				shape.setSelected(true);
			}
			else {
				--counter;
				shape.setSelected(false);
			}
			selected = true;
		}
		
		if (counter == 1)  {
			propertyChangeSupport.firePropertyChange("update turn on", false, true);
			propertyChangeSupport.firePropertyChange("shape selected", false, true);
		}  
		else if (counter > 1) {
			propertyChangeSupport.firePropertyChange("update turn off", false, true);
			propertyChangeSupport.firePropertyChange("change position turn off", false, true);
		}
		if (counter == 1 && model.getAll().size() >= 2) propertyChangeSupport.firePropertyChange("change position turn on", false, true);
		if (!selected) unselect();
		else selected = false;
		frame.getView().repaint();	
	}
	
	public void unselect() {
		Iterator<Shape> shapesIterator = model.getAll().iterator();
		propertyChangeSupport.firePropertyChange("shape unselected", false, true);
		propertyChangeSupport.firePropertyChange("change position turn off", false, true);
		
		while(shapesIterator.hasNext()) {
			Shape shapeForSelection = shapesIterator.next();
			if(shapeForSelection.isSelected()) {
				shapeForSelection.setSelected(false);
			}
		}
		
		counter = 0;
	}

	public void btnUpdatePointClicked(Shape shape) {
		DlgPoint point = new DlgPoint();
		point.write((Point) shape);
		point.setVisible(true);
		if(point.isConfirmed()) executeCommand(new CmdUpdatePoint((Point) shape, new Point(point.getXcoordinate(), point.getYcoordinate(), point.getColor()), log));
	}
	
	public void btnUpdateLineClicked(Shape shape) {
		DlgLine line = new DlgLine();
		line.write((Line) shape);
		line.setVisible(true);
		if(line.isConfirmed()) executeCommand(new CmdUpdateLine((Line) shape, new Line(new Point(line.getXcoordinateInitial(), line.getYcoordinateInitial()), new Point(line.getXcoordinateLast(), line.getYcoordinateLast()), line.getColor()), log));
	}
	
	public void btnUpdateRectangleClicked(Shape shape) {
		DlgRectangle rectangle = new DlgRectangle();
		rectangle.fillUp((Rectangle) shape);
		rectangle.setVisible(true);
		if(rectangle.isConfirmed()) {
			if (rectangle.getWidth() + rectangle.getX() > frame.getView().getWidth() || rectangle.getHeight() + rectangle.getY() > frame.getView().getHeight() || rectangle.getY() - rectangle.getHeight() <= 0 || rectangle.getX() - rectangle.getWidth() < 0) JOptionPane.showMessageDialog(null, "The rectangle goes out of drawing!");
			else executeCommand(new CmdUpdateRectangle((Rectangle) shape, new Rectangle(new Point(rectangle.getXcoordinate(), rectangle.getYcoordinate()), rectangle.getRectangleWidth(), rectangle.getRectangleHeight(), rectangle.getEdgeColor(), rectangle.getInteriorColor()), log));
		}
	}

	public void btnUpdateSquareClicked(Shape shape) {
		DlgSquare square = new DlgSquare();
		square.fillUp((Square) shape);
		square.setVisible(true);
		if(square.isConfirmed()) {
			if (square.getWidth() + square.getX() > frame.getView().getWidth() || square.getWidth() + square.getY() > frame.getView().getHeight() || square.getY() - square.getWidth() <= 0 || square.getX() - square.getWidth() < 0) JOptionPane.showMessageDialog(null, "The square goes out of drawing!");
			else executeCommand(new CmdUpdateSquare((Square) shape, new Square(new Point(square.getXcoordinate(), square.getYcoordinate()), square.getSideLength(), square.getEdgeColor(), square.getInteriorColor()), log));
		}
	}
	
	public void btnUpdateCircleClicked(Shape shape) {
		DlgCircle circle = new DlgCircle();
		circle.fillUp((Circle) shape);
		circle.setVisible(true);
		if(circle.isConfirmed()) {
			if (circle.getRadiusLength() + circle.getX() > frame.getView().getWidth() || circle.getRadiusLength() + circle.getY() > frame.getView().getHeight() || circle.getY() - circle.getRadiusLength() <= 0 || circle.getX() - circle.getRadiusLength() < 0) JOptionPane.showMessageDialog(null, "The circle goes out of drawing!");
			else executeCommand(new CmdUpdateCircle((Circle) shape, new Circle(new Point(circle.getXcoordinateOfCenter(), circle.getYcoordinateOfCenter()), circle.getRadiusLength(), circle.getEdgeColor(), circle.getInteriorColor()), log));
		}
	}
	
	public void btnUpdateHexagonClicked(Shape shape) {
		DlgHexagon hexagon = new DlgHexagon();
		hexagon.fillUp((HexagonAdapter) shape);
		hexagon.setVisible(true);
		if (hexagon.isConfirmed()) {
			if (hexagon.getRadiusLength() + hexagon.getX() > frame.getView().getWidth() || hexagon.getRadiusLength() + hexagon.getY() > frame.getView().getHeight() || hexagon.getY() - hexagon.getRadiusLength() <= 0 || hexagon.getX() - hexagon.getRadiusLength() < 0) JOptionPane.showMessageDialog(null, "The hexagon goes out of drawing!");
			else {
				Hexagon hex = new Hexagon(hexagon.getXcoordinate(), hexagon.getYcoordinate(), hexagon.getRadiusLength());
				hex.setAreaColor(hexagon.getInteriorColor());
				hex.setBorderColor(hexagon.getEdgeColor());
				executeCommand(new CmdUpdateHexagon((HexagonAdapter) shape, new HexagonAdapter(hex), log));
			}
		}		
	}
	
	public Shape getSelectedShape() {
		if(!model.getAll().isEmpty()) {
			Iterator<Shape> iterator = model.getAll().iterator();
			while(iterator.hasNext()) {
				Shape shapeForModification = iterator.next();
				if(shapeForModification.isSelected()) return shapeForModification;
			}
		} 	
		
		JOptionPane.showMessageDialog(null, "There is no shapes for modification!", "Notification", JOptionPane.INFORMATION_MESSAGE);
		return null;
	}

	public void btnDeleteShapeClicked() {
		if(JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete selected shape?", "Warning!", JOptionPane.YES_NO_OPTION) == 0) {	
			Iterator<Shape> it = model.getAll().iterator();
			ArrayList<Shape> shapesForDeletion = new ArrayList<Shape>();
			
			while (it.hasNext()) {
				Shape shape = it.next();
				if(shape.isSelected()) shapesForDeletion.add(shape);
			}
			
			executeCommand(new CmdRemoveShape(shapesForDeletion, model, log));
		}
	}
	
	public void executeCommand(Command command) {
		command.execute();
		model.addCommand(command);
	
		if (!model.getUndoCommands().isEmpty()) {
			model.getUndoCommands().removeAllElements();
			propertyChangeSupport.firePropertyChange("redo turn off", false, true);
		}
		
		if (frame.getList().size() == 1) propertyChangeSupport.firePropertyChange("log turn on", false, true);
		
		if (model.getAll().isEmpty()) {
			propertyChangeSupport.firePropertyChange("shape don't exist", false, true);
		} else if (model.getAll().size() == 1) 
			propertyChangeSupport.firePropertyChange("shape exist", false, true);
		
		if (model.getAll().isEmpty() && model.getCommands().isEmpty()) propertyChangeSupport.firePropertyChange("draw is empty", false, true);
		else propertyChangeSupport.firePropertyChange("draw is not empty", false, true);
		frame.getView().repaint();
	}
	
	public void undo() {
		model.getCommands().peek().unexecute();
		model.getUndoCommands().push(model.getCommands().pop());
		if (frame.getList().isEmpty()) propertyChangeSupport.firePropertyChange("log turn off", false, true);
		if (model.getUndoCommands().size() == 1) propertyChangeSupport.firePropertyChange("redo turn on", false, true);
		if (model.getCommands().isEmpty()) propertyChangeSupport.firePropertyChange("undo turn off", false, true);
		frame.getView().repaint();
	}
	
	public void redo() {		
		model.getUndoCommands().peek().execute();
		model.getCommands().push(model.getUndoCommands().pop());
		if (model.getUndoCommands().isEmpty()) propertyChangeSupport.firePropertyChange("redo turn off", false, true);
		if (model.getCommands().size() == 1) propertyChangeSupport.firePropertyChange("shape exist", false, true);
		if (!model.getCommands().isEmpty()) propertyChangeSupport.firePropertyChange("draw is not empty", false, true);
		frame.getView().repaint();
	}
	
	public void save() {
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
		chooser.enableInputMethods(false);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setDialogTitle("Save");
		chooser.setAcceptAllFileFilterUsed(false);
		if (!model.getAll().isEmpty()) {
			chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
			chooser.setFileFilter(new FileNameExtensionFilter("Picture", "jpeg"));
		}
		if (!model.getCommands().isEmpty()) chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) fileManager = new FileManager(new FileDraw(model));
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) fileManager = new FileManager(new FileLog(frame));
			else fileManager = new FileManager(new FilePicture(frame));
			fileManager.save(chooser.getSelectedFile());
		}
	}

	public void open() {
		chooser.enableInputMethods(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "ser"));
		chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			model.removeAll();
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) {
				fileManager = new FileManager(new FileDraw(model));
				frame.getView().repaint();
			}
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) fileManager = new FileManager(new FileLog(frame));
			fileManager.open(chooser.getSelectedFile());
		}	
	}

	public void newDraw() {
		model.removeAll();
		frame.getList().removeAllElements();
		frame.getView().repaint();
	}

	public void toFront() {
		Shape shape = getSelectedShape();
		if (model.getIndexOfShape(shape) == model.getAll().size() - 1) JOptionPane.showMessageDialog(null, "Shape is on top!");
		else executeCommand(new CmdToFront(model, shape, log));
	}

	public void bringToFront() {
		Shape shape = getSelectedShape();
		if (model.getIndexOfShape(shape) == model.getAll().size() - 1) JOptionPane.showMessageDialog(null, "Shape is already on top!");
		else executeCommand(new CmdBringToFront(model, shape, log));
	}

	public void toBack() {
		Shape shape = getSelectedShape();
		if (model.getIndexOfShape(shape) == 0) JOptionPane.showMessageDialog(null, "Shape is on first place!");
		else executeCommand(new CmdToBack(model, shape, log));
	}

	public void bringToBack() {
		Shape shape = getSelectedShape();
		if (model.getIndexOfShape(shape) == 0) JOptionPane.showMessageDialog(null, "Shape is already on first place!");
		else executeCommand(new CmdBringToBack(model, shape, log));
	}
	
	public void updateShapeClicked() {
		Shape shape = getSelectedShape();
		if (shape instanceof Point) btnUpdatePointClicked(shape);
		else if (shape instanceof Line) btnUpdateLineClicked(shape);
		else if (shape instanceof Rectangle) btnUpdateRectangleClicked(shape);
		else if (shape instanceof Square) btnUpdateSquareClicked(shape);
		else if (shape instanceof Circle) btnUpdateCircleClicked(shape);
		else btnUpdateHexagonClicked(shape);
	}
}