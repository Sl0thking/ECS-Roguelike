package de.sloth.system.hmi.hmiInventory;

import de.sloth.hmi.InventoryGameLayer;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.hmi.HMIGameSystem;

public class CloseInventory implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		HMIGameSystem mainHmi = (HMIGameSystem) system;
		PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameHMI().getGameInterfaceLayer("playerInfo");
		InventoryGameLayer igl = (InventoryGameLayer) mainHmi.getGameHMI().getGameInterfaceLayer("igl");
		pil.setVisible(true);
		pil.setDisable(false);
		igl.setDisable(true);
		igl.setVisible(false);
		pil.requestFocus();
	}
}