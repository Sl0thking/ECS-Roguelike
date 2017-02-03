package de.sloth.persistence.gameConstants;

/**
 * Standard interface for a ConstClass of our game.
 * @author Kevin Jolitz
 * @version 31.05.2015
 *
 */
public interface IConstsProvider {
	/**
	 * Returns constant of an desired field.
	 * @param constName Name of the desired data
	 * @return String of the desired constant.
	 */
	public String getDataObject(String constName);
	
	/**
	 * Generate new editable file, basing on standard constant
	 */
	public void generateNewObjectPropertiesFile();
}
