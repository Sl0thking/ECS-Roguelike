package de.sloth.component;

public class HealComp extends Component {
	int healValue;
	
	public HealComp(int value) {
		this.healValue = value;
	}

	public int getHealValue() {
		return healValue;
	}
	
	public void setHealValue(int healValue) {
		this.healValue = healValue;
	}
}
