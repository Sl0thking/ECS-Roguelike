package de.sloth.generators;

import java.util.Random;

import de.sloth.component.EquipableComp;
import de.sloth.component.EquipmentRarity;
import de.sloth.component.EquipmentSlot;
import de.sloth.component.LootableComp;
import de.sloth.entity.Entity;
import de.sloth.persistence.gameConstants.GameObjectConstProvider;

/**
 * Definition of a {@linkplain LootGenerator}
 * <br> The {@linkplain LootGenerator} generates loot based on the current surfaceLvl.
 * <br> It can generate random {@linkplain Equip} and {@linkplain BuffPotion}. 
 
 * @author Kevin Jolitz
 * @author Joshua Ward
 * @version 31.05.2015
 */
public class LootGenerator extends GameObjectGenerator {

	/**
	 * Constant filepath for standard {@linkplain Equip} names used by {@linkplain GameElementNameGenrator}.
	 */
	private static final String EQUIP_NAME_FILE_PATH = "./names/equip_names/names";
	
	/**
	 * Constant filepath for legendary armor names used by {@linkplain GameElementNameGenerator}.
	 */
	private static final String EQUIP_LEGENDARY_ARMOR_FILE_PATH = "./names/equip_names/legendary_armor";
	
	/**
	 * Constant filepath for weapons names used by {@linkplain GameElementNameGenrator}.
	 */
	private static final String EQUIP_WEAPON_FILE_PATH = "./names/equip_names/weapons";
	
	/**
	 * Constant filepath for legendary weapon names used by {@linkplain GameElementNameGenerator}.
	 */
	private static final String EQUIP_LEGENDARY_WEAPON_FILE_PATH = "./names/equip_names/legendary_weapons";
	
	/**
	 * Constant filepath for {@linkplain Equip} suffixes used by {@linkplain GameElementNameGenrator}.
	 */
	private static final String EQUIP_SUFFIX_FILE_PATH = "./names/equip_names/suffixes";
	
	/**
	 * Constant filepath for {@linkplain BuffPotion} names used by {@linkplain GameElementNameGenrator}.
	 */
	private static final String BUFFPOTION_NAME_FILE_PATH = "./gameRessources/game_configs/buffpotion_names/names";
	
	/**
	 * Constant filepath for {@linkplain BuffPotion} suffixes used by {@linkplain GameElementNameGenrator}.
	 */
	private static final String BUFFPOTION_SUFFIX_FILE_PATH = "./gameRessources/game_configs/buffpotion_names/suffixes";
	
	/**
	 * Constant filepath for standard names used by {@linkplain GameElementNameGenrator}.
	 */
	private static final String NAME_PATH = "./gameRessources/game_configs/languages/strings";
	
	/**
	 * The singleton instance of {@linkplain LootGenerator}.
	 */
	private static LootGenerator instance;
	
	private LootGenerator(){}
	
	/**
	 * Returns the instance of the loot generator
	 * @return instance
	 */
	public static LootGenerator getInstance() {
		if(instance == null) {
			instance = new LootGenerator();
		}
		return instance;
	}
	
	/**
	 * Generates a {@linkplain Equip} based on the current surfaceLvl.
	 * @param surfaceLvl : The current surfaceLvl of the dungeon.
	 * @return A single piece of {@linkplain Equip} with a random name.
	 */
	public Entity generateEquipment(int surfaceLvl) {
		Entity equip = new Entity();
		EquipableComp equipComp = new EquipableComp();
		LootableComp lComp = new LootableComp();
		lComp.setImage("file:./sprites/sword.png");
		
		equipComp.setRarity(getRandomRarity());
		equipComp.setSlot(EquipmentSlot.values()[new Random().nextInt(EquipmentSlot.values().length)]);
		
		int rarityBonus = 0;
		if (equipComp.getRarity().equals(EquipmentRarity.LEGENDARY)) {
			rarityBonus = 2;
		} else if (equipComp.getRarity().equals(EquipmentRarity.RARE)) {
			rarityBonus = 1;
		}

		//generate random equip name via NameLoader
		String name = "", suffix = "";
		if (equipComp.getSlot().equals(EquipmentSlot.RIGHT_HAND)) {
			int damageBonus = Integer.parseInt(this.loadProperty("equip_dmg_bonus"));
			equipComp.setDmgBonus(damageBonus * (surfaceLvl + rarityBonus));
			if (equipComp.getRarity().equals(EquipmentRarity.LEGENDARY)) {
				name = this.generateObjectName(EQUIP_LEGENDARY_WEAPON_FILE_PATH);
			} else {
				name = this.generateObjectName(EQUIP_WEAPON_FILE_PATH);
			}
		} else {
			int armorBonus = Integer.parseInt(this.loadProperty("equip_armor_bonus"));
			equipComp.setArmorBonus(armorBonus * (surfaceLvl + rarityBonus));
			if (equipComp.getRarity().equals(EquipmentRarity.LEGENDARY)) {
				name = this.generateObjectName(EQUIP_LEGENDARY_ARMOR_FILE_PATH);
			} else {
				name = this.getObjectName(equipComp.getSlot().toString().toUpperCase(), EQUIP_NAME_FILE_PATH);
			}
		}
		if (!equipComp.getRarity().equals(EquipmentRarity.LEGENDARY))
			suffix = this.generateObjectName(EQUIP_SUFFIX_FILE_PATH);
		
		lComp.setGoldBounty(equipComp.getArmorBonus() + equipComp.getDmgBonus());
		lComp.setName(name + " " + suffix);
		//lComp.setSymbol(this.loadProperty("equip_symbol"));	
		equip.addComponent(equipComp);
		equip.addComponent(lComp);
		return equip;
	}
	
