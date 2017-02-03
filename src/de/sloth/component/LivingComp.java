package de.sloth.component;

import javafx.beans.property.SimpleIntegerProperty;

public class LivingComp extends Component {
	private boolean isLiving;
	private SimpleIntegerProperty hp;
	private SimpleIntegerProperty hpMax;
	private SimpleIntegerProperty attack_min;
	private SimpleIntegerProperty attack_max;
	private SimpleIntegerProperty defense;

	public int getHp() {
		return hp.getValue();
	}

	public void setHp(int hp) {
		this.hp.setValue(hp);
	}

	public int getHpMax() {
		return hpMax.getValue();
	}

	public void setHpMax(int hpMax) {
		this.hpMax.setValue(hpMax);
	}
	
	public LivingComp(boolean isLiving) {
		super();
		this.isLiving = isLiving;
		this.hp = new SimpleIntegerProperty();
		this.hp.setValue(10);
		this.hpMax = new SimpleIntegerProperty();
		this.hpMax.setValue(10);
		this.attack_min = new SimpleIntegerProperty(1);
		this.attack_max = new SimpleIntegerProperty(3);
		this.defense = new SimpleIntegerProperty(1);
	}

	public boolean isLiving() {
		return isLiving;
	}

	public void setLiving(boolean isLiving) {
		this.isLiving = isLiving;
	}

	@Override
	public String toString() {
		return "LivingComp [isLiving=" + isLiving + ", hp=" + hp + ", hpMax=" + hpMax 
				+ ", defense=" + defense + "]";
	}

	public SimpleIntegerProperty getHpProperty() {
		return hp;
	}

	public SimpleIntegerProperty getHpMaxProperty() {
		return hpMax;
	}

	public SimpleIntegerProperty getAttackMin() {
		return attack_min;
	}

	public void setAttackMin(int attack_min) {
		this.attack_min.setValue(attack_min);
	}

	public SimpleIntegerProperty getAttackMax() {
		return attack_max;
	}

	public void setAttackMax(int attack_max) {
		this.attack_max.setValue(attack_max);
	}

	public SimpleIntegerProperty getDefenseProperty() {
		return defense;
	}
	
	public int getDefense() {
		return defense.getValue();
	}

	public void setDefenseProperty(SimpleIntegerProperty defense) {
		this.defense = defense;
	}

	public void setDefense(int defense) {
		this.defense.setValue(defense);
	}
	
	
	
}
