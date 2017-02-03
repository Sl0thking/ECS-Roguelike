package de.sloth.system.hmi.hmiInventory;

import de.sloth.hmi.InventoryGameLayer;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.hmi.HMIGameSystem;

public class ChangeInventoryCursor implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		HMIGameSystem mainHmi = (HMIGameSystem) system;
		HMIInventoryEvent iEvent = (HMIInventoryEvent) expectedEvent;
		InventoryGameLayer igl = (InventoryGameLayer) mainHmi.getGameHMI().getGameInterfaceLayer("igl");
		igl.setActiveSlot(iEvent.getPosX(), iEvent.getPosY());
	}
}