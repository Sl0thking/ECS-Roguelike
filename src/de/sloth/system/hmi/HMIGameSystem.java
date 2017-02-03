package de.sloth.system.hmi;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.hmi.GameHMI;
import de.sloth.main.IEntityManagement;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;

public class HMIGameSystem extends GameSystem {

	private GameHMI gameHMI;
	
	public HMIGameSystem(GameHMI gameHMI, String systemID) {
		super(systemID);
		this.setGameHMI(gameHMI);
	}

	public HMIGameSystem(GameHMI gameHMI, String systemID,
			Class<? extends GameEvent> listeningEvent,
			IEntityManagement entityManager,
			ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(systemID, listeningEvent, entityManager, eventQueue);
		this.setGameHMI(gameHMI);
	}

	public GameHMI getGameHMI() {
		return gameHMI;
	}

	public void setGameHMI(GameHMI gameHMI) {
		this.gameHMI = gameHMI;
	}
}