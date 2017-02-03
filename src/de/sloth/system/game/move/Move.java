package de.sloth.system.game.move;

import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.collision.CollisionEvent;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class Move implements IBehavior {

	private int maxX;
	private int maxY;
	
	public Move(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		MoveEvent movEvent = (MoveEvent) expectedEvent;
		Entity srcEntity = movEvent.getSrcEntity();
		Position3DComp posComp = (Position3DComp) srcEntity.getComponent(Position3DComp.class);
		SpriteComp sComp = (SpriteComp) srcEntity.getComponent(SpriteComp.class);
		boolean isCollided = checkCollision(system, srcEntity, movEvent);
		if(movEvent.getTargetX() >= 0 && movEvent.getTargetX() < maxX &&
		   movEvent.getTargetY() >= 0 && movEvent.getTargetY() < maxY &&
			  !isCollided) {
			if(sComp.getDirection() == "right" && movEvent.getTargetX() < posComp.getX()) {
				sComp.setDirection("left");
				sComp.setSpritePath(srcEntity.getName() + "_left.png");
			} else if(sComp.getDirection() == "left" && movEvent.getTargetX() > posComp.getX()) {
				sComp.setDirection("right");
				sComp.setSpritePath(srcEntity.getName() + "_right.png");
			}
			posComp.setX(movEvent.getTargetX());
			posComp.setY(movEvent.getTargetY());
			system.getEntityManager().removeEntity(srcEntity);
			system.getEntityManager().addEntity(srcEntity);
		}
	}
	
	private boolean checkCollision(GameSystem system, Entity srcEntity, MoveEvent movEvent) {
		for(Entity entity : system.getEntityManager().getChunkEntities()) {
			if(entity.getId() != srcEntity.getId()) {
				Position3DComp posComp = (Position3DComp) entity.getComponent(Position3DComp.class);
				if(posComp != null && posComp.getX() == movEvent.getTargetX() && 
				   posComp.getY() == movEvent.getTargetY() &&
				   posComp.getZ() == movEvent.getTargetZ()) {
					GameEvent collision = new CollisionEvent(srcEntity, entity);
					system.getEventQueue().add(collision);
					return true;
				}
			}
		}
		return false;
	}
}