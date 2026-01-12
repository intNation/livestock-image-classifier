package acsse.csc3a.processing;

import java.awt.image.BufferedImage;
import acsse.csc3a.util.FeatureVector;

public interface FeatureExtractor {
	FeatureVector extract(BufferedImage img, String path);
}