package commonUtility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class base {

	public String readValueFromPropertiesFile(String key) throws Throwable {
		FileInputStream fis = new FileInputStream(new File("src/main/resources/configuration/config.properties"));
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}
}
