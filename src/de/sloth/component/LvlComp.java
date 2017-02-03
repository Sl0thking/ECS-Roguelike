package de.sloth.component;

import javafx.beans.property.SimpleIntegerProperty;

public class LvlComp extends Component {

	private SimpleIntegerProperty lvl;
	private SimpleIntegerProperty maxExp;
	private SimpleIntegerProperty currExp;
	
	public LvlComp() {
		lvl = new SimpleIntegerProperty(1);
		maxExp = new SimpleIntegerProperty(50);
		currExp = new SimpleIntegerProperty(0);
	}

	public SimpleIntegerProperty getLvl() {
		return lvl;
	}

	public void setLvlProperty(SimpleIntegerProperty lvl) {
		this.lvl = lvl;
	}
	
	public void setLvl(int lvl) {
		this.lvl.setValue(lvl);
	}

	public SimpleIntegerProperty getMaxExp() {
		return maxExp;
	}

	public void setMaxExpProperty(SimpleIntegerProperty maxExp) {
		this.maxExp = maxExp;
	}
	
	public void setMaxExp(int maxExp) {
		this.maxExp.setValue(maxExp);
	}

	public SimpleIntegerProperty getCurrExp() {
		return currExp;
	}

	public void setCurrExpProperty(SimpleIntegerProperty currExp) {
		this.currExp = currExp;
	}
	
	public void setCurrExp(int currExp) {
		this.currExp.setValue(currExp);
	}
	
	public void gainExp(int exp) {
		this.currExp.setValue(this.currExp.getValue() + exp);
		if(this.currExp.getValue() >= this.maxExp.getValue()) {
			this.lvl.setValue(this.lvl.getValue() + 1);
			this.currExp.setValue(0);
			this.maxExp.setValue(this.maxExp.getValue() * 1.5);
		}
	}

}
