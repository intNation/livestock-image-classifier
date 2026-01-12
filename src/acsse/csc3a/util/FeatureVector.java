package acsse.csc3a.util;

public class FeatureVector {
	private double[] data;
	private String imagePath;

	public FeatureVector(double[] d, String p) {
		data = d;
		imagePath = p;
	}

	public double[] getData() {
		return data;
	}

	public String getPath() {
		return imagePath;
	}
}