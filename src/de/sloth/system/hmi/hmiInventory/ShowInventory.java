package de.sloth.system.hmi.hmiInventory;

import de.sloth.hmi.InventoryGameLayer;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.hmi.HMIGameSystem;

public class ShowInventory implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		HMIGameSystem mainHmi = (HMIGameSystem) system;
		PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameHMI().getGameInterfaceLayer("playerInfo");
		InventoryGameLayer igl = (InventoryGameLayer) mainHmi.getGameHMI().getGameInterfaceLayer("igl");
		pil.setVisible(false);
		pil.setDisable(true);
		igl.setDisable(false);
		igl.setVisible(true);
		igl.requestFocus();
	}
}