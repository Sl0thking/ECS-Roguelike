package de.sloth.system.game.inventory;

import java.util.LinkedList;
import java.util.List;

import de.sloth.component.FocusComp;
import de.sloth.component.InventoryComponent;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.hmi.hmiInventory.HMIInventoryEvent;

public class ChangeCursor implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		InventoryEvent iEvent = (InventoryEvent) expectedEvent;
		List<Class<?>> filterComps = new LinkedList<Class<?>>();
		filterComps.add(InventoryComponent.class);
		filterComps.add(FocusComp.class);
		Entity mainEntity = system.getEntityManager().getActivePlayabaleEntity();
		InventoryComponent icomp = (InventoryComponent) mainEntity.getComponent(InventoryComponent.class);
		icomp.setCurrentSlotX(icomp.getCurrentSlotX() + iEvent.getxPos());
		
		if(icomp.getCurrentSlotX() > 14) {
			icomp.setCurrentSlotX(0);
		} else if(icomp.getCurrentSlotX() < 0) {
			icomp.setCurrentSlotX(14);
		}
		system.getEventQueue().add(new HMIInventoryEvent("changeCursor", icomp.getCurrentSlotX(), icomp.getCurrentSlotY()));
	}
}