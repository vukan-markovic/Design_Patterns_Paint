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
 * <h2>Class that represent controller in MVC architectural pattern.</h2>
 * 
 * Called by the {@link DrawingView} when user click something and act depending on the command (usually update {@link DrawingModel}).
 */
public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Point initialPointOfLine;
	private Color edgeColor = Color.BLACK;
	private Color interiorColor = Color.WHITE;
	private Color choosenEdgeColor;
	private Color choosenInteriorColor;
	private PropertyChangeSupport propertyChangeSupport;
	private int counter = 0;
	private boolean selected;
	private FileManager fileManager;
	private DefaultListModel<String> log;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		initialPointOfLine = null;
		propertyChangeSupport = new PropertyChangeSupport(this);
		log = frame.getList();
	}
	
	/**
	 * <h3>Add listener that will listen (observe) to the changes in this class.</h3>
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
		choosenEdgeColor = JColorChooser.showDialog(null, "Colors pallete", edgeColor);
		if (choosenEdgeColor != null) {
			if (choosenEdgeColor.equals(Color.WHITE)) {
				JOptionPane.showMessageDialog(null, "Background is white :D");
				return null;
			}
			edgeColor = choosenEdgeColor;
			return edgeColor;
		}
		return choosenEdgeColor;
	}
	
	/**
	 * <h3>User clicked to choose area color, show {@inheritDoc JColorChooser}.</h3>
	 * 
	 * @return Color that user choose.
	 */
	public Color btnInteriorColorClicked() {
		choosenInteriorColor = JColorChooser.showDialog(null, "Colors pallete", interiorColor);
		if (choosenInteriorColor != null) {
			interiorColor = choosenInteriorColor;
			return interiorColor;
		}
		return choosenEdgeColor;
	}
	
	/**
	 * <h3>Called when user click to add new {@link Point}.</h3>
	 * <p>
	 * Create {@link CmdAddShape}, add {@link Point} to that command and execute it.</br>
	 * Fire change that now shape exist, repaint {@link DrawingView} and add command to the list of commands.
	 * </p>
	 * @param click Represent place where user clicked.
	 */
	public void btnAddPointClicked(MouseEvent click) {
		unselect();
		executeCommand(new CmdAddShape(new Point(click.getX(), click.getY(), edgeColor), model, log));
	}
	
	/**
	 * <h3>Called when user click to add new {@link Line}.</h3>
	 * <p>
	 * Create {@link CmdAddShape}, add {@link Line} to that command and execute it.</br>
	 * Fire change that now shape exist, repaint {@link DrawingView} and add command to the list of commands.
	 * </p>
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
	 * <h3>Called when user click to add new {@link Square}.</h3>
	 * <p>
	 * Create {@link CmdAddShape}, add {@link Square} to that command and execute it, ensuring that {@link Square} don't go out of draw.</br>
	 * Fire change that now shape exist, repaint {@link DrawingView} and add command to the list of commands.
	 * </p>
	 * @param click Represent place where user clicked.
	 */
	public void btnAddSquareClicked(MouseEvent click) {
		unselect();
		DlgSquare square = new DlgSquare();
		square.write(click.getX(),click.getY(), frame.getView().getWidth(), frame.getView().getHeight());
		square.deleteButtons();
		square.setVisible(true);
		if (square.isConfirmed()) executeCommand(new CmdAddShape(new Square(new Point(click.getX(), click.getY()), square.getSideLength(), edgeColor, interiorColor), model, log));
	}
	
	/**
	 * <h3>Called when user click to add new {@link Rectangle}.</h3>
	 * <p>
	 * Create {@link CmdAddShape}, add {@link Rectangle} to that command and execute it, ensuring that {@link Rectangle} don't go out of draw.</br>
	 * Fire change that now shape exist, repaint {@link DrawingView} and 
	 * add command to the list of commands.
	 * </p>
	 * @param click Represent place where user clicked.
	 */
	public void btnAddRectangleClicked(MouseEvent click) {
		unselect();
		DlgRectangle rectangle = new DlgRectangle();
		rectangle.write(click.getX(), click.getY(), frame.getView().getWidth(), frame.getView().getHeight());
		rectangle.deleteButtons();
		rectangle.setVisible(true);
		if (rectangle.isConfirmed()) executeCommand(new CmdAddShape(new Rectangle(new Point(click.getX(), click.getY()), rectangle.getRectangleWidth(), rectangle.getRectangleHeight(), edgeColor, interiorColor), model, log));
	}
	
	/**
	 * <h3>Called when user click to add new {@link Circle}.</h3>
	 * <p>
	 * Create {@link CmdAddShape}, add {@link Circle} to that command and execute it, ensuring that {@link Circle} don't go out of draw.</br>
	 * Fire change that now shape exist, repaint {@link DrawingView} and 
	 * add command to the list of commands.
	 *</p>
	 * @param click Represent place where user clicked.
	 */
	public void btnAddCircleClicked(MouseEvent click) {
		unselect();
		DlgCircle circle = new DlgCircle();
		circle.write(click.getX(), click.getY(), frame.getView().getWidth(), frame.getView().getHeight());
		circle.deleteButtons();
		circle.setVisible(true);
		if (circle.isConfirmed()) executeCommand(new CmdAddShape(new Circle(new Point(click.getX(), click.getY()), circle.getRadiusLength(), edgeColor, interiorColor), model, log));
	}
	
	/**
	 * <h3>Called when user click to add new {@link Hexagon}.</h3>
	 * <p>
	 * Create {@link CmdAddShape}, add {@link Hexagon} to that command and execute it, ensuring that {@link Hexagon} don't go out of draw.</br>
	 * </br>Fire change that now shape exist, repaint {@link DrawingView} and add command to the list of commands.
	 * </p>
	 * @param click Represent place where user clicked.
	 */
	public void btnAddHexagonClicked(MouseEvent click) {
		unselect();
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.write(click.getX(), click.getY(), frame.getView().getWidth(), frame.getView().getHeight());
		dlgHexagon.deleteButtons();
		dlgHexagon.setVisible(true);
		if (dlgHexagon.isConfirmed()) {
			Hexagon hexagon = new Hexagon(click.getX(), click.getY(), dlgHexagon.getRadiusLength());
			hexagon.setBorderColor(edgeColor);
			hexagon.setAreaColor(interiorColor);
			executeCommand(new CmdAddShape(new HexagonAdapter(hexagon), model, log));
		}
	}

	/**
	 * <h3>Called when user select some shape on a draw. </h3>
	 * <p>
	 * Multiple shapes can be selected and deleted.</br>
	 * One selected shape can be modified, deleted, and moved by Z coordinate ({@link CmdBringToBack}, {@link CmdBringToFront}, {@link CmdToBack} and {@link CmdToFront}).</br>
	 * When user click on some part on draw where no shape, all selected shapes are unselected.</br>
	 * When user click on some selected shape only that shape is unselected.</br>
	 * </p>
	 * @param click Represent place where user click.
	 */
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
	
	/**
	 * <h3>Method is called when some shape(s) need to be unselected.</h3>
	 */
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

	/**
	 * <h3>Method is called when user want to update some existing {@link Point} on draw.</h3>
	 * <p>
	 * {@link DlgPoint} is presented for user to enter new values for point.</br>
	 * Then {@link CmdUpdatePoint} is executed if user confirm input.
	 * </p>
	 * @param point Point that user want to update.
	 */
	public void btnUpdatePointClicked(Point point) {
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.write(point, frame.getView().getWidth(), frame.getView().getHeight());
		dlgPoint.setVisible(true);
		if(dlgPoint.isConfirmed()) executeCommand(new CmdUpdatePoint(point, new Point(dlgPoint.getXcoordinate(), dlgPoint.getYcoordinate(), dlgPoint.getColor()), log));
	}
	
	/**
	 * <h3>Method is called when user want to update some existing {@link Line} on draw.</h3>
	 * <p>
	 * {@link DlgLine} is presented for user to enter new values for line.
	 * Then {@link CmdUpdateLine} is executed if user confirm input.
	 * </p>
	 * @param line Line that user want to update.
	 */
	public void btnUpdateLineClicked(Line line) {
		DlgLine dlgLine = new DlgLine();
		dlgLine.write(line);
		dlgLine.setVisible(true);
		if(dlgLine.isConfirmed()) executeCommand(new CmdUpdateLine(line, new Line(new Point(dlgLine.getXcoordinateInitial(), dlgLine.getYcoordinateInitial()), new Point(dlgLine.getXcoordinateLast(), dlgLine.getYcoordinateLast()), dlgLine.getColor()), log));
	}
	
	/**
	 * <h3>Method is called when user want to update some existing {@link Rectangle} on draw.</h3>
	 * <p>
	 * {@link DlgRectangle} is presented for user to enter new values for rectangle.</br>
	 * Then {@link CmdUpdateRectangle} is executed if user confirm input.
	 * </p>
	 * @param rectangle Rectangle that user want to update.
	 */
	public void btnUpdateRectangleClicked(Rectangle rectangle) {
		DlgRectangle dlgRectangle = new DlgRectangle();
		dlgRectangle.fillUp(rectangle, frame.getView().getWidth(), frame.getView().getHeight());
		dlgRectangle.setVisible(true);
		if(dlgRectangle.isConfirmed()) executeCommand(new CmdUpdateRectangle(rectangle, new Rectangle(new Point(dlgRectangle.getXcoordinate(), dlgRectangle.getYcoordinate()), dlgRectangle.getRectangleWidth(), dlgRectangle.getRectangleHeight(), dlgRectangle.getEdgeColor(), dlgRectangle.getInteriorColor()), log));
	}

	/**
	 * <h3>Method is called when user want to update some existing {@link Square} on draw.</h3>
	 * <p>
	 * {@link DlgSquare} is presented for user to enter new values for square.</br>
	 * Then {@link CmdUpdateSquare} is executed if user confirm input.
	 * </p>
	 * @param square Square that user want to update.
	 */
	public void btnUpdateSquareClicked(Square square) {
		DlgSquare dlgSquare = new DlgSquare();
		dlgSquare.fillUp(square, frame.getView().getWidth(), frame.getView().getHeight());
		dlgSquare.setVisible(true);
		if(dlgSquare.isConfirmed()) executeCommand(new CmdUpdateSquare(square, new Square(new Point(dlgSquare.getXcoordinate(), dlgSquare.getYcoordinate()), dlgSquare.getSideLength(), dlgSquare.getEdgeColor(), dlgSquare.getInteriorColor()), log));
	}
	
	/**
	 * <h3>Method is called when user want to update some existing {@link Circle} on a draw.</h3>
	 * <p>
	 * {@link DlgCircle} is presented for user to enter new values for circle.</br>
	 * Then {@link CmdUpdateCircle} is executed if user confirm input.
	 * </p>
	 * @param circle Circle that user want to update.
	 */
	public void btnUpdateCircleClicked(Circle circle) {
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.fillUp(circle, frame.getView().getWidth(), frame.getView().getHeight());
		dlgCircle.setVisible(true);
		if(dlgCircle.isConfirmed()) executeCommand(new CmdUpdateCircle(circle, new Circle(new Point(dlgCircle.getXcoordinateOfCenter(), dlgCircle.getYcoordinateOfCenter()), dlgCircle.getRadiusLength(), dlgCircle.getEdgeColor(), dlgCircle.getInteriorColor()), log));
	}
	
	/**
	 * <h3>Method is called when user want to update some existing {@link HexagonAdapter} on a draw.</h3>
	 * <p>
	 * {@link DlgHexagon} is presented for user to enter new values for hexagon.</br>
	 * Then {@link CmdUpdateHexagon} is executed if user confirm input.
	 * </p>
	 * @param hexagon Hexagon that user want to update.
	 */
	public void btnUpdateHexagonClicked(HexagonAdapter hexagon) {
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.fillUp(hexagon, frame.getView().getWidth(), frame.getView().getHeight());
		dlgHexagon.setVisible(true);
		if (dlgHexagon.isConfirmed()) {
			Hexagon hex = new Hexagon(dlgHexagon.getXcoordinate(), dlgHexagon.getYcoordinate(), dlgHexagon.getRadiusLength());
			hex.setAreaColor(dlgHexagon.getInteriorColor());
			hex.setBorderColor(dlgHexagon.getEdgeColor());
			executeCommand(new CmdUpdateHexagon(hexagon, new HexagonAdapter(hex), log));
		}		
	}
	
	/**
	 * <h3>Method that returns currently selected shape.</h3>
	 * 
	 * @return Shape that is selected.
	 */
	public Shape getSelectedShape() {
		Iterator<Shape> iterator = model.getAll().iterator();
		while(iterator.hasNext()) {
			Shape shapeForModification = iterator.next();
			if(shapeForModification.isSelected()) return shapeForModification;
		}
		return null;
	}

	/**
	 * <h3>Method is called when user want to delete some shape(s).</h3>
	 * 
	 * {@link CmdRemoveShape} is executed.
	 */
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
	
	/**
	 * <h3>Method that execute some command.</h3>
	 * 
	 * Fire changes from {@link DrawingModel} to Observer {@link DrawingFrame} that updates buttons. 
	 * 
	 * @param command Command that need to be executed.
	 */
	public void executeCommand(Command command) {
		command.execute();
		model.addCommand(command);
	
		if (!model.getUndoCommands().isEmpty()) {
			model.getUndoCommands().removeAllElements();
			propertyChangeSupport.firePropertyChange("redo turn off", false, true);
		}
		
		if (model.getAll().isEmpty()) propertyChangeSupport.firePropertyChange("shape don't exist", false, true);
		else if (model.getAll().size() == 1 && (command instanceof CmdAddShape || command instanceof CmdRemoveShape)) propertyChangeSupport.firePropertyChange("shape exist", false, true);
		
		if (model.getCommands().size() == 1) propertyChangeSupport.firePropertyChange("draw is not empty", false, true);
		if (model.getCommands().isEmpty()) propertyChangeSupport.firePropertyChange("draw is empty", false, true);
		frame.getView().repaint();
	}
	
	/**
	 * <h3> Method that unexecute (undo) some command. </h3>
	 * 
	 * Fire changes from {@link DrawingModel} to Observer {@link DrawingFrame} that updates buttons. 
	 * 
	 * @param command Command that need to be unexecuted.
	 */
	public void undo() {
		model.getLastCommand().unexecute();
		model.addUndoCommand(model.removeCommand());
		if (frame.getList().isEmpty()) propertyChangeSupport.firePropertyChange("log turn off", false, true);
		if (model.getUndoCommands().size() == 1) propertyChangeSupport.firePropertyChange("redo turn on", false, true);
		if (model.getCommands().isEmpty()) propertyChangeSupport.firePropertyChange("draw is empty", false, true);
		frame.getView().repaint();
	}
	
	/**
	 * <h3> Method that execute previously unexecuted command. </h3>
	 * 
	 * Fire changes from {@link DrawingModel} to Observer {@link DrawingFrame} that updates buttons. 
	 * <br>
	 * @param command Command that need to be executed again.
	 */
	public void redo() {		
		model.getLastUndoCommand().execute();
		model.addCommand((model.removeUndoCommand()));
		if (model.getUndoCommands().isEmpty()) propertyChangeSupport.firePropertyChange("redo turn off", false, true);
		if (model.getCommands().size() == 1) {
			propertyChangeSupport.firePropertyChange("shape exist", false, true);
			propertyChangeSupport.firePropertyChange("draw is not empty", false, true);
			propertyChangeSupport.firePropertyChange("log turn on", false, true);
		}
		frame.getView().repaint();
	}
	
	/**
	 * <h3>Method that is obligate for displaying {@ JFileChooser} for user to choose where to save draw as serialized file, log file with executed commands or picture (screenshot) of draw.</h3>
	 * 
	 * Then called appropriate {@link FileManager} to save {@docRoot File}.
	 */ 
	public void save() {
		JFileChooser chooser = new JFileChooser();
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
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) fileManager = new FileManager(new FileLog(frame, model, this));
			else fileManager = new FileManager(new FilePicture(frame));
			fileManager.save(chooser.getSelectedFile());
		}
		chooser.setVisible(false);
	}

	/**
	 * <h3>Method that is obligate for displaying {@ JFileChooser} for user to choose file to open.</h3>
	 * 
	 * Supported formats are serialized file and log file with executed commands.
	 * 
	 * Then called appropriate {@link FileManager} to open {@docRoot File}.
	 */ 
	public void open() {
		JFileChooser chooser = new JFileChooser();
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
			else if (chooser.getFileFilter().getDescription().equals("Commands log")) fileManager = new FileManager(new FileLog(frame, model, this));
			fileManager.open(chooser.getSelectedFile());
			propertyChangeSupport.firePropertyChange("draw is loaded", false, true);
		}	
		chooser.setVisible(false);
	}

	/**
	 * <h3>Method that create new draw if draw is not already empty removing all executed shapes and comands.</h3>
	 */ 
	public void newDraw() {
		if(JOptionPane.showConfirmDialog(null, "Are you sure that you want to start new draw?", "Warning!", JOptionPane.YES_NO_OPTION) == 0) {	
			model.removeAll();
			frame.getList().removeAllElements();
			propertyChangeSupport.firePropertyChange("draw is empty", false, true);
			frame.getView().repaint();
		}
	}

	/**
	 * <h3>Method that call command {@link CmdToFront} which move some shape one position forward in the list of shapes if shape is not already at last position.</h3>
	 */ 
	public void toFront() {
		Shape shape = getSelectedShape();
		if (model.getIndexOfShape(shape) == model.getAll().size() - 1) JOptionPane.showMessageDialog(null, "Shape is on top!");
		else executeCommand(new CmdToFront(model, shape, log));
	}

	/**
	 * <h3>Method that call command {@link CmdBringToFront} which bring some shape at the end of the list of shapes if shape is not already at last position.</h3>
	 */ 
	public void bringToFront() {
		Shape shape = getSelectedShape();
		if (model.getIndexOfShape(shape) == model.getAll().size() - 1) JOptionPane.showMessageDialog(null, "Shape is already on top!");
		else executeCommand(new CmdBringToFront(model, shape, log, model.getAll().size() - 1));
	}

	/**
	 * <h3>Method that call command {@link CmdToBack} which move some shape one position backward in the list of shapes if shape is not already at first position.</h3>
	 */ 
	public void toBack() {
		Shape shape = getSelectedShape();
		if (model.getIndexOfShape(shape) == 0) JOptionPane.showMessageDialog(null, "Shape is on first place!");
		else executeCommand(new CmdToBack(model, shape, log));
	}

	/**
	 * <h3>Method that call command {@link CmdBringToBack} which bring some shape at the beginnig of the list of shapes if shape is not already at first position.</h3>
	 */ 
	public void bringToBack() {
		Shape shape = getSelectedShape();
		if (model.getIndexOfShape(shape) == 0) JOptionPane.showMessageDialog(null, "Shape is already on first place!");
		else executeCommand(new CmdBringToBack(model, shape, log));
	}
	
	/**
	 * <h3>Method that is called when user choose to update some shape.</h3>
	 * 
	 * Determines instance of selected shape and call appropriate method forwarding casted type of shape.
	 */ 
	public void updateShapeClicked() {
		Shape shape = getSelectedShape();
		if (shape instanceof Point) btnUpdatePointClicked((Point) shape);
		else if (shape instanceof Line) btnUpdateLineClicked((Line) shape);
		else if (shape instanceof Rectangle) btnUpdateRectangleClicked((Rectangle) shape);
		else if (shape instanceof Square) btnUpdateSquareClicked((Square) shape);
		else if (shape instanceof Circle) btnUpdateCircleClicked((Circle) shape);
		else btnUpdateHexagonClicked((HexagonAdapter) shape);
	}
}