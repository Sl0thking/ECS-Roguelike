package de.sloth.system.hmi.hmiMenu;

import de.sloth.entity.Entity;
import de.sloth.hmi.InventoryGameLayer;
import de.sloth.hmi.MainMenuLayer;
import de.sloth.hmi.NotSupportedEntityException;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.hmi.HMIGameSystem;

public class ShowGame implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		HMIGameSystem hmiSys = (HMIGameSystem) system;
		MainMenuLayer mml = (MainMenuLayer) hmiSys.getGameHMI().getGameInterfaceLayer("main");
		PlayerInformationLayer pil = (PlayerInformationLayer) hmiSys.getGameHMI().getGameInterfaceLayer("playerInfo");
		InventoryGameLayer igl = (InventoryGameLayer) hmiSys.getGameHMI().getGameInterfaceLayer("igl");
		
		boolean isRestarted = pil.isDisabled();
		pil.setVisible(!pil.isVisible());
		pil.setDisable(!pil.isDisabled());
		pil.requestFocus();
		mml.setVisible(!mml.isVisible());
		mml.setDisable(!mml.isDisabled());

		if(isRestarted) {
			Entity entity = system.getEntityManager().getActivePlayabaleEntity();
			try {
				pil.setObservableEntity(entity);
				igl.setObservableEntity(entity);
			} catch (NotSupportedEntityException e) {
				e.printStackTrace();
			}
		}	
	}
}