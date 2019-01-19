package strategy;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import frame.DrawingFrame;

/**
 * Class that is responsible to save draw as jpeg.
 */
public class FilePicture implements FileHandler {
	private DrawingFrame frame;
	
	public FilePicture(DrawingFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Save currently draw as picture. 
	 * 
	 * {@see <a>https://stackoverflow.com/questions/4725320/how-to-save-window-contents-as-an-image</a>} 
	 */
	@Override
	public void save(File file) {
		 BufferedImage imagebuffer = null;
		    try {
		        imagebuffer = new Robot().createScreenCapture(frame.getView().getBounds());
		        frame.getView().paint(imagebuffer.createGraphics());
		        ImageIO.write(imagebuffer,"jpeg", new File(file + ".jpeg"));
		    } catch (Exception e) {
		        System.out.println(e.getMessage());
		    }
	}

	/**
	 * Not implemented.
	 */
	@Override
	public void open(File file) {}
}