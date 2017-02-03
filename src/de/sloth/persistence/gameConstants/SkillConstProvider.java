package de.sloth.persistence.gameConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Handles the skillConst.properties file.
 * All constants for the skills are defined in the class.
 * @author Kevin Wesseler
 * @version 28.05.2015
 */
public class SkillConstProvider extends Properties implements IConstsProvider {

	private static final long serialVersionUID = -7040158402261391069L;
	//DAMAGE_SKILL
	private static final String EXTRA_DAMAGE_SCALING = "1.5";
	private static final String DAMAGE_SKILL_COOLDOWN = "4";
	//RANGE_SKILL
	private static final String RANGE_SKILL_RANGE = "4";
	private static final String RANGE_DAMAGE_SCALING = "1.0";
	private static final String RANGE_SKILL_COOLDOWN = "3";
	//AoE_SKILL
	private static final String AOE_SKILL_RANGE = "2";
	private static final String AOE_DAMAGE_SCALING = "1.0";
	private static final String AOE_SKILL_COOLDOWN = "3";
	
	/**
	 * Constructor
	 * Put the property key together with the value.
	 */
	public SkillConstProvider() {
		this.setProperty("extra_damage_scaling", EXTRA_DAMAGE_SCALING);
		this.setProperty("damage_skill_cooldown", DAMAGE_SKILL_COOLDOWN);
		
		this.setProperty("range_skill_range", RANGE_SKILL_RANGE);
		this.setProperty("range_damage_scaling", RANGE_DAMAGE_SCALING);
		this.setProperty("range_skill_cooldown", RANGE_SKILL_COOLDOWN);
		
		this.setProperty("aoe_skill_range", AOE_SKILL_RANGE);
		this.setProperty("aoe_damage_scaling", AOE_DAMAGE_SCALING);
		this.setProperty("aoe_skill_cooldown", AOE_SKILL_COOLDOWN);
	}
	
	/**
	 * Searches for the property with the specified key in this property list.
	 * @param constName - the key to search the value
	 */
	@Override
	public String getDataObject(String constName) {
			return this.getProperty(constName);
	}

	/**
	 * Load the property file. When file can't be found it creates a new file.
	 * If the file can't be created a massage will be prompt into the console.
	 * 
	 * When the file creation hasn't failed it will store the constants in the property file.
	 */
	@Override
	public void generateNewObjectPropertiesFile() {
		String path = "." + File.separator + "gameRessources" + File.separator + "game_configs" 
				+ File.separator +"skill_consts" + File.separator + "skillConst.properties";
		File propertyFile = new File(path);
		try {
			propertyFile.createNewFile();
			this.store(new FileOutputStream(path), "WUPPI SKILL CONST");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}