package de.sloth.generators;

import de.sloth.persistence.gameConstants.ElementNameIOLoader;

/**
 * Definition of GameElementNameGenerator
 * <br> {@link GameObject} name provider, for random generating or loading
 * a specific name for a {@link GameObject} or a {@link GameEvent} name.
 * <br> The GameElementNameGenerator uses a {@link ElementNameIOLoader} to load the names.
 * 
 * @author Kevin Jolitz
 * @author Joshua Ward
 * @version 01.06.2015
 */
public class GameElementNameGenerator {
	
	/**
	 * Current {@link Language}. 
	 * <br> The {@link ElementNameIOLoader} will load a name depending on the current {@link Language}.
	 */
	private static Language language = Language.GERMAN;
	
	/**
	 * Returns a name which is randomly selected by using {@link ElementNameIOLoader#getRandomName(String)}.
	 * @param filePath : Path to file containing the names.
	 * @return A randomly selected name.
	 */
	public static String generateObjectName(String filePath) {
		return ElementNameIOLoader.getRandomName(filePath + "_" + language.toString().toLowerCase() + ".txt");
	}
	
	/**
	 * Returns a name if the key, given by parameter, is found in the file using
	 * {@link ElementNameIOLoader#getName(String, String)}.
	 * <br> If the {@link ElementNameIOLoader} can not find the key, it will return "Not found".
	 * @param key : The key to the name that should be looked for.
	 * @param filePath : Path to file containing the names. 
	 * @return The name that was looked for. If the key couldn't be found it will return "Not found".
	 */
	public static String getObjectName(String key, String filePath) {
		return ElementNameIOLoader.getName(key, filePath + "_" + language.toString().toLowerCase() + ".properties");		
	}
	
	/**
	 * Returns a name if the key, given by parameter, is found in the file using
	 * {@link ElementNameIOLoader#getEventName(String, String)}.
	 * <br> If the {@link ElementNameIOLoader} can not find the key, it will return "Not found".
	 * @param interaction : The {@link GameEvent} that should be looked for.
	 * @param filePath : Path to file containing the names. 
	 * @return The name that was looked for. If the key couldn't be found it will return "Not found".
	 
	public static String getEventName(GameEvent interaction, String filePath) {
		return ElementNameIOLoader.getEventName(interaction.name(), filePath + "_" + language.toString().toLowerCase() + ".properties");	
	}*/
	
	/**
	 * Setter for language
	 * @param language Current {@link Language}. The {@link ElementNameIOLoader} will load 
	 * a name depending on the current {@link Language}.
	 */
	public static void setLanguage(Language language) {
		GameElementNameGenerator.language = language;
	}
}