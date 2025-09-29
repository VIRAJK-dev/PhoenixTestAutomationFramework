package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigManager {

	private static Properties prop = new Properties();
	private static String path = "config/config.properties";
	private static String env;

	private ConfigManager() {
		// Private constructor
	}

	static {

		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();
		System.out.println("Running tests in" + env);

		switch (env) {
		case "dev" -> path = "config/config.dev.properties";

		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.qa.properties";

		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {
			throw new RuntimeException("Cant read file at path" + path);
		}

		FileReader fileReader = null;
		try {
//			fileReader = new FileReader(configFile);
			prop.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {
		// TODO Auto-generated method stub

		return prop.getProperty(key);

	}

}
