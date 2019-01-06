package shapes;
//public abstract class Shape implements Comparable, Serializable{
//	private int id;
//	private boolean settedId;
//	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public boolean isSettedId() {
//		return settedId;
//	}
//	public void setSettedId(boolean settedId) {
//		this.settedId = settedId;
//	}
//}

//public interface Command extends Serializable {}

//public void execute() {
//	model.add(circle);
//	Logger.getInstance().log(getClass().getSimpleName() + "_execute", model.getShapeIndex(circle),
//			circle.toString(), true);
//}

//public class UpdateRectangle implements Command {
//	private int shapeId;
//	
//	public UpdateRectangle(Rectangle original, Rectangle newState, int shapeId) {
//		this.original = original;
//		this.newState = newState;
//		this.shapeId = shapeId;
//	}
//	
//	Logger.getInstance().log(getClass().getSimpleName() + "_execute", shapeId, newState.toString(), true);
//}

//		btnModify.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				controller.modify();
//			}
//		});
//		
//	
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);	

//public class Model {	
//	private int id = 0;
//
//	public Model() {
//		this.shapes = new ArrayList<Shape>();
//		this.selectedShapes = new ArrayList<Shape>();
//	}
//	
//	public void addShape(Shape shape){
//		shapes.add(shape);
//		shape.setId(shapes.indexOf(shape));
//		if(!shape.isSettedId()){
//			settingId(shape);
//		}
//	}
//	
//	public void settingId(Shape shape){
//		if(shapes != null){
//			for(Shape s: shapes){
//				if(shape.getId() == s.getId() && !shape.equals(s)){
//					shape.setId(id++);
//					settingId(shape);			
//				}
//			}
//		}
//		shape.setSettedId(true);	
//	}

//public class LoggerModel implements Serializable {
//	private ArrayList<String> logLines = new ArrayList<String>();
//
//	public void add(String s) {
//		logLines.add(s);
//	}
//	
//	public boolean remove(String s) {
//		return logLines.remove(s);
//	}
//	
//	public String peek() {
//		return logLines.get(logLines.size()-1);
//	}
//	
//	public ArrayList<String> getLogLines() {
//		return logLines;
//	}
//
//	public void setLogLines(ArrayList<String> logLines) {
//		this.logLines = logLines;
//		System.out.println(logLines.size());
//	}
//}

//public class ZAxisHelper {
//
//	public static int doBringToFront(Shape selectedShape, ShapeModel model, MainFrame frame) {
//		model.getShapesList().remove(selectedShape);
//		model.getShapesList().add(selectedShape);
//		frame.repaint();
//		return model.getShapesList().size() - 1;
//	}
//
//	public static int doBringToBack(Shape selectedShape, ShapeModel model, MainFrame frame) {
//		model.getShapesList().remove(selectedShape);
//		model.getShapesList().add(0, selectedShape);
//		frame.repaint();
//		return 0;
//	}
//
//	public static void doToFront(int selectedIndex, ShapeModel model, MainFrame frame) {
//		if (selectedIndex != -1 && selectedIndex != (model.getShapesList().size() - 1)) {
//			Collections.swap(model.getShapesList(), selectedIndex + 1, selectedIndex);
//		}
//		frame.repaint();
//	}
//
//	public static void doToBack(int selectedIndex, ShapeModel model, MainFrame frame) {
//		if (selectedIndex != -1 && selectedIndex != 0) {
//			Collections.swap(model.getShapesList(), selectedIndex - 1, selectedIndex);
//		}
//		frame.repaint();
//	}
//
//	public static void moveShapeToIndex(int newIndex, Shape shape, ShapeModel model, MainFrame frame) {
//		if (model.getShapesList().remove(shape)) {
//			if (newIndex < model.getShapesList().size() - 1) {
//				model.getShapesList().add(newIndex, shape);
//			} else {
//				model.getShapesList().add(shape);
//			}
//		}
//		frame.repaint();
//	}
//
//	public static Shape getSelectedShape(ShapeModel model) {
//		for (Shape s : model.getShapesList()) {
//			if (s != null && s.isSelected()) {
//				return s;
//			}
//		}
//		return null;
//	}
//
//	public static int getSelectedShapeIndex(ShapeModel model) {
//		int listSize = model.getShapesList().size() - 1;
//		for (int i = 0; i <= listSize; i++) {
//			if (model.getShapesList().get(i) != null && model.getShapesList().get(i).isSelected()) {
//				return i;
//			}
//		}
//		return -1;
//	}
//}

