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
	private Shape shape;
	
	public FileLog(DrawingFrame frame) {
		this.frame = frame;
		model = frame.getView().getModel();
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
		String[] commands = command.split("->");
		switch(commands[0]) {
		case "Added point":
			new CmdAddShape(new Point(), model, frame.getList()).execute(); 
			break;
//		case "Added hexagon":
//			new CmdAddShape(new HexagonAdapter(new Hexagon()), model, log, "hexagon").execute(); 
//			break;
		case "Added line":
			new CmdAddShape(new Line(), model, log).execute(); 
			break;
		case "Added circle":
			new CmdAddShape(new Circle(), model, log).execute(); 
			break;
		case "Added rectangle":
			new CmdAddShape(new Rectangle(), model, log).execute(); 
			break;
		case "Added square":
			new CmdAddShape(new Square(), model, log).execute(); 
			break;
//		case "Updated point":
//			new CmdUpdatePoint(oldState, new Point(), frame.getList()).execute();
//			break;
//		case "Updated hexagon":
//			new CmdUpdateHexagon(oldState, new HexagonAdapter(new Hexagon()), log).execute();
//			break;
//		case "Updated line":
//			new CmdUpdateLine(oldState, new Line(), log).execute();
//			break;
//		case "Updated circle":
//			new CmdUpdateCircle(oldState, new Circle(), log).execute();
//			break;
//		case "Updated rectangle":
//			new CmdUpdateRectangle(oldState, new Rectangle(), log).execute();
//			break;
//		case "Updated square":
//			new CmdUpdateSquare(oldState, new Square(), log).execute();
//			break;
//		case "Removed point":
//			new CmdRemoveShape(, model, log).execute();
//			break;
//		case "Removed hexagon":
//			new CmdRemoveShape(, model, log).execute();
//			break;
//		case "Removed line":
//			new CmdRemoveShape(, model, log).execute();
//			break;
//		case "Removed circle":
//			shape = new Circle();
//			new CmdRemoveShape(, model, log).execute();
//			break;
//		case "Removed rectangle":
//			shape = new Rectangle();
//			new CmdRemoveShape(, model, log).execute();
//			break;
//		case "Removed square":
//			shape = new Square();
//			new CmdRemoveShape(, model, log).execute();
//			break;
		case "Bring to front":
			new CmdBringToFront(model, shape, log).execute();
			break;
		case "Bring to back":
			new CmdBringToBack(model, shape, log).execute();
			break;
		case "To front":
			new CmdToFront(model, shape, log).execute();
			break;
		case "To back":
			new CmdToBack(model, shape, log).execute();
			break;
		}
		frame.getView().repaint();
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