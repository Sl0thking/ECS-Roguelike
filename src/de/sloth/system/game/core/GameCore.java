package de.sloth.system.game.core;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.hmi.FPSCalculator;
import de.sloth.hmi.LayeredCanvas;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Main Game Loop, basing on a animation timer
 * @author Kevin Jolitz
 *
 */
public class GameCore extends AnimationTimer {

	List<GameSystem> gameSystems;
	final int MS_PER_FRAME = 1000/60;
	FPSCalculator fpsCalc;

	public GameCore() {
		//this.cRenderer = new SpriteRendererSystem(entities, eventQueue, lc, screenWidth, screenHeight);
		this.gameSystems = new LinkedList<GameSystem>();
		this.fpsCalc = new FPSCalculator();
	}

	
	public SimpleIntegerProperty getFpsProperty() {
		return this.fpsCalc.getFpsProperty();
	}
	
	public void registerSystem(GameSystem system) {
		gameSystems.add(system);
	}
	
	public void removeSystem(GameSystem system) {
		gameSystems.remove(system);
	}
	
	public void doGameLogic() {
		for(GameSystem system:gameSystems) {
			system.executeSystem();
		}
	}

	@Override
	public void handle(long now) {
		//Normally input is recognized here, but everything is handled by fx so ignored in gameloop
		//handleUserInput();
		double secondsBefore = new Date().getTime()/1000.0;
		this.fpsCalc.start();
		this.doGameLogic();
		//cRenderer.executeSystem();
		
		double secondsAfter = new Date().getTime()/1000.0;
		try {
			long wait = (long) ((secondsBefore*1000 + MS_PER_FRAME) - secondsAfter*1000);
			if(wait >= 0) {
				Thread.sleep(wait);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.fpsCalc.stop();
	}

	public List<GameSystem> getRegistredSystems() {
		return gameSystems;
	}
}