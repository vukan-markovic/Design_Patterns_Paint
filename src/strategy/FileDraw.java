package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.DrawingModel;
import shapes.Shape;

public class FileDraw implements FileHandler {
	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	private DrawingModel model;
	
	public FileDraw(DrawingModel model) {
		this.model = model;
	}
	
	@Override
	public void save(File file) {
			try {
				fileOutputStream = new FileOutputStream(file + ".ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
				out.writeObject(model.getAll());
				out.close();
				fileOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void open(File file) {
		try {
			fileInputStream = new FileInputStream(file);
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	         model.addMultiple((ArrayList<Shape>) objectInputStream.readObject());
	         objectInputStream.close();
	         fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}