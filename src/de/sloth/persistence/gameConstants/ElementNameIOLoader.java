package de.sloth.persistence.gameConstants;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;


/**
 * Definition of ElementNameIOLoader.
 * <br> Used for random generating or loading a specific name for
 * a {@link GameObject} or a {@link GameEvent} name.
 * 
 * @author Joshua Ward
 * @version 01.06.2015
 */
public class ElementNameIOLoader {
	
	/**
	 * Returns a name which is randomly selected by the {@link ElementNameIOLoader}.
	 * @param filePath : Path to file containing the names.
	 * @return A randomly selected name.
	 */
	public static String getRandomName(String filePath) {
		String result = "";
		
		try {
			ArrayList<String> names = new ArrayList<String>();
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(filePath));
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(stream);
			
			while (scan.hasNext()) {
				names.add(scan.nextLine());
			}
				
			result = names.get(new Random().nextInt(names.size()));
		} catch (FileNotFoundException e) {
			result = "File Empty";
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Returns a name if the key, given by parameter, is found in the file.
	 * <br> If the key cannot be found in the file, it will return "Not found".
	 * @param key : The key to the name that should be looked for.
	 * @param filePath : Path to file containing the names. 
	 * @return The name that was looked for. If the key couldn't be found it will return "Not found".
	 */
	public static String getName(String key, String filePath) {
		String result = "";
		boolean found = false;
		Properties properties = new Properties();
		
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(filePath));
			properties.load(stream);
			for (Object k : properties.keySet()) {
				if (k.toString().equals(key)) {
					result = properties.getProperty(key);
					found = true;
				}
			}
			if (!found) {
				throw new NameNotFoundException();
			}
			stream.close();
		} catch (IOException | NameNotFoundException e) {
			result = "Not found";
			e.printStackTrace();
		}		

		return result;
	}

	/**
	 * Returns a name if the key, given by parameter, is found in the file.
	 * <br> If the {@link ElementNameIOLoader} can not find the key, it will return "Not found".
	 * @param eventTag : The key that should be looked for.
	 * @param filePath : Path to file containing the names. 
	 * @return The name that was looked for. If the key couldn't be found it will return "Not found".
	 */
	public static String getEventName(String eventTag, String filePath) {
		return getName(eventTag, filePath);
	}
}