package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.DefaultListModel;
import adapter.HexagonAdapter;
import commands.CmdAddShape;
import commands.CmdBringToBack;
import commands.CmdBringToFront;
import commands.CmdRemoveShape;
import commands.CmdSelectShape;
import commands.CmdToBack;
import commands.CmdToFront;
import commands.CmdUpdateCircle;
import commands.CmdUpdateHexagon;
import commands.CmdUpdateLine;
import commands.CmdUpdatePoint;
import commands.CmdUpdateRectangle;
import commands.CmdUpdateSquare;
import controller.DrawingController;
import dialogs.DlgLogParser;
import frame.DrawingFrame;
import hexagon.Hexagon;
import model.DrawingModel;
import shapes.Circle;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;

public class FileLog implements FileHandler {
	private BufferedWriter writer;
	private BufferedReader reader;
	private DrawingFrame frame;
	private DrawingModel model;
	private DlgLogParser logParser;
	private DrawingController controller;
	
	public FileLog(DrawingFrame frame, DrawingModel model, DrawingController controller) {
		this.frame = frame;
		this.model = model; 
		this.controller = controller;
	}

	/**
	 * Save forwarded file as log of commands.
	 */
	@Override
	public void save(File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file + ".log"));
			DefaultListModel<String> list = frame.getList();
			for (int i = 0; i < frame.getList().size(); i++) {
				writer.write(list.getElementAt(i));
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open forwarded log file and execute it command by command in interaction with user.
	 */
	@Override
	public void open(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));
			logParser = new DlgLogParser();
			logParser.setFileLog(this);
			logParser.addCommand(reader.readLine());
			logParser.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read one line from log file and parse it.
	 * 
	 * @param command Represent command that need to be parsed.
	 */
	public void readLine(String command) {
		try {
			String[] commands1 = command.split("->");
			switch(commands1[0]) {
				case "Added":
					Shape shape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdAddShape(shape, model));
					frame.getList().addElement("Added->" + shape.toString());
					break;
				case "Updated":
					Shape oldShape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					int index = model.getIndexOfShape(oldShape);
					if (oldShape instanceof Point) {
						Point newPoint = parsePoint(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdatePoint((Point) model.getShapeByIndex(index), newPoint));
						frame.getList().addElement("Updated" + oldShape.toString() + "->" + newPoint.toString());
					}
					else if (oldShape instanceof Line) {
						Line newLine = parseLine(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdateLine((Line) model.getShapeByIndex(index), newLine));
						frame.getList().addElement("Updated" + oldShape.toString() + "->" + newLine.toString());
					}
					else if (oldShape instanceof Rectangle) {
						Rectangle newRectangle = parseRectangle(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdateRectangle((Rectangle) model.getShapeByIndex(index), newRectangle));
						frame.getList().addElement("Updated" + oldShape.toString() + "->" + newRectangle.toString());
					}
					else if (oldShape instanceof Square) {
						Square newSquare = parseSquare(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdateSquare((Square) model.getShapeByIndex(index), newSquare));
						frame.getList().addElement("Updated" + oldShape.toString() + "->" + newSquare.toString());
					}
					else if (oldShape instanceof Circle) {
						Circle newCircle = parseCircle(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdateCircle((Circle) model.getShapeByIndex(index), newCircle));
						frame.getList().addElement("Updated" + oldShape.toString() + "->" + newCircle.toString());
					}
					else if (oldShape instanceof HexagonAdapter) {
						HexagonAdapter newHexagon = parseHexagon(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdateHexagon((HexagonAdapter) model.getShapeByIndex(index), newHexagon));
						frame.getList().addElement("Updated" + oldShape.toString() + "->" + newHexagon.toString());
					}
					break;
				case "Deleted":
					Shape deletedShape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdRemoveShape(deletedShape, model)); 
					frame.getList().addElement("Deleted->" + deletedShape.toString());
					break;
				case "Moved to front":
					Shape shapeMovedToFront = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdToFront(model, shapeMovedToFront));
					frame.getList().addElement("Moved to front->" + shapeMovedToFront.toString());
					break;
				case "Moved to back":
					Shape shapeMovedToBack = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdToBack(model, shapeMovedToBack));
					frame.getList().addElement("Moved to back->" + shapeMovedToBack.toString());
					break;
				case "Bringed to front":
					Shape shapeBringedToFront = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdBringToFront(model, shapeBringedToFront, model.getAll().size() - 1));
					frame.getList().addElement("Bringed to front->" + shapeBringedToFront.toString());
					break;
				case "Bringed to back":
					Shape shapeBringedToBack = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdBringToBack(model, shapeBringedToBack));
					frame.getList().addElement("Bringed to back->" + shapeBringedToBack.toString());
					break;
				case "Selected":
					Shape selectedShape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					int index1 = model.getIndexOfShape(selectedShape);
					controller.executeCommand(new CmdSelectShape(model.getShapeByIndex(index1), true));
					frame.getList().addElement("Selected->" + selectedShape.toString());
					break;
				case "Unselected":
					Shape unselectedShape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					int index2 = model.getIndexOfShape(unselectedShape);
					controller.executeCommand(new CmdSelectShape(model.getShapeByIndex(index2), false));
					frame.getList().addElement("Unselected->" + unselectedShape.toString());
					break;
			}
		
			String line = reader.readLine();
			if (line != null) logParser.addCommand(line);
			else {
				logParser.closeDialog();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Determine which type of shape need to be parsed and call appropriate method.
	 * 
	 * @param shape Represent type of shape which need to be parsed. 
	 * @param shapeParameters Represent values of that shape.
	 * @return Represent parsed shape. 
	 */
	private Shape parseShape(String shape, String shapeParameters) {
		if (shape.equals("Point")) return parsePoint(shapeParameters);
		else if (shape.equals("Hexagon")) return parseHexagon(shapeParameters);
		else if (shape.equals("Line")) return parseLine(shapeParameters);
		else if (shape.equals("Circle")) return parseCircle(shapeParameters);
		else if (shape.equals("Rectangle")) return parseRectangle(shapeParameters);
		else return parseSquare(shapeParameters);
	}

	/**
	 * Method that parse {@link Point} from log file.
	 * 
	 * @param string Represent {@link Point} that need to be parsed.
	 * @return Represent parsed {@link Point}.
	 */
	private Point parsePoint(String string) {
		String [] pointParts = string.split(";"); 		
		String s = pointParts[2].split("=")[1].substring(1, pointParts[2].split("=")[1].length() - 1);
		String [] colors = s.split(",");
		return new Point(Integer.parseInt(pointParts[0].split("=")[1]), Integer.parseInt(pointParts[1].split("=")[1]), new Color(Integer.parseInt(colors[0].split("-")[1]), Integer.parseInt(colors[1].split("-")[1]), Integer.parseInt(colors[2].split("-")[1])));
	}
	
	/**
	 * Method that parse {@link Circle} from log file.
	 * 
	 * @param string Represent {@link Circle} that need to be parsed.
	 * @return Represent parsed {@link Circle}.
	 */
	private Circle parseCircle(String string) {
		String [] circleParts = string.split(";"); 	
		int radius = Integer.parseInt(circleParts[0].split("=")[1]);
		int x = Integer.parseInt(circleParts[1].split("=")[1]);
		int y = Integer.parseInt(circleParts[2].split("=")[1]);
		String s = circleParts[3].split("=")[1].substring(1, circleParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = circleParts[4].split("=")[1].substring(1, circleParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Circle(new Point(x, y), radius, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}
	
	/**
	 * Method that parse {@link Square} from log file.
	 * 
	 * @param string Represent {@link Square} that need to be parsed.
	 * @return Represent parsed {@link Square}.
	 */
	private Square parseSquare(String string) {
		String [] squareParts = string.split(";"); 	
		int x = Integer.parseInt(squareParts[0].split("=")[1]);
		int y = Integer.parseInt(squareParts[1].split("=")[1]);
		int side = Integer.parseInt(squareParts[2].split("=")[1]);
		String s = squareParts[3].split("=")[1].substring(1, squareParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = squareParts[4].split("=")[1].substring(1, squareParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Square(new Point(x, y), side, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	/**
	 * Method that parse {@link Rectangle} from log file.
	 * 
	 * @param string Represent {@link Rectangle} that need to be parsed.
	 * @return Represent parsed {@link Rectangle}.
	 */
	private Rectangle parseRectangle(String string) {
		String [] rectangleParts = string.split(";"); 	
		int x = Integer.parseInt(rectangleParts[0].split("=")[1]);
		int y = Integer.parseInt(rectangleParts[1].split("=")[1]);
		int height = Integer.parseInt(rectangleParts[2].split("=")[1]);
		int width = Integer.parseInt(rectangleParts[3].split("=")[1]);
		String s = rectangleParts[4].split("=")[1].substring(1, rectangleParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = rectangleParts[5].split("=")[1].substring(1, rectangleParts[5].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Rectangle(new Point(x, y), width, height, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	/**
	 * Method that parse {@link Line} from log file.
	 * 
	 * @param string Represent {@link Line} that need to be parsed.
	 * @return Represent parsed {@link Line}.
	 */
	private Line parseLine(String string) {
		String [] lineParts = string.split(";"); 	
		int xStart = Integer.parseInt(lineParts[0].split("=")[1]);
		int yStart = Integer.parseInt(lineParts[1].split("=")[1]);
		int xEnd = Integer.parseInt(lineParts[2].split("=")[1]);
		int yEnd = Integer.parseInt(lineParts[3].split("=")[1]);
		String s = lineParts[4].split("=")[1].substring(1, lineParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		return new Line(new Point(xStart, yStart), new Point(xEnd, yEnd), new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
	}
	
	/**
	 * Method that parse {@link HexagonAdapter} from log file.
	 * 
	 * @param string Represent {@link HexagonAdapter} that need to be parsed.
	 * @return Represent parsed {@link HexagonAdapter}.
	 */
	private HexagonAdapter parseHexagon(String string) {
		String [] hexagonParts = string.split(";"); 	
		int radius = Integer.parseInt(hexagonParts[0].split("=")[1]);
		int x = Integer.parseInt(hexagonParts[1].split("=")[1]);
		int y = Integer.parseInt(hexagonParts[2].split("=")[1]);
		String s = hexagonParts[3].split("=")[1].substring(1, hexagonParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = hexagonParts[4].split("=")[1].substring(1, hexagonParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		Hexagon h = new Hexagon(x, y, radius);
		h.setBorderColor(new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
		h.setAreaColor(new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
		return new HexagonAdapter(h);
	}
}