//try {
//	BufferedWriter writter = new BufferedWriter(new FileWriter(file,true));
//	for(String line: frame.getTxtEventLog().getText().split("\\n")) { 
//		writter.append(line);   
//		writter.newLine();      
//	}
//	writter.close();    
//} catch (IOException e) {
//	e.printStackTrace();
//  }
//
//@Override
//public void load() {
//file = fileChooser.getSelectedFile();
//model.getShapes().clear();
//
//try {
//bfReader=new BufferedReader(new FileReader(file));
//String line, shape, command;
//
//List<Shape> shapes = new ArrayList<>();
//while((line=bfReader.readLine()) != null) {
//	String[] lineArray = line.split(",");
//	command = lineArray[0];
//	shape = lineArray[1];
//	int id = Integer.parseInt(lineArray[2]);
//
//	if (command.equals("Added")){
//		if (shape.equals("Point")) {
//			Point p = new Point(Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4]),Color.decode(lineArray[5]));
//			p.setId(id);
//			p.setSettedId(true);
//			CommandAdd c = new CommandAdd(model, p);
//			controller.doCommand(c);
//		} else if (shape.equals("Line")){
//			Point startPoint = new Point(Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4]));
//			Point endPoint = new Point(Integer.parseInt(lineArray[5]), Integer.parseInt(lineArray[6]));	
//			Line l = new Line(startPoint, endPoint, Color.decode(lineArray[7]));
//			l.setId(id);
//			l.setSettedId(true);
//			CommandAdd c = new CommandAdd(model, l);
//			controller.doCommand(c);
//		} else if (shape.equals("Circle")) {
//			Circle ci = new Circle(new Point(Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4])), Integer.parseInt(lineArray[5]), Color.decode(lineArray[6]), Color.decode(lineArray[7]));
//			ci.setId(id);
//			ci.setSettedId(true);
//			CommandAdd c = new CommandAdd(model, ci);
//			controller.doCommand(c);
//		} else if (shape.equals("Square")) {
//			Square s = new Square(new Point(Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4])), Integer.parseInt(lineArray[5]), Color.decode(lineArray[7]), Color.decode(lineArray[6]));
//			s.setId(id);
//			s.setSettedId(true);
//			CommandAdd c = new CommandAdd(model, s);
//			controller.doCommand(c);
//		} else if (shape.equals("Rectangle")) {
//			Rectangle r = new Rectangle(new Point(Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4])), Integer.parseInt(lineArray[5]), Integer.parseInt(lineArray[7]), Color.decode(lineArray[6]), Color.decode(lineArray[8]));
//			r.setId(id);
//			r.setSettedId(true);
//			CommandAdd c = new CommandAdd(model, r);
//			controller.doCommand(c);
//		} else if (shape.equals("Hexagon")) {
//			HexagonAdapter h = new HexagonAdapter(new Point(Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4])), Integer.parseInt(lineArray[5]),Color.decode(lineArray[6]),Color.decode(lineArray[7]));
//			h.setId(id);
//			h.setSettedId(true);
//			CommandAdd c = new CommandAdd(model, h);
//			controller.doCommand(c);
//		}
//	}
//	else if (command.equals("Removed")){
//		model.getShapes().stream().forEach(s -> {
//			if(s.getId() == id) {
//				model.addShapeToSelection(s);
//				CommandRemove c = new CommandRemove(model);
//				controller.doCommand(c);
//			}
//		});
//	}
//	else if (command.equals("To front")) {
//		for(Shape s: model.getShapes()) {
//			if(s.getId() == id) {
//				CommandToFront c = new CommandToFront(model, s);
//				controller.doCommand(c);
//			}
//		};
//		
//	} else if (command.equals("To back")) {
//		for(Shape s: model.getShapes()){
//			if(s.getId() == id) {
//				CommandToBack c = new CommandToBack(model, s);
//				controller.doCommand(c);
//			}
//		};
//	} else if (command.equals("Bring to front")) {
//		model.getShapes().stream().forEach(s -> {
//			if(s.getId() == id) {
//				CommandBringToFront c = new CommandBringToFront(model, s);
//				controller.doCommand(c);
//			}
//		});
//	} else if (command.equals("Push to back")) {
//		model.getShapes().stream().forEach(s -> {
//			if(s.getId() == id) {
//				CommandPushToBack c = new CommandPushToBack(model, s);
//				controller.doCommand(c);
//			}
//		});
//	}
//	else if(command.equals("Modified")) {
//		String shapeF = shape;
//		model.getShapes().stream().forEach(s-> {
//			if(s.getId() == id) {
//				if (shapeF.equals("Point")) {
//        			DlgPoint dlg = new DlgPoint();
//        			dlg.setX(Integer.parseInt(lineArray[3]));
//        			dlg.setY(Integer.parseInt(lineArray[4]));
//        			dlg.setOutterColor(Color.decode(lineArray[5]));
//        			CommandUpdatePoint cmd = new CommandUpdatePoint((Point) s, dlg);
//        			controller.doCommand(cmd);
//        		} 
//        		else if (shapeF.equals("Line")) {
//        			DlgLine dlg = new DlgLine();
//        			dlg.setsX(Integer.parseInt(lineArray[3]));
//        			dlg.setsY(Integer.parseInt(lineArray[4]));
//        			dlg.seteX(Integer.parseInt(lineArray[5]));
//        			dlg.seteY(Integer.parseInt(lineArray[6]));
//        			dlg.setOutterColor(Color.decode(lineArray[7]));
//        			CommandUpdateLine cmd = new CommandUpdateLine((Line) s, dlg);
//        			controller.doCommand(cmd);
//        		}  else if (shapeF.equals("Circle")) {
//        			DlgCircle dlg = new DlgCircle();
//        			dlg.setX(Integer.parseInt(lineArray[3]));
//        			dlg.setY(Integer.parseInt(lineArray[4]));
//        			dlg.setRadius(Integer.parseInt(lineArray[5]));
//        			dlg.setOutterColor(Color.decode(lineArray[6]));
//        			dlg.setInnerColor(Color.decode(lineArray[7]));
//        			CommandUpdateCircle cmd =  new CommandUpdateCircle((Circle) s, dlg);
//        			controller.doCommand(cmd);
//        		} else if (shapeF.equals("Square")) {
//        			DlgSquare dlg = new DlgSquare();
//        			dlg.setX(Integer.parseInt(lineArray[3]));
//        			dlg.setY(Integer.parseInt(lineArray[4]));
//        			dlg.setSide(Integer.parseInt(lineArray[5]));
//        			dlg.setOutterColor(Color.decode(lineArray[6]));
//        			dlg.setInnerColor(Color.decode(lineArray[7]));
//        			CommandUpdateSquare cmd =  new CommandUpdateSquare((Square) s, dlg);
//        			controller.doCommand(cmd);
//        		}   else if (shapeF.equals("Rectangle")) {
//        			DlgRectangle dlg = new DlgRectangle();
//        			dlg.setX(Integer.parseInt(lineArray[3]));
//        			dlg.setY(Integer.parseInt(lineArray[4]));
//        			dlg.setWidthR(Integer.parseInt(lineArray[5]));
//        			dlg.setHeightR(Integer.parseInt(lineArray[6]));
//        			dlg.setOutterColor(Color.decode(lineArray[7]));
//        			dlg.setInnerColor(Color.decode(lineArray[8]));
//        			CommandUpdateRectangle cmd =  new CommandUpdateRectangle((Rectangle) s, dlg);
//        			controller.doCommand(cmd);
//        		} else if (shapeF.equals("Hexagon")) {
//        			DlgHexagon dlg = new DlgHexagon();
//        			dlg.setX(Integer.parseInt(lineArray[3]));
//        			dlg.setY(Integer.parseInt(lineArray[4]));
//        			dlg.setRadius(Integer.parseInt(lineArray[5]));
//        			dlg.setOutterColor(Color.decode(lineArray[6]));
//        			dlg.setInnerColor(Color.decode(lineArray[7]));
//        			CommandUpdateHexagon cmd =  new CommandUpdateHexagon((HexagonAdapter) s, dlg);
//        			controller.doCommand(cmd);
//        		}

