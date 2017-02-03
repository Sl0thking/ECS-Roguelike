package de.sloth.controllHandler;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.Position3DComp;
import de.sloth.entity.Entity;
import de.sloth.main.IEntityManagement;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.move.MoveEvent;
import de.sloth.system.hmi.hmiInventory.HMIInventoryEvent;
import de.sloth.system.hmi.hmiMenu.HMIMenuEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SimpleControllHandler implements EventHandler<KeyEvent> {

	private IEntityManagement entityManager;
	private ConcurrentLinkedQueue<GameEvent> eventQueue;
	
	public SimpleControllHandler(IEntityManagement entityManager, ConcurrentLinkedQueue<GameEvent> eventQueue, int spriteWidth, int spriteHeight) {
		this.entityManager = entityManager;
		this.eventQueue = eventQueue;
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode().equals(KeyCode.W) ||
		   event.getCode().equals(KeyCode.S) ||
		   event.getCode().equals(KeyCode.A) ||
		   event.getCode().equals(KeyCode.D)) {
				handleFocusMovement(event.getCode());
		} else if(event.getCode().equals(KeyCode.G)) {
			/*Entity entity = new Entity();
			entity.setId(getId()+1);
			entity.addComponent(new Position3DComp());
			((Position3DComp) entity.getComponent(Position3DComp.class)).setX(128);
			((Position3DComp) entity.getComponent(Position3DComp.class)).setY(256);
			entities.add(entity);*/
		} else if(event.getCode().equals(KeyCode.Q)) {
			//GameEvent toggleEvent = new HMIEvent(HMIKeyword.togglePlayerInfo);
			//this.eventQueue.add(toggleEvent);
		} else if(event.getCode().equals(KeyCode.I)) {
			GameEvent showInventory = new HMIInventoryEvent("showInventory");
			this.eventQueue.add(showInventory);
		} else if(event.getCode().equals(KeyCode.ESCAPE)) {
			this.eventQueue.add(new HMIMenuEvent("showMenu"));
			this.entityManager.clear();
		}
	}
	
	/*private int getId() {
		int maxId = 0;
		for(Entity entity : this.entities) {
			if(entity.getId() > maxId) {
				maxId = entity.getId();
			}
		}
		return maxId;
	}*/
	
	private void handleFocusMovement(KeyCode kCode) {
		MoveEvent moveEvent = new MoveEvent();
		Entity focusEntity = this.entityManager.getActivePlayabaleEntity();
		if(kCode.equals(KeyCode.W)) {
			Position3DComp comp = (Position3DComp) focusEntity.getComponent(Position3DComp.class);
			moveEvent.setTargetY(comp.getY()-1);
			moveEvent.setTargetX(comp.getX());
			moveEvent.setTargetZ(1);
		} else if(kCode.equals(KeyCode.S)) {
			Position3DComp comp = (Position3DComp) focusEntity.getComponent(Position3DComp.class);
			moveEvent.setTargetY(comp.getY()+1);
			moveEvent.setTargetX(comp.getX());
			moveEvent.setTargetZ(1);
		} else if(kCode.equals(KeyCode.A)) {
			Position3DComp comp = (Position3DComp) focusEntity.getComponent(Position3DComp.class);
			moveEvent.setTargetX(comp.getX()-1);
			moveEvent.setTargetY(comp.getY());
			moveEvent.setTargetZ(1);
		} else if(kCode.equals(KeyCode.D)) {
			Position3DComp comp = (Position3DComp) focusEntity.getComponent(Position3DComp.class);
			moveEvent.setTargetX(comp.getX()+1);
			moveEvent.setTargetY(comp.getY());
			moveEvent.setTargetZ(1);
		}
		moveEvent.setSrcEntity(focusEntity);
		this.eventQueue.add(moveEvent);
	}
}
