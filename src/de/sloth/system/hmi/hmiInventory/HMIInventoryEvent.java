package de.sloth.system.hmi.hmiInventory;

import de.sloth.system.game.core.GameEvent;

public class HMIInventoryEvent extends GameEvent {
	int posX;
	int posY;
	
	public HMIInventoryEvent(String keyword) {
		super(keyword);
		this.posX = 0;
		this.posY = 0;
	}
	
	public HMIInventoryEvent(String keyword, int posX, int posY) {
		super(keyword);
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}