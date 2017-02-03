package de.sloth.system.game.move;

import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;

public class MoveEvent extends GameEvent {

	private int targetX;
	private int targetY;
	private int targetZ;
	private Entity srcEntity;
	
	public MoveEvent() {
		this.targetX = 0;
		this.targetY = 0;
		this.targetZ = 0;
	}

	public int getTargetX() {
		return targetX;
	}

	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}

	public int getTargetY() {
		return targetY;
	}

	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}

	public Entity getSrcEntity() {
		return srcEntity;
	}

	public void setSrcEntity(Entity srcEntity) {
		this.srcEntity = srcEntity;
	}

	public int getTargetZ() {
		// TODO Auto-generated method stub
		return this.targetZ;
	}
	
	public void setTargetZ(int targetZ) {
		this.targetZ = targetZ;
	}
	
	
}
