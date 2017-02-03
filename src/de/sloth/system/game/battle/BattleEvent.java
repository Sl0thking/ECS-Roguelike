package de.sloth.system.game.battle;

import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;

public class BattleEvent extends GameEvent {
	private Entity collisionSrc;
	private Entity collisionTarget;
	

	public BattleEvent(Entity collisionSrc, Entity collisionTarget) {
		this.collisionSrc = collisionSrc;
		this.collisionTarget = collisionTarget;
	}
	
	public Entity getCollisionSrc() {
		return collisionSrc;
	}

	public void setCollisionSrc(Entity collisionSrc) {
		this.collisionSrc = collisionSrc;
	}

	public Entity getCollisionTarget() {
		return collisionTarget;
	}

	public void setCollisionTarget(Entity collisionTarget) {
		this.collisionTarget = collisionTarget;
	}
}
