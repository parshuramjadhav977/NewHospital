package org.hosspital.helper;
import java.io.FileInputStream;
import java.util.Properties;
import java.nio.file.Path;
import java.nio.file.FileSystems;

public class PathHelper {                 
public static final  String filePath="C:\\Users\\jadha\\eclipse-workspace\\HospitalMangementSystem\\admin";
	public static String completePath = "";
	public static Properties p = new Properties();
	public PathHelper() {

		try {
			Path currentDirectoryPath = FileSystems.getDefault().getPath("");
			String currentDirectoryName = currentDirectoryPath.toAbsolutePath().toString();
			completePath = currentDirectoryName + "\\src\\resources\\db.properties";
			FileInputStream finf = new FileInputStream(completePath);
			p.load(finf);
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
		}
}
}