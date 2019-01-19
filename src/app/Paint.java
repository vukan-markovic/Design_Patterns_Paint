package app;

import controller.DrawingController;
import frame.DrawingFrame;
import model.DrawingModel;

/**
 * @author Vukan Markovic
 * @version 1.0
 * @since 11.01.2019.
 */
public class Paint {

	public static void main(String[] args) {
		DrawingFrame frame = new DrawingFrame();
		frame.setVisible(true);
		frame.setTitle("Markovic Vukan IT20/2016");
		DrawingModel model = new DrawingModel();
		frame.getView().setModel(model);
		frame.setController(new DrawingController(model, frame));
	}
}