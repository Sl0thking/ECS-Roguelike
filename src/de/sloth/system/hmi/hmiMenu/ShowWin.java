package de.sloth.system.hmi.hmiMenu;

import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.hmi.WinGameLayer;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.hmi.HMIGameSystem;

public class ShowWin implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		HMIGameSystem mainHmi = (HMIGameSystem) system;
		PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameHMI().getGameInterfaceLayer("playerInfo");
		//MainMenuLayer mml = (MainMenuLayer) mainHmi.getGameInterfaceLayer("main");
		WinGameLayer wlg = (WinGameLayer) mainHmi.getGameHMI().getGameInterfaceLayer("wlg");
		pil.setVisible(false);
		pil.setDisable(true);
		wlg.setVisible(true);
		wlg.setDisable(false);
	}
}