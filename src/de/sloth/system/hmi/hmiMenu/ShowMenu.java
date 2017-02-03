package de.sloth.system.hmi.hmiMenu;

import de.sloth.hmi.MainMenuLayer;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.hmi.WinGameLayer;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.hmi.HMIGameSystem;

public class ShowMenu implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		HMIGameSystem hmiSys = (HMIGameSystem) system;
		PlayerInformationLayer pil = (PlayerInformationLayer) hmiSys.getGameHMI().getGameInterfaceLayer("playerInfo");
		MainMenuLayer mml = (MainMenuLayer) hmiSys.getGameHMI().getGameInterfaceLayer("main");
		WinGameLayer wlg = (WinGameLayer) hmiSys.getGameHMI().getGameInterfaceLayer("wlg");
		pil.setVisible(false);
		pil.setDisable(true);
		wlg.setVisible(false);
		wlg.setDisable(true);
		mml.setVisible(true);
		mml.setDisable(false);
		mml.requestFocus();
	}
}