package utilities.GeneralUtils;

import java.io.FileInputStream;
import java.util.*;

public class PropertyConfig {
	protected static Properties properties;
	
	static {
		String path = "C:\\Users\\Alienware Laptop\\IdeaProjects\\BriteERP-T10\\BriteERP-T10\\properties\\config.properties";
	
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			properties = new Properties();
			properties.load(fileInputStream);
			
			fileInputStream.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String property) {
		try {
			return properties.getProperty(property);
		}catch(Exception e) {
			System.out.println("Unable to get property");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setProperty(String property, String value) {
		try {
			properties.setProperty(property, value);
		}catch(Exception e) {
			System.out.println("Unable to set property");
			e.printStackTrace();
		}
	}
}
