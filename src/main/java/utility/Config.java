package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static Properties properties;
	static {
		String filePath = "./test.properties";

		try {
			FileInputStream input = new FileInputStream(filePath);
			properties = new Properties();
			properties.load(input);
			input.close();
			System.out.println("retreived property by key: "+input);
		} catch (IOException e) {
			
			System.out.println("File not found");
			throw new RuntimeException("File not found");
		}
	}

	public static String getPropValue(String key){

		String value = properties.getProperty(key);
		return value;
	}
}
