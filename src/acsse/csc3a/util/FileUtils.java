package acsse.csc3a.util;

import java.io.File;

public class FileUtils {
	public static String[] listImages(String dir) {
		File f = new File(dir);
		return f.list((d, name) ->
			name.toLowerCase().matches(".*\\.(png|jpg|jpeg)")
		);
	}

	public static boolean deleteImage(String path) {
		return new File(path).delete();
	}
}


