package de.sloth.system.hmi.hmiMenu;

import de.sloth.component.FocusComp;
import de.sloth.entity.Entity;
import de.sloth.hmi.LooseGameLayer;
import de.sloth.hmi.NotSupportedEntityException;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.hmi.HMIGameSystem;

public class ToggleGameOver implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		HMIGameSystem hmiSys = (HMIGameSystem) system;
		LooseGameLayer wlgl = (LooseGameLayer) hmiSys.getGameHMI().getGameInterfaceLayer("wl");
		PlayerInformationLayer pil = (PlayerInformationLayer) hmiSys.getGameHMI().getGameInterfaceLayer("playerInfo");
		boolean isRestarted = pil.isDisabled();
		pil.setVisible(!pil.isVisible());
		pil.setDisable(!pil.isDisabled());
		pil.requestFocus();
		wlgl.setVisible(!wlgl.isVisible());
		wlgl.setDisable(!wlgl.isDisabled());
		if(isRestarted) {
			for(Entity entity: system.getEntityManager().getAllEntities()) {
				FocusComp fComp = (FocusComp) entity.getComponent(FocusComp.class);
				if(fComp != null) {
					try {
						pil.setObservableEntity(entity);
					} catch (NotSupportedEntityException e) {
						e.printStackTrace();
					}
				}
			}
		}	
	}
}