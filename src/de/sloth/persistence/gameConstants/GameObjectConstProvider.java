package de.sloth.persistence.gameConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A collection of all necessary gameObject constants of the game.
 * We recommended not to edit this file, instead use the modding file,
 * which this class can create, basing on our basic configuration.
 * 
 * @author Kevin Jolitz
 * @author Joshua Ward
 * @version 31.05.2015
 */
@SuppressWarnings("serial")
public class GameObjectConstProvider extends Properties implements IConstsProvider{
	
	//Hero consts
	private static final String HERO_SYMBOL = "H";
	private static final String HERO_MAX_HP = "35";
	private static final String HERO_MIN_DMG = "3";
	private static final String HERO_MAX_DMG = "5";
	private static final String HERO_BASE_ARMOR = "0";
	private static final String HERO_MAX_XP = "100";
	private static final String HERO_SCALING = "1.2";
	private static final String HERO_POTION_SCALING = "0.33";
	private static final String HERO_POTION_COUNT = "1";
	
	//Enemy consts
	private static final String ENEMY_SYMBOL = "M";
	private static final String ENEMY_BASEHP = "25";
	private static final String ENEMY_MIN_DMG = "4";
	private static final String ENEMY_MAX_DMG = "5";
	private static final String ENEMY_BASE_ARMOR = "1"; 
	private static final String ENEMY_EXP_BOUNTY = "20";
	
	//Tower consts
	private static final String TOWER_SYMBOL = "T";
	private static final String TOWER_RANGE = "5";
	private static final String TOWER_BASEHP = "25";
	private static final String TOWER_MIN_DMG = "6";
	private static final String TOWER_MAX_DMG = "6";
	private static final String TOWER_BASE_ARMOR = "3"; 
	
	//Boss consts
	private static final String BOSS_SYMBOL = "B";
	private static final String BOSS_BASEHP = "100";
	private static final String BOSS_MIN_DMG = "8";
	private static final String BOSS_MAX_DMG = "10";
	private static final String BOSS_BASE_ARMOR = "2";
	
	//Merchant consts
	private static final String MERCHANT_SYMBOL = "$";
	private static final String MERCHANT_BASE_COST = "5";
	
	//Chest consts
	private static final String CHEST_SYMBOL = "Q";
	
	//Door consts
	private static final String DOOR_SYMBOL_HORIZONTAL = "|";
	private static final String DOOR_SYMBOL_VERTICAL = "-";
	private static final String BOSS_DOOR_SYMBOL = "=";

	//Wall consts
	private static final String WALL_SYMBOL = "#";
	
	//Trap consts
	private static final String TRAP_SYMBOL = "X";
	private static final String TRAP_BASE_DMG = "4";
	
	//Stair consts
	private static final String STAIR_SYMBOL = ">";
	
	//Equip consts
	private static final String EQUIP_SYMBOL = "%";
	private static final String EQUIP_DMG_BONUS = "2";
	private static final String EQUIP_ARMOR_BONUS = "1";
	private static final String EQUIP_RARE_CHANCE = "30";
	private static final String EQUIP_LEGENDARY_CHANCE = "10";
	
	//BuffPotion consts
	private static final String BUFFPOTION_SYMBOL = "?";
	private static final String BUFFPOTION_CHANCE = "25";
	private static final String BUFFPOTION_MIN_DURATION = "20";
	private static final String BUFFPOTION_MAX_DURATION = "30";
	private static final String BUFFPOTION_BASEAMOUNT = "2";
	
	//HealPotion consts
	private static final String HEALPOTION_SYMBOL = "~";
	private static final String HEALPOTION_CHANCE = "33";
	
