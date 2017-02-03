package de.sloth.system.game.systemActivation;

import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class ActivateSystem implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		GameCoreSystem gcSys = (GameCoreSystem) system;
		SystemActivationEvent sae = (SystemActivationEvent) expectedEvent;
		
		for(GameSystem sys : gcSys.getCore().getRegistredSystems()) {
			if(sys.getSystemID().equals(sae.getTargetSystemID())) {
				sys.setActive(sae.isActive());
				System.out.println("Activate: " + sae.getTargetSystemID());
			}
		}
	}
}