//public class CommandToFront implements Command{
//	private Model model;
//	private Shape s;
//	private int index;
//	private String command;
//	
//	public CommandToFront(Model model, Shape s) {
//		this.model = model;
//		this.s = s;
//	}
//	
//	@Override
//	public void execute() {
//		command = "To front," + s.toString()+ "\n";
//		index = model.getShapes().indexOf(s) + 1;
//		model.removeShape(s);
//		model.addShapeIndex(s, index);
//	}
//
//	@Override
//	public void unexecute() {
//		command = "To back," + s.toString()+ "\n";
//		model.removeShape(s);
//		model.addShapeIndex(s, index-1);		
//	}
//}
//
//public class CommandToBack implements Command{
//	private Model model;
//	private Shape s;
//	private int index;
//	private String command;
//	
//	public CommandToBack(Model model, Shape s) {
//		this.model = model;
//		this.s = s;
//	}
//
//  @Override
//	public void execute() {
//		command = "To back,"+ s.toString()+ "\n";
//		index = model.getShapes().indexOf(s) -1;
//		model.removeShape(s);
//		model.addShapeIndex(s, index);
//	}
//
//	@Override
//	public void unexecute() {
//		command = "To front,"+ s.toString()+ "\n";
//		model.removeShape(s);
//		model.addShapeIndex(s, index+1);
//		
//	}
//
//	@Override
//	public String log() {
//		return command;
//	}
//}
//
//public class CommandBringToFront implements Command{
//	private Model model;
//	private Shape s;
//	private int index;
//	private String command;
//	
//	public CommandBringToFront(Model model, Shape s) {
//		this.model = model;
//		this.s = s;
//	}
//	
//	@Override
//	public void execute() {
//		command = "Bring to front," + s.toString()+ "\n";
//		index = model.getShapes().indexOf(s);
//		model.removeShape(s);
//		model.addShape(s);
//	}
//
//	@Override
//	public void unexecute() {
//		command = "Push to back," + s.toString()+ "\n";
//		model.removeShape(s);
//		model.addShapeIndex(s, index);
//	}
//	
//	public class CommandPushToBack implements Command{
//		private Model model;
//		private Shape s;
//		private int index;
//		private String command;
//		
//		public CommandPushToBack(Model model, Shape s) {
//			this.model = model;
//			this.s = s;
//		}
//
//		@Override
//		public void execute() {
//			command = "Push to back," + s.toString()+ "\n";
//			index = model.getShapes().indexOf(s);
//			model.removeShape(s);
//			model.addShapeIndex(s, 0);
//		}
//
//		@Override
//		public void unexecute() {
//			command = "Bring to front," + s.toString()+ "\n";
//			model.removeShape(s);
//			model.addShapeIndex(s, index);
//		}