	/**
	 * Generates a {@linkplain BuffPotion} based on the current surfaceLvl.
	 * @param surfaceLvl : The current surfaceLvl of the dungeon.
	 * @return A {@linkplain BuffPotion} with a random {@linkplain State}.
	 
	public BuffPotion generateBuffPotion(int surfaceLvl) {
		BuffPotion buffPotion = new BuffPotion();
		int amount = Integer.parseInt(this.loadProperty("buffpotion_baseamount")) * surfaceLvl;
		String name = this.generateObjectName(BUFFPOTION_NAME_FILE_PATH);
		
		State state = new State();		
		int minDuration = Integer.parseInt(this.loadProperty("buffpotion_min_duration"));
		int maxDuration = Integer.parseInt(this.loadProperty("buffpotion_max_duration"));
		state.setDurationInTurns(new Random().nextInt((maxDuration - minDuration) + 1) + minDuration);
				
		switch (new Random().nextInt(4)) {
			case 0:
				buffPotion.setName(name + " " + this.getObjectName("STRENGTH", BUFFPOTION_SUFFIX_FILE_PATH));
				state.setType(StateType.STRENGTH);
				state.setDmgBonus(amount);
				break;
			case 1:
				buffPotion.setName(name + " " + this.getObjectName("ARMOR", BUFFPOTION_SUFFIX_FILE_PATH));
				state.setType(StateType.ARMOR);
				state.setArmorBonus(amount);
				break;
			case 2:
				buffPotion.setName(name + " " + this.getObjectName("HEALTH", BUFFPOTION_SUFFIX_FILE_PATH));
				state.setType(StateType.HEALTH);
				state.setMaxHpBonus(amount);
				break;
			case 3:	
				buffPotion.setName(name + " " + this.getObjectName("RAVE", BUFFPOTION_SUFFIX_FILE_PATH));
				state.setType(StateType.RAVE);
				break;
		}

		buffPotion.setState(state);
		buffPotion.setGoldBounty(buffPotion.getState().getMaxHpBonus() 
							   + buffPotion.getState().getArmorBonus()
							   + buffPotion.getState().getDmgBonus()
							   + buffPotion.getState().getHpDmg());
		
		buffPotion.setSymbol(this.loadProperty("buffpotion_symbol"));
		return buffPotion;
	}*/
	
	/**
	 * Determines whether a {@linkplain BuffPotion} is dropping or not.
	 * <br> It uses a pre defined chance from the {@linkplain GameObjectConstProvider}.
	 * @return boolean : True if a {@linkplain BuffPotion} is dropped.
	 */
	public boolean isBuffPotionDropped() {
		if(new Random().nextInt(99) <= Integer.parseInt(this.loadProperty("healpotion_chance"))) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines whether a healpotion is dropping or not.
	 * <br> It uses a pre defined chance from the {@linkplain GameObjectConstProvider}.
	 * @return boolean : True if a healpotion is dropped.
	 */
	public boolean isHealPotionDropped() {
		if(new Random().nextInt(99) <= Integer.parseInt(this.loadProperty("healpotion_chance"))) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns a randomly selected {@linkplain EquipmentRarity} based on their dropchance.
	 * @return A random {@linkplain EquipmentRarity}.
	 */
	private EquipmentRarity getRandomRarity() {
		int random = new Random().nextInt(99);
		if (random <= Integer.parseInt(this.loadProperty("equip_legendary_chance"))) {
			return EquipmentRarity.LEGENDARY;
		} else if (random <= Integer.parseInt(this.loadProperty("equip_rare_chance"))) {
			return EquipmentRarity.RARE;
		} else {
			return EquipmentRarity.COMMON;
		}
	}
}
