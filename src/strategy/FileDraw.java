package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.DrawingModel;
import shapes.Shape;

/**
 * Class that is responsible to save and open draw as serializable file.
 */
public class FileDraw implements FileHandler {
	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	private DrawingModel model;
	
	public FileDraw(DrawingModel model) {
		this.model = model;
	}
	
	/**
	 * Save forwarded file as serializable.
	 */
	@Override
	public void save(File file) {
		try {
			fileOutputStream = new FileOutputStream(file + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(model.getAll());
			out.close();
			fileOutputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Open serializable file and load it to draw adding shapes to model.
	 */
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
			System.out.println(e.getMessage());
		}
	}
}