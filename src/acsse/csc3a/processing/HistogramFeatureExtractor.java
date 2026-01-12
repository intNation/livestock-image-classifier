package acsse.csc3a.processing;

import java.awt.image.BufferedImage;
import acsse.csc3a.util.*;

public class HistogramFeatureExtractor implements FeatureExtractor {
	private int bins = 16;

	public FeatureVector extract(BufferedImage img, String path) {
		double[] hist = new double[bins * 3];
		int w = img.getWidth(), h = img.getHeight();
		for (int y = 0; y < h; y++)
			for (int x = 0; x < w; x++) {
				int rgb = img.getRGB(x, y);
				int r = (rgb >> 16) & 0xff, g = (rgb >> 8) & 0xff, b = rgb & 0xff;
				int br = r * bins / 256, bg = g * bins / 256, bb = b * bins / 256;
				hist[br]++;
				hist[bins + bg]++;
				hist[2 * bins + bb]++;
			}
		// normalize
		double total = w * h;
		for (int i = 0; i < hist.length; i++)
			hist[i] /= total;
		return new FeatureVector(hist, path);
	}
}