package de.sloth.controllHandler;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.main.IEntityManagement;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.inventory.InventoryEvent;
import de.sloth.system.hmi.hmiInventory.HMIInventoryEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InventoryControllHandler implements EventHandler<KeyEvent> {

	private IEntityManagement entityManager;
	private ConcurrentLinkedQueue<GameEvent> eventQueue;
	
	public InventoryControllHandler(IEntityManagement entityManager, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		this.entityManager = entityManager;
		this.eventQueue = eventQueue;
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode().equals(KeyCode.W)) {
			InventoryEvent iEvent = new InventoryEvent("changeCursor");
			iEvent.setxPos(-5);	
			this.eventQueue.add(iEvent);
		} else if(event.getCode().equals(KeyCode.S)) {
			InventoryEvent iEvent = new InventoryEvent("changeCursor");
			iEvent.setxPos(5);	
			this.eventQueue.add(iEvent);
		} else if(event.getCode().equals(KeyCode.A)) {
			InventoryEvent iEvent = new InventoryEvent("changeCursor");
			iEvent.setxPos(-1);	
			this.eventQueue.add(iEvent);
		} else if(event.getCode().equals(KeyCode.D)) {
			InventoryEvent iEvent = new InventoryEvent("changeCursor");
			iEvent.setxPos(1);	
			this.eventQueue.add(iEvent);
		} else if(event.getCode().equals(KeyCode.I) || event.getCode().equals(KeyCode.ESCAPE)) {
			GameEvent closeInventory = new HMIInventoryEvent("closeInventory");
			this.eventQueue.add(closeInventory);
		} else if(event.getCode().equals(KeyCode.SPACE)) {
			//InventoryEvent iEvent = new InventoryEvent(InventoryKeyword.useItem);
			//this.eventQueue.add(iEvent);
		}
	}
}