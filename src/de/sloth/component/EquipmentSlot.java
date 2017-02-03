package de.sloth.component;

/**
 * Definition of a <b>EquipmentSlot</b>.
 * <br> A <b>EquipmentSlot</b> defines a slot where <b>Equip</b> can be placed in.
 * @author Kevin Jolitz
 * @author Joshua Ward
 */
public enum EquipmentSlot {
	HELMET, ARMOR, BOOTS, SHIELD, RIGHT_HAND;

	/**
	 * This function takes the name of the <b>EquipmentSlot</b> and changes it to
	 * standard text. 
	 * <br> Format is: <i>1. letter uppercase and rest lowercase.</i>
	 * @return the name of the type as standard text
	 */
	public String toName() {
		return name().substring(0, 1) + name().substring(1).toLowerCase();
	}
}
