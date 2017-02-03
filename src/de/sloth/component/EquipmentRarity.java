package de.sloth.component;

/**
 * Definition of a {@linkplain EquipmentRarity}.
 * <br> {@linkplain EquipmentRarity} is a description of how rare the {@linkplain Equip} is.
 * @author Joshua Ward
 */
public enum EquipmentRarity {
	COMMON, RARE, LEGENDARY;
	
	/**
	 * This function takes the name of the {@linkplain EquipmentRarity} and changes it to
	 * standard text. 
	 * <br> Format is: <i>1. letter uppercase and rest lowercase.</i>
	 * @return the name of the type as standard text.
	 */
	public String toName() {
		return name().substring(0, 1) + name().substring(1).toLowerCase();
	}
}
