package controller;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
import javax.imageio.ImageIO;
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
import strategy.FileManager;
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
	private Stack<Command> commands;
	private Stack<Command> undoCommands;
	private FileManager fileManager;
	private DefaultListModel<String> log;
	private JFileChooser chooser;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		initialPointOfLine = null;
		propertyChangeSupport = new PropertyChangeSupport(this);
		commands = new Stack<Command>();
		undoCommands = new Stack<Command>();
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
		Point p = new Point(click.getX(), click.getY(), edgeColor);
		CmdAddShape cmdAddShape = new CmdAddShape(p, model, log, "point");
		cmdAddShape.execute();
		if (model.getAll().size() == 1) propertyChangeSupport.firePropertyChange("shape exist", false, true);
		frame.getView().repaint();
		commands.add(cmdAddShape);
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
			Line l = new Line(initialPointOfLine, new Point(click.getX(), click.getY()), edgeColor);
			CmdAddShape cmdAddShape = new CmdAddShape(l, model, log, "line");
			cmdAddShape.execute();
			if (model.getAll().size() == 1) propertyChangeSupport.firePropertyChange("shape exist", false, true);
			initialPointOfLine = null;
			frame.getView().repaint();
			commands.add(cmdAddShape);
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
			if (square.getWidth() + click.getX() > frame.getView().getWidth() || square.getWidth() + click.getY() > frame.getView().getHeight() || click.getY() - square.getWidth() <= 0 || click.getX() - square.getWidth() < 0) {
				JOptionPane.showMessageDialog(null, "The square goes out of drawing!");
			} else {
				Square s = new Square(new Point(click.getX(), click.getY()), square.getSideLength(), edgeColor, interiorColor);
				CmdAddShape cmdAddShape = new CmdAddShape(s, model, log, "square");
				cmdAddShape.execute();
				if (model.getAll().size() == 1) propertyChangeSupport.firePropertyChange("shape exist", false, true);
				frame.getView().repaint();
				commands.add(cmdAddShape);
			}
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
			if (rectangle.getWidth() + click.getX() > frame.getView().getWidth() || rectangle.getHeight() + click.getY() > frame.getView().getHeight() || click.getY() - rectangle.getHeight() <= 0 || click.getX() - rectangle.getWidth() < 0) {
				JOptionPane.showMessageDialog(null, "The rectangle goes out of drawing!");
			} else {
				Rectangle r = new Rectangle(new Point(click.getX(), click.getY()), rectangle.getRectangleWidth(), rectangle.getRectangleHeight(), edgeColor, interiorColor);
				CmdAddShape cmdAddShape = new CmdAddShape(r, model, log, "rectangle");
				cmdAddShape.execute();
				if (model.getAll().size() == 1) propertyChangeSupport.firePropertyChange("shape exist", false, true);
				frame.getView().repaint();
				commands.add(cmdAddShape);
			}
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
			if (circle.getRadiusLength() + click.getX() > frame.getView().getWidth() || circle.getRadiusLength() + click.getY() > frame.getView().getHeight() || click.getY() - circle.getRadiusLength() <= 0 || click.getX() - circle.getRadiusLength() < 0) {
				JOptionPane.showMessageDialog(null, "The circle goes out of drawing!");
			} else {
				Circle c = new Circle(new Point(click.getX(), click.getY()), circle.getRadiusLength(), edgeColor, interiorColor);
				CmdAddShape cmdAddShape = new CmdAddShape(c, model, log, "circle");
				cmdAddShape.execute();
				if (model.getAll().size() == 1) propertyChangeSupport.firePropertyChange("shape exist", false, true);
				frame.getView().repaint();
				commands.add(cmdAddShape);
			}
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
			if (dlgHexagon.getRadiusLength() + click.getX() > frame.getView().getWidth() || dlgHexagon.getRadiusLength() + click.getY() > frame.getView().getHeight() || click.getY() - dlgHexagon.getRadiusLength() <= 0 || click.getX() - dlgHexagon.getRadiusLength() < 0) {
				JOptionPane.showMessageDialog(null, "The hexagon goes out of drawing!");
			} else {
				Hexagon hexagon = new Hexagon(click.getX(), click.getY(), dlgHexagon.getRadiusLength());
				hexagon.setBorderColor(edgeColor);
				hexagon.setAreaColor(interiorColor);
				HexagonAdapter h = new HexagonAdapter(hexagon);
				CmdAddShape cmdAddShape = new CmdAddShape(h, model, log, "hexagon");
				cmdAddShape.execute();
				if (model.getAll().size() == 1) propertyChangeSupport.firePropertyChange("shape exist", false, true);
				frame.getView().repaint();		
				commands.add(cmdAddShape);
			}
		}
	}

	public void btnSelectShapeClicked(MouseEvent click) {
		if(model.getAll().isEmpty()) {
			propertyChangeSupport.firePropertyChange("shape don't exist", false, true);
			JOptionPane.showMessageDialog(null, "There is no shapes for selection!", "Notification", JOptionPane.INFORMATION_MESSAGE);
		}
		
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
		else if (counter > 1) propertyChangeSupport.firePropertyChange("update turn off", false, true);
		
		if (!selected) unselect();
		else selected = false;
		frame.getView().repaint();	
	}
	
	public void unselect() {
		Iterator<Shape> shapesIterator = model.getAll().iterator();
		propertyChangeSupport.firePropertyChange("shape unselected", false, true);
		
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
		if(point.isConfirmed()) {
			Point pointForModification = (Point) shape;
			Point p = new Point(point.getXcoordinate(), point.getYcoordinate(), point.getColor());
			CmdUpdatePoint cmdUpdatePoint = new CmdUpdatePoint(pointForModification, p, log);
			cmdUpdatePoint.execute();
			frame.getView().repaint();
			commands.add(cmdUpdatePoint);
		}
	}
	
	public void btnUpdateLineClicked(Shape shape) {
		DlgLine line = new DlgLine();
		line.write((Line) shape);
		line.setVisible(true);
		if(line.isConfirmed()) {
			Line lineForModification = (Line) shape;
			Line l = new Line(new Point(line.getXcoordinateInitial(), line.getYcoordinateInitial()), new Point(line.getXcoordinateLast(), line.getYcoordinateLast()), line.getColor());
			CmdUpdateLine cmdUpdateLine = new CmdUpdateLine(lineForModification, l, log);
			cmdUpdateLine.execute();
			frame.getView().repaint();
			commands.add(cmdUpdateLine);
		}	
	}
	
	public void btnUpdateRectangleClicked(Shape shape) {
		DlgRectangle rectangle = new DlgRectangle();
		rectangle.fillUp((Rectangle) shape);
		rectangle.setVisible(true);
		if(rectangle.isConfirmed()) {
			if (rectangle.getWidth() + rectangle.getX() > frame.getView().getWidth() || rectangle.getHeight() + rectangle.getY() > frame.getView().getHeight() || rectangle.getY() - rectangle.getHeight() <= 0 || rectangle.getX() - rectangle.getWidth() < 0) {
				JOptionPane.showMessageDialog(null, "The rectangle goes out of drawing!");
			} else {
				Rectangle rectangleForModification = (Rectangle) shape;
				Rectangle r = new Rectangle(new Point(rectangle.getXcoordinate(), rectangle.getYcoordinate()), rectangle.getRectangleWidth(), rectangle.getRectangleHeight(), rectangle.getEdgeColor(), rectangle.getInteriorColor());
				CmdUpdateRectangle cmdUpdateRectangle = new CmdUpdateRectangle(rectangleForModification, r, log);
				cmdUpdateRectangle.execute();
				frame.getView().repaint();
				commands.add(cmdUpdateRectangle);
			}
		}
	}

	public void btnUpdateSquareClicked(Shape shape) {
		DlgSquare square = new DlgSquare();
		square.fillUp((Square) shape);
		square.setVisible(true);
		if(square.isConfirmed()) {
			if (square.getWidth() + square.getX() > frame.getView().getWidth() || square.getWidth() + square.getY() > frame.getView().getHeight() || square.getY() - square.getWidth() <= 0 || square.getX() - square.getWidth() < 0) {
				JOptionPane.showMessageDialog(null, "The square goes out of drawing!");
			} else {
				Square squareForModification = (Square) shape;
				Square s = new Square(new Point(square.getXcoordinate(), square.getYcoordinate()), square.getSideLength(), square.getEdgeColor(), square.getInteriorColor());
				CmdUpdateSquare cmdUpdateSquare = new CmdUpdateSquare(squareForModification, s, log);
				cmdUpdateSquare.execute();
				frame.getView().repaint();
				commands.add(cmdUpdateSquare);
			}
		}
	}
	
	public void btnUpdateCircleClicked(Shape shape) {
		DlgCircle circle = new DlgCircle();
		circle.fillUp((Circle) shape);
		circle.setVisible(true);
		if(circle.isConfirmed()) {
			if (circle.getRadiusLength() + circle.getX() > frame.getView().getWidth() || circle.getRadiusLength() + circle.getY() > frame.getView().getHeight() || circle.getY() - circle.getRadiusLength() <= 0 || circle.getX() - circle.getRadiusLength() < 0) {
				JOptionPane.showMessageDialog(null, "The circle goes out of drawing!");
			} else {
				Circle circleForModification = (Circle) shape;
				Circle c = new Circle(new Point(circle.getXcoordinateOfCenter(), circle.getYcoordinateOfCenter()), circle.getRadiusLength(), circle.getEdgeColor(), circle.getInteriorColor());
				CmdUpdateCircle cmdUpdateCircle = new CmdUpdateCircle(circleForModification, c, log);
				cmdUpdateCircle.execute();
				frame.getView().repaint();
				commands.add(cmdUpdateCircle);
			}
		}
	}
	
	public void btnUpdateHexagonClicked(Shape shape) {
		DlgHexagon hexagon = new DlgHexagon();
		hexagon.fillUp((HexagonAdapter) shape);
		hexagon.setVisible(true);
		if (hexagon.isConfirmed()) {
			if (hexagon.getRadiusLength() + hexagon.getX() > frame.getView().getWidth() || hexagon.getRadiusLength() + hexagon.getY() > frame.getView().getHeight() || hexagon.getY() - hexagon.getRadiusLength() <= 0 || hexagon.getX() - hexagon.getRadiusLength() < 0) {
				JOptionPane.showMessageDialog(null, "The hexagon goes out of drawing!");
			} else {
				HexagonAdapter hexagonForModification = (HexagonAdapter) shape;
				Hexagon hex = new Hexagon(hexagon.getXcoordinate(), hexagon.getYcoordinate(), hexagon.getRadiusLength());
				hex.setAreaColor(hexagon.getInteriorColor());
				hex.setBorderColor(hexagon.getEdgeColor());
				HexagonAdapter h = new HexagonAdapter(hex);
				CmdUpdateHexagon cmdUpdateHexagon = new CmdUpdateHexagon(hexagonForModification, h, log);
				cmdUpdateHexagon.execute();
				frame.getView().repaint();
				commands.add(cmdUpdateHexagon);
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
			
			CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(shapesForDeletion, model, log);
			cmdRemoveShape.execute();
			if (model.getAll().isEmpty()) propertyChangeSupport.firePropertyChange("shape don't exist", false, true);
			frame.getView().repaint();
			commands.add(cmdRemoveShape);
		}
	}
	
	public void undo() {
		commands.peek().unexecute();
		undoCommands.push(commands.pop());
		propertyChangeSupport.firePropertyChange("redo turn on", false, true);
		frame.getView().repaint();
	}
	
	public void redo() {		
		undoCommands.peek().execute();
		commands.push(undoCommands.pop());
		if (undoCommands.isEmpty()) propertyChangeSupport.firePropertyChange("redo turn off", false, true);
		frame.getView().repaint();
	}
	
	public void save(FileManager fileManager) {
		this.fileManager = fileManager;
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
		chooser.enableInputMethods(false);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setDialogTitle("Save draw");
		chooser.setFileFilter(new FileNameExtensionFilter(".ser", "ser"));
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) fileManager.save(chooser.getSelectedFile());
	}

	public void saveDrawAsImage() {
		 BufferedImage imagebuffer = null;
		    try {
		        imagebuffer = new Robot().createScreenCapture(frame.getView().getBounds());
		    } catch (AWTException e1) {
		        e1.printStackTrace();
		    }  
		    Graphics2D graphics2D = imagebuffer.createGraphics();
		     frame.getView().paint(graphics2D);
		     try {
		        ImageIO.write(imagebuffer,"jpeg", new File("draw_" + Calendar.getInstance().getTimeInMillis() + ".jpeg"));
		    } catch (Exception e) {
		        System.out.println("error");
		    }
	}
	
	public void open() {
		chooser.enableInputMethods(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			model.removeAll();
			fileManager.open(chooser.getSelectedFile());
			frame.getView().repaint();
		}	
	}

	public void newDraw() {
		model.removeAll();
		frame.getView().repaint();
	}

	public void toFront() {
		Shape shape = getSelectedShape();
		CmdToFront cmdToFront = new CmdToFront(model, shape, log, getShapeType(shape));
		cmdToFront.execute();
		commands.add(cmdToFront);
		frame.getView().repaint();
	}

	public void bringToFront() {
		Shape shape = getSelectedShape();
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, shape, log, getShapeType(shape));
		cmdBringToFront.execute();
		commands.add(cmdBringToFront);
		frame.getView().repaint();
	}

	public void ToBack() {
		Shape shape = getSelectedShape();
		CmdToBack cmdToBack = new CmdToBack(model, shape, log, getShapeType(shape));
		cmdToBack.execute();
		commands.add(cmdToBack);
		frame.getView().repaint();
	}

	public void BringToBack() {
		Shape shape = getSelectedShape();
		CmdBringToBack cmdBringToBack = new CmdBringToBack(model, shape, log, getShapeType(shape));
		cmdBringToBack.execute();
		commands.add(cmdBringToBack);
		frame.getView().repaint();	
	}
	
	public String getShapeType(Shape shape) {
		if (shape instanceof Point) return "point";
		else if (shape instanceof Line) return "line";
		else if (shape instanceof Rectangle) return "rectangle";
		else if (shape instanceof Square) return "square";
		else if (shape instanceof Circle) return "circle";
		else return "hexagon";
	}

	public void updateShapeClicked() {
		Shape shape = getSelectedShape();
		switch(getShapeType(shape)) {
			case "point":
				btnUpdatePointClicked(shape);
				break;
			case "square":
				btnUpdateSquareClicked(shape);
				break;
			case "hexagon":
				btnUpdateHexagonClicked(shape);
				break;
			case "circle":
				btnUpdateCircleClicked(shape);
				break;
			case "line":
				btnUpdateLineClicked(shape);
				break;
			case "rectangle":
				btnUpdateRectangleClicked(shape);
				break;
		}
	}
}