//public class Controller {
//	private Frame frame;
//	private Model model;
//	private View view;
//	private List<Command> commandsStack;
//	private List<Command> undoCommandsStack;

//	public Controller() {
//		this.commandsStack = new ArrayList<Command>();
//		this.undoCommandsStack = new ArrayList<Command>();
//		this.obervers = new ArrayList<Observer>();
//		fileChooser.addChoosableFileFilter(serFilter);
//		fileChooser.addChoosableFileFilter(logFilter);
//	}
	
//	public void doCommand(Command c) {
//		c.execute();
//		frame.getTxtEventLog().append(c.log());
//		commandsStack.add(c);
//		enableOrDisableBtnUndoBtn();
//		frame.getBtnRedo().setEnabled(false);
//		view.repaint();
//	}

//	public void undoCommand() {
//		Command c = commandsStack.get(commandsStack.size()-1);
//		commandsStack.remove(c);
//		undoCommandsStack.add(c);
//		c.unexecute();
//		frame.getTxtEventLog().append(c.log());
//		enableOrDisableBtnUndoBtn();
//		enableOrDisableBtnRedoBtn();
//	}
	
//	public void redoCommand() {
//		Command c = undoCommandsStack.get(undoCommandsStack.size()-1);
//		undoCommandsStack.remove(c);
//		commandsStack.add(c);
//		c.execute();
//		c.log();
//		enableOrDisableBtnUndoBtn();
//		enableOrDisableBtnRedoBtn();
//	}
	
//	public void toFront() {
//		Shape s = model.getSelectedShapes().get(0);
//		if(model.getShapes().indexOf(s) < model.getSelectedShapes().size()) {
//			CommandToFront cmd = new CommandToFront(model, s);
//			doCommand(cmd);
//		}
//		
//	}
		
//	public Model getModel() {
//		return model;
//	}

//	public void setModel(Model model) {
//		this.model = model;
//	}

//  public View getView() {
//		return view;
//	}

