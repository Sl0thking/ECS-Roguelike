package de.sloth.persistence.gameConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Property Class for loading internal data for different gameObjects.
 * Return value is always string, when numeric values are used casting is necessary.
 * 
 * When a property is not found, a standard value is 
 * loaded from a internal constant list (GameObjectConsts / SkillConsts).
 * 
 * @author Kevin Jolitz
 * @author Kevin Wesseler
 * @version 31.05.2015
 */
public class ConstFileLoader extends Properties {

	private static final long serialVersionUID = 8225091880636995521L;
	private String propertiesPath;
	private IConstsProvider constClass;
	private static boolean useProperties = true;
	
	/**
	 * Constructor
	 * @param propertiesPath Path to the properties file.
	 * @param constClass Responsible constant file.
	 */
	public ConstFileLoader(String propertiesPath, IConstsProvider constClass) {
		this.propertiesPath = propertiesPath;
		this.constClass = constClass;
		loadProperties();
	}
	
	/**
	 * Returns the String of the constant out of the property file.
	 * @param constString
	 * @return constant out of the property file
	 */
	public String getObjectData(String constString) {
		if(useProperties) {
			return this.getProperty(constString, constClass.getDataObject(constString));
		} else {
			return constClass.getDataObject(constString);
		}
	}
	
	/**
	 * Generate a new property file with the constant out of the constClass and stores it into the
	 * property file by using {@code loadProperties()}.
	 */
	public void generateNewPropertiesFile() {
		constClass.generateNewObjectPropertiesFile();
		loadProperties();
	}
	
	/**
	 * Load the property file. When file can't be found it creates a new file.
	 * If the file can't be created a massage will be prompt into the console.
	 * 
	 * When the file creation hasn't failed it will store the constants in the property file. 
	 */
	private void loadProperties() {
		try {
			this.load(new FileInputStream(propertiesPath));
		} catch (FileNotFoundException e) {
			File propertyFile = new File(propertiesPath);
			try {
				propertyFile.createNewFile();
				this.store(new FileOutputStream(propertiesPath), "WUPPI CONST");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Handles the use of the property file. Connected to the MainMenu - use CostumMode.
	 * @param bool - {@code true} default value 
	 */
	public static void useConstFile(boolean bool) {
		ConstFileLoader.useProperties = bool;
	}
}