package de.sloth.system.game.endConditions;

import java.util.LinkedList;
import java.util.List;

import de.sloth.component.EnemyComp;
import de.sloth.component.FocusComp;
import de.sloth.component.LivingComp;
import de.sloth.entity.Entity;
import de.sloth.main.IEntityManagement;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.hmi.hmiMenu.HMIMenuEvent;

public class EndCondition implements IBehavior {

	@Override
	public void execute(GameSystem system) {
		checkLoose(system);
		checkWin(system);
	}
		
	private void checkWin(GameSystem system) {
		List<Class<?>> enemyComps = new LinkedList<Class<?>>();
		enemyComps.add(EnemyComp.class);
		List<Entity> enemies = IEntityManagement.filterEntitiesByComponents(system.getEntityManager().getAllEntities(), enemyComps);
		if(enemies.size() == 0) {
			GameEvent event = new HMIMenuEvent("showWin");
			system.getEventQueue().add(event);
			system.setActive(false);
		}
	}

	private void checkLoose(GameSystem system) {
		List<Class<?>> playerComps = new LinkedList<Class<?>>();
		playerComps.add(FocusComp.class);
		playerComps.add(LivingComp.class);
		List<Entity> players = system.getEntityManager().getPlayableEntities();
		if(players.size() > 0) {
			Entity player = players.get(0);
			LivingComp lComp = (LivingComp) player.getComponent(LivingComp.class);
			if(lComp.getHp() <= 0) {
				system.getEntityManager().removeEntity(player);
				GameEvent event = new HMIMenuEvent("toggleGameOver");
				system.getEventQueue().add(event);
				system.setActive(false);
			} 
		}
	}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {}

}
