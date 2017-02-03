package de.sloth.component;

public class EquipableComp extends Component {

	private EquipmentRarity rarity;
	private EquipmentSlot eSlot;
	private int dmgBonus;
	private int armorBonus;
	
	public EquipableComp() {
		rarity = EquipmentRarity.COMMON;
		eSlot = EquipmentSlot.HELMET;
		dmgBonus = 0;
		armorBonus = 0;
	}

	public void setRarity(EquipmentRarity rarity) {
		this.rarity = rarity;		
	}

	public EquipmentRarity getRarity() {
		return this.rarity;
	}
	
	public void setSlot(EquipmentSlot equipmentSlot) {
		this.eSlot = equipmentSlot;		
	}

	public EquipmentSlot getSlot() {
		return this.eSlot;
	}

	public void setDmgBonus(int dmgBonus) {
		this.dmgBonus = dmgBonus;
	}
	
	public int getDmgBonus() {
		return this.dmgBonus;
	}
	
	public void setArmorBonus(int armorBonus) {
		this.armorBonus = armorBonus;
	}

	public int getArmorBonus() {
		return this.armorBonus;
	}
}