//	public void setView(View view) {
//		this.view = view;
//		view.setController(this);
//	}

//	public Frame getFrame() {
//		return frame;
//	}

//public class AdditionalActionsController implements Serializable {

//	public void doBringToFront() {
//		int selectedIndex = ZAxisHelper.getSelectedShapeIndex(model);
//		BringToFrontCommand btfCommand = new BringToFrontCommand(selectedIndex, model, frame);
//		btfCommand.execute();
//		model.getUndoStack().offerLast(btfCommand);
//	}
//
//	public void doToFront() {
//		ToFrontCommand tfCommand = new ToFrontCommand(model, frame);
//		tfCommand.execute();
//		model.getUndoStack().offerLast(tfCommand);
//	}
//}
//
//public class BringToFrontCommand implements Command {
//	private int selectedIndex = -1;
//	private Shape shape;
//	private ShapeModel model;
//	private MainFrame frame;
//
//	public BringToFrontCommand(int selectedIndex, ShapeModel model, MainFrame frame) {
//		this.selectedIndex = selectedIndex;
//		this.model = model;
//		this.frame = frame;
//		shape = model.getShapesList().get(selectedIndex);
//	}
//
//	@Override
//	public void execute() {
//		ZAxisHelper.doBringToFront(shape, model, frame);
//		Logger.getInstance().log(getClass().getSimpleName() + "_execute", shape.toString(), true, true);
//	}
//
//	@Override
//	public void unexecute() {
//		ZAxisHelper.moveShapeToIndex(selectedIndex, shape, model, frame);
//		Logger.getInstance().log(getClass().getSimpleName() + "_unexecute", shape.toString(), true, true);
//	}
//}
//
//
//
//public class ToBackCommand implements Command {
//	private static final long serialVersionUID = 7699736277398433843L;
//	private ShapeModel model;
//	private MainFrame frame;
//	private Shape shape;
//
//	public ToBackCommand(ShapeModel model, MainFrame frame) {
//		this.model = model;
//		this.frame = frame;
//		shape = ZAxisHelper.getSelectedShape(model);
//	}
//
//	@Override
//	public void execute() {
//		ZAxisHelper.doToBack(model.getShapeIndex(shape), model, frame);
//
//		Logger.getInstance().log(getClass().getSimpleName() + "_execute",
//				model.get(model.getShapeIndex(shape)).toString(), true, true);
//	}
//
//	@Override
//	public void unexecute() {
//		ZAxisHelper.doToFront(model.getShapeIndex(shape), model, frame);
//
//		Logger.getInstance().log(getClass().getSimpleName() + "_unexecute",
//				model.get(model.getShapeIndex(shape)).toString(), true, true);
//	}


//public Square clone() {
//		return new Square(upperLeft.clone(),
//				sideLength, this.getColor(), this.getInnerColor());
//	}

//	public Point clone() {
//		return new Point(x,y,this.getColor());
//	}


//public Line clone() {
//	return new Line(pointStart.clone(),pointEnd.clone(),this.getColor());
//}

//public Circle clone() {
//	return new Circle(center.clone(),
//			this.radius, this.getColor(), this.getInnerColor());
//}  

//public Rectangle clone() {
//	return new Rectangle(upperLeft.clone(), 
//			this.getSideLength(), this.width, this.getColor(), this.getInnerColor());
//}

//@Override
//public void loadFile(File file) {
//	 File selectedFile = file;
//     if(selectedFile.getName().split("\\.")[1].equals("log"))
//     try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
//         String line,text="";
//    	 LogParser lp=new LogParser();
//         while ((line = br.readLine()) != null) {
//        	 text=text+line+'\n';
//        	 lp.getTpLogPreview().setText(text);
//        	 lp.setConf(false);
//        	 lp.setVisible(true);
//        	 if(lp.isConf()) {
//        		 CommandTransfer ct=new CommandTransfer(model,frame);
//        		 Command c=ct.toCommand(line);
//        		 frame.getView().repaint();
//        		 if(c==null) {lp.dispose();break;}
//        	 }else {lp.dispose();break;}
//         }
//     }catch (Exception e2) {
//			e2.printStackTrace();
//     	JOptionPane.showMessageDialog(frame,
//         	    "Error while reading the file.");
//		}
//     else JOptionPane.showMessageDialog(frame,
//     	    "Wrong file format, file extension must be '.log'.");
//}