	/**
	 * Constructor
	 * Sets all constants into a hash map for the properties.
	 */
	public GameObjectConstProvider() {
		//Hero consts
		this.setProperty("hero_symbol", HERO_SYMBOL);
		this.setProperty("hero_max_hp", HERO_MAX_HP);
		this.setProperty("hero_min_dmg", HERO_MIN_DMG);
		this.setProperty("hero_max_dmg", HERO_MAX_DMG);
		this.setProperty("hero_base_armor", HERO_BASE_ARMOR);
		this.setProperty("hero_scaling", HERO_SCALING);
		this.setProperty("hero_max_xp", HERO_MAX_XP);
		this.setProperty("hero_potion_scaling", HERO_POTION_SCALING);
		this.setProperty("hero_potion_count", HERO_POTION_COUNT);
	
		//Chest consts
		this.setProperty("chest_symbol", CHEST_SYMBOL);
		
		//Enemy consts
		this.setProperty("enemy_symbol", ENEMY_SYMBOL);
		this.setProperty("enemy_basehp", ENEMY_BASEHP);
		this.setProperty("enemy_min_dmg", ENEMY_MIN_DMG);
		this.setProperty("enemy_max_dmg", ENEMY_MAX_DMG);
		this.setProperty("enemy_base_armor", ENEMY_BASE_ARMOR);
		this.setProperty("enemy_exp_bounty", ENEMY_EXP_BOUNTY);
		
		//Tower consts
		this.setProperty("tower_symbol", TOWER_SYMBOL);
		this.setProperty("tower_range", TOWER_RANGE);
		this.setProperty("tower_basehp", TOWER_BASEHP);
		this.setProperty("tower_min_dmg", TOWER_MIN_DMG);
		this.setProperty("tower_max_dmg", TOWER_MAX_DMG);
		this.setProperty("tower_base_armor", TOWER_BASE_ARMOR);
		
		//Boss consts
		this.setProperty("boss_symbol", BOSS_SYMBOL);
		this.setProperty("boss_basehp", BOSS_BASEHP);
		this.setProperty("boss_min_dmg", BOSS_MIN_DMG);
		this.setProperty("boss_max_dmg", BOSS_MAX_DMG);
		this.setProperty("boss_base_armor", BOSS_BASE_ARMOR);
		
		//Merchant consts
		this.setProperty("merchant_symbol", MERCHANT_SYMBOL);
		this.setProperty("merchant_base_cost", MERCHANT_BASE_COST);
		
		//Door consts
		this.setProperty("door_symbol_horizontal", DOOR_SYMBOL_HORIZONTAL);
		this.setProperty("door_symbol_vertical", DOOR_SYMBOL_VERTICAL);
		this.setProperty("boss_door_symbol", BOSS_DOOR_SYMBOL);
		
		//Wall consts
		this.setProperty("wall_symbol", WALL_SYMBOL);
		
		//Trap consts
		this.setProperty("trap_symbol", TRAP_SYMBOL);
		this.setProperty("trap_base_dmg", TRAP_BASE_DMG);
		
		//Stair consts
		this.setProperty("stair_symbol", STAIR_SYMBOL);
		
		//Equip consts
		this.setProperty("equip_symbol", EQUIP_SYMBOL);
		this.setProperty("equip_dmg_bonus", EQUIP_DMG_BONUS);
		this.setProperty("equip_armor_bonus", EQUIP_ARMOR_BONUS);
		this.setProperty("equip_rare_chance", EQUIP_RARE_CHANCE);
		this.setProperty("equip_legendary_chance", EQUIP_LEGENDARY_CHANCE);
	
		//BuffPotion consts
		this.setProperty("buffpotion_symbol", BUFFPOTION_SYMBOL);	
		this.setProperty("buffpotion_chance", BUFFPOTION_CHANCE);
		this.setProperty("buffpotion_min_duration", BUFFPOTION_MIN_DURATION);
		this.setProperty("buffpotion_max_duration", BUFFPOTION_MAX_DURATION);
		this.setProperty("buffpotion_baseamount", BUFFPOTION_BASEAMOUNT);
		
		//HealPotion consts
		this.setProperty("healpotion_symbol", HEALPOTION_SYMBOL);
		this.setProperty("healpotion_chance", HEALPOTION_CHANCE);
		
		//Chest consts
		this.setProperty("chest_symbol", CHEST_SYMBOL);
	}
	
	/**
	 * Returns the property-value to the given property-key.
	 * @param constName - property-key to get the property-value
	 * @return the property-value to the given key
	 */
	public String getDataObject(String constName) {
		return this.getProperty(constName);
	}
	
	/**
	 * Generates a new property-file and stores the constants of the GameObjects.
	 * !!! Violation of our architecture, next sprint moving this into other class on persistence lvl !!! 
	 */
	public void generateNewObjectPropertiesFile() {
		String path = "." + File.separator + "gameRessources" + File.separator + "game_configs" + File.separator +"gameObjectConst.properties";
		File propertyFile = new File(path);
		try {
			propertyFile.createNewFile();
			this.store(new FileOutputStream(path), "WUPPI CONST");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}