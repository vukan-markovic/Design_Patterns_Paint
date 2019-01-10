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
import commands.CmdToBack;
import commands.CmdToFront;
import commands.CmdUpdateCircle;
import commands.CmdUpdateHexagon;
import commands.CmdUpdateLine;
import commands.CmdUpdatePoint;
import commands.CmdUpdateRectangle;
import commands.CmdUpdateSquare;
import controller.DrawingController;
import dialogs.LogParser;
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
	private DefaultListModel<String> log;
	private LogParser logParser;
	private DrawingController controller;
	
	public FileLog(DrawingFrame frame, DrawingModel model, DrawingController controller) {
		this.frame = frame;
		this.model = model; 
		this.controller = controller;
		log = frame.getList();
	}

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

	@Override
	public void open(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));
			logParser = new LogParser();
			logParser.setFileLog(this);
			logParser.addCommand(reader.readLine());
			logParser.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void readLine(String command) {
		String[] commands1 = command.split("->");
		if (commands1[0].equals("Added")) {
			controller.executeCommand(new CmdAddShape(parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]), model, frame.getList())); 
		}
		else if (commands1[0].equals("Updated")) {
			Shape shape1 = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
			if (shape1 instanceof Point) {
				controller.executeCommand(new CmdUpdatePoint((Point) shape1, parsePoint(commands1[2].split(":")[1]), frame.getList()));
			}
			else if (shape1 instanceof Line) {
				controller.executeCommand(new CmdUpdateLine((Line) shape1, parseLine(commands1[2].split(":")[1]), frame.getList()));
			}
			else if (shape1 instanceof Square) {
				controller.executeCommand(new CmdUpdateSquare((Square) shape1, parseSquare(commands1[2].split(":")[1]), frame.getList()));
			}
			else if (shape1 instanceof Rectangle) {
				controller.executeCommand(new CmdUpdateRectangle((Rectangle) shape1, parseRectangle(commands1[2].split(":")[1]), frame.getList()));
			}
			else if (shape1 instanceof Circle) {
				controller.executeCommand(new CmdUpdateCircle((Circle) shape1, parseCircle(commands1[2].split(":")[1]), frame.getList()));
			}
			else {
				controller.executeCommand(new CmdUpdateHexagon((HexagonAdapter) shape1, parseHexagon(commands1[2].split(":")[1]), frame.getList()));
			}
		} else if (commands1[0].equals("Deleted")) {
			controller.executeCommand(new CmdRemoveShape(parseShape(commands1[0].split(":")[0], commands1[1].split(":")[1]), model, frame.getList())); 
		} else if (commands1[0].equals("Moved to front")) {
			controller.executeCommand(new CmdToFront(model, parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]), log));
		} else if (commands1[0].equals("Moved to back")) {
			controller.executeCommand(new CmdToBack(model, parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]), log));
		} else if (commands1[0].equals("Bringed to front")) {
			controller.executeCommand(new CmdBringToFront(model, parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]), log));
		} else if (commands1[0].equals("Bringed to back")) {
			controller.executeCommand(new CmdBringToBack(model, parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]), log));
		}

		try {
			String line = reader.readLine();
			if (line != null) logParser.addCommand(line);
			else {
				logParser.closeDialog();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Shape parseShape(String shape, String shapeParameters) {
		if (shape.equals("Point")) return parsePoint(shapeParameters);
		else if (shape.equals("Hexagon")) return parseHexagon(shapeParameters);
		else if (shape.equals("Line")) return parseLine(shapeParameters);
		else if (shape.equals("Circle")) return parseCircle(shapeParameters);
		else if (shape.equals("Rectangle")) return parseRectangle(shapeParameters);
		else return parseSquare(shape);
	}

	private Point parsePoint(String string) {
		String [] pointParts = string.split(";"); 		
		String s = pointParts[2].split("=")[1].substring(1, pointParts[2].split("=")[1].length() - 1);
		String [] colors = s.split(",");
		return new Point(Integer.parseInt(pointParts[0].split("=")[1]), Integer.parseInt(pointParts[1].split("=")[1]), new Color(Integer.parseInt(colors[0].split("-")[1]), Integer.parseInt(colors[1].split("-")[1]), Integer.parseInt(colors[2].split("-")[1])));
	}
	
	private Circle parseCircle(String string) {
		String [] pointParts = string.split(";"); 	
		int radius = Integer.parseInt(pointParts[0].split("=")[1]);
		int x = Integer.parseInt(pointParts[1].split("=")[1]);
		int y = Integer.parseInt(pointParts[2].split("=")[1]);
		String s = pointParts[3].split("=")[1].substring(1, pointParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = pointParts[4].split("=")[1].substring(1, pointParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Circle(new Point(x, y), radius, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}
	
	private Square parseSquare(String string) {
		String [] pointParts = string.split(";"); 	
		int x = Integer.parseInt(pointParts[0].split("=")[1]);
		int y = Integer.parseInt(pointParts[1].split("=")[1]);
		int side = Integer.parseInt(pointParts[2].split("=")[1]);
		String s = pointParts[3].split("=")[1].substring(1, pointParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = pointParts[4].split("=")[1].substring(1, pointParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Square(new Point(x, y), side, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	private Rectangle parseRectangle(String string) {
		String [] pointParts = string.split(";"); 	
		int x = Integer.parseInt(pointParts[0].split("=")[1]);
		int y = Integer.parseInt(pointParts[1].split("=")[1]);
		int height = Integer.parseInt(pointParts[2].split("=")[1]);
		int width = Integer.parseInt(pointParts[3].split("=")[1]);
		String s = pointParts[4].split("=")[1].substring(1, pointParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = pointParts[5].split("=")[1].substring(1, pointParts[5].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Rectangle(new Point(x, y), width, height, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	private Line parseLine(String string) {
		String [] pointParts = string.split(";"); 	
		int xStart = Integer.parseInt(pointParts[0].split("=")[1]);
		int yStart = Integer.parseInt(pointParts[1].split("=")[1]);
		int xEnd = Integer.parseInt(pointParts[2].split("=")[1]);
		int yEnd = Integer.parseInt(pointParts[3].split("=")[1]);
		String s = pointParts[4].split("=")[1].substring(1, pointParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		return new Line(new Point(xStart, yStart), new Point(xEnd, yEnd), new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
	}
	
	private HexagonAdapter parseHexagon(String string) {
		String [] pointParts = string.split(";"); 	
		int radius = Integer.parseInt(pointParts[0].split("=")[1]);
		int x = Integer.parseInt(pointParts[1].split("=")[1]);
		int y = Integer.parseInt(pointParts[2].split("=")[1]);
		String s = pointParts[3].split("=")[1].substring(1, pointParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = pointParts[4].split("=")[1].substring(1, pointParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		Hexagon h = new Hexagon(x, y, radius);
		h.setBorderColor(new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
		h.setAreaColor(new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
		return new HexagonAdapter(h);
	}
}