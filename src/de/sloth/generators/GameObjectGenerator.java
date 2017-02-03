package de.sloth.generators;

import de.sloth.persistence.gameConstants.ConstFileLoader;
import de.sloth.persistence.gameConstants.GameObjectConstProvider;

/**
 * Abstract class of a gameObjectGenerator.
 * Implements the necessary ConstFileLoader and provides important
 * interfaces for loading names/gameObject data.
 * 
 * @author Kevin Jolitz
 * @version 31.05.2015
 */
public abstract class GameObjectGenerator {
	
	private static ConstFileLoader constFileLoader;
	private static final String GAME_CONSTS_PATH = ".";
	
	/**
	 * Constructor
	 */
	public GameObjectGenerator() {
		super();
		generatePropLoader();
	}

	/**
	 * Initialize a {@link PropertyLoader}, if the current instance is null.
	 */
	private void generatePropLoader() {
		if(constFileLoader == null) {
			GameObjectGenerator.constFileLoader = new ConstFileLoader(GAME_CONSTS_PATH, new GameObjectConstProvider());
		}
	}

	/**
	 * Returns the property-value.
	 * @param gameObjectProp - key to the values in the property-file.
	 * @return the value our of the property-file
	 */
	public String loadProperty(String gameObjectProp) {
		return constFileLoader.getObjectData(gameObjectProp);
	}
	
	/**
	 * Returns a random name of the GameObject in the chosen language.
	 * @param path - path to the file, without the '_language'
	 * @return random name of the GameObject
	 */
	public String generateObjectName(String path) {
		return GameElementNameGenerator.generateObjectName(path);
	}
	
	/**
	 * Returns the name of the GameObject in the chosen language out of the
	 * property-file in the chosen language.
	 * @param key - key for the property-file
	 * @param filePath - path to the property-file, without the '_language'
	 * @return name of the GameObject
	 */
	public String getObjectName(String key, String filePath) {
		return GameElementNameGenerator.getObjectName(key, filePath);
	}
	
	/**
	 * Give access to the {@code generateNewPropertiesFile()} in {@link PropertyLoader}.
	 * Generate a new property file with the constant out of the constClass and stores
	 * it into the property file by using loadProperties().
	 */
	public static void generateNewProperties() {
		constFileLoader.generateNewPropertiesFile();
	}
}