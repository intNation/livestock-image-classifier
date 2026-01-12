package acsse.csc3a.processing;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageLoader {
	public static BufferedImage load(File f) {
		try {
			return ImageIO.read(f);
		} catch (Exception e) {
			return null;
		}
	}
}