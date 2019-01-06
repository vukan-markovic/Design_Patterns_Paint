package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.DefaultListModel;
import adapter.HexagonAdapter;
import commands.CmdAddShape;
import commands.CmdRemoveShape;
import commands.CmdUpdateCircle;
import commands.CmdUpdateHexagon;
import commands.CmdUpdateLine;
import commands.CmdUpdatePoint;
import commands.CmdUpdateRectangle;
import commands.CmdUpdateSquare;
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

public class FileLog implements FileManager {
	private BufferedWriter writer;
	private BufferedReader reader;
	private DrawingFrame frame;
	private DrawingModel model;
	private LogParser logParser;
	private Shape shape;
	private String shapeType;
	
	public FileLog(DrawingFrame frame) {
		this.frame = frame;
		model = frame.getView().getModel();
	}

	@Override
	public void save(File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file));
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
//		String[] commands = command.split("->");
//		switch(commands[0]) {
//		case "Added point":
//			shape = new Point();
//			shapeType = "point";
//			new CmdAddShape(shape ,model, frame.getList(), shapeType).execute(); 
//			break;
//		case "Added hexagon":
//			shape = new HexagonAdapter(new Hexagon());
//			shapeType = "hexagon";
//			new CmdAddShape(shape ,model, frame.getList(), shapeType).execute(); 
//			break;
//		case "Added line":
//			shape = new Line();
//			shapeType = "line";
//			new CmdAddShape(shape ,model, frame.getList(), shapeType).execute(); 
//			break;
//		case "Added circle":
//			shape = new Circle();
//			shapeType = "circle";
//			new CmdAddShape(shape ,model, frame.getList(), shapeType).execute(); 
//			break;
//		case "Added rectangle":
//			shape = new Rectangle();
//			shapeType = "rectangle";
//			new CmdAddShape(shape ,model, frame.getList(), shapeType).execute(); 
//			break;
//		case "Added square":
//			shape = new Square();
//			shapeType = "square";
//			new CmdAddShape(shape ,model, frame.getList(), shapeType).execute(); 
//			break;
//		case "Updated point":
//			shape = new Point();
//			new CmdUpdatePoint(oldState, newState, frame.getList()).execute();
//			break;
//		case "Updated hexagon":
//			shape = new HexagonAdapter(new Hexagon());
//			new CmdUpdateHexagon(oldState, newState, log).execute();
//			break;
//		case "Updated line":
//			shape = new Line();
//			new CmdUpdateLine(oldState, newState, log).execute();
//			break;
//		case "Updated circle":
//			shape = new Circle();
//			new CmdUpdateCircle(oldState, newState, log).execute();
//			break;
//		case "Updated rectangle":
//			shape = new Rectangle();
//			new CmdUpdateRectangle(oldState, newState, log).execute();
//			break;
//		case "Updated square":
//			shape = new Square();
//			new CmdUpdateSquare(oldState, newState, log).execute();
//			break;
//		case "Removed point":
//			shape = new Point();
//			new CmdRemoveShape(shapes, model, frame.getList()).execute();
//			break;
//		case "Removed hexagon":
//			shape = new HexagonAdapter(new Hexagon());
//			new CmdRemoveShape(shapes, model, frame.getList()).execute();
//			break;
//		case "Removed line":
//			shape = new Line();
//			new CmdRemoveShape(shapes, model, frame.getList()).execute();
//			break;
//		case "Removed circle":
//			shape = new Circle();
//			new CmdRemoveShape(shapes, model, frame.getList()).execute();
//			break;
//		case "Removed rectangle":
//			shape = new Rectangle();
//			new CmdRemoveShape(shapes, model, frame.getList()).execute();
//			break;
//		case "Removed square":
//			shape = new Square();
//			new CmdRemoveShape(shapes, model, frame.getList()).execute();
//			break;
//		}
//		
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
}