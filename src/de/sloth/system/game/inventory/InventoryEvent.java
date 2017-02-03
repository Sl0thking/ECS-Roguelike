package de.sloth.system.game.inventory;

import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;

public class InventoryEvent extends GameEvent {
	int xPos;
	int yPos;
	Entity collectedEntity;
	
	public InventoryEvent(String keyword) {
		super(keyword);
	}
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public Entity getCollectingEntity() {
		return collectedEntity;
	}
	
	public void setCollectingEntity(Entity entity) {
		this.collectedEntity = entity;
	}
}