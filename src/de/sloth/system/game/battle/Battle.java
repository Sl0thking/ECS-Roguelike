package de.sloth.system.game.battle;

import java.util.Random;

import de.sloth.component.LivingComp;
import de.sloth.component.LvlComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.game.loot.LootEvent;

public class Battle implements IBehavior {
	
	private int rollAttack(int min, int max) {
		 Random rand = new Random();
		 return min + rand.nextInt((max-min)+1);
	}

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		BattleEvent bEvent = (BattleEvent) expectedEvent;
		Entity attacker = bEvent.getCollisionSrc();
		Entity defender = bEvent.getCollisionTarget();
		LivingComp attackerStatus = (LivingComp) attacker.getComponent(LivingComp.class);
		LivingComp defenderStatus = (LivingComp) defender.getComponent(LivingComp.class);
			
		int attackerValue = rollAttack(attackerStatus.getAttackMin().getValue(), attackerStatus.getAttackMax().getValue());
		int defenderValue = rollAttack(defenderStatus.getAttackMin().getValue(), defenderStatus.getAttackMax().getValue());
			
		defenderStatus.setHp(defenderStatus.getHp() - (attackerValue - defenderStatus.getDefense()));
		attackerStatus.setHp(attackerStatus.getHp() - (defenderValue - attackerStatus.getDefense()));
		if(defenderStatus.getHp() <= 0) {
			system.getEntityManager().removeEntity(defender);
			LvlComp lcomp = (LvlComp) attacker.getComponent(LvlComp.class);
			int bLvl = lcomp.getLvl().getValue();
				
			lcomp.gainExp(10);
			if(lcomp.getLvl().getValue() > bLvl) {
				attackerStatus.setAttackMin(attackerStatus.getAttackMin().getValue()+2);
				attackerStatus.setAttackMax(attackerStatus.getAttackMax().getValue()+2);
				attackerStatus.setHpMax(attackerStatus.getHpMax()+25);
				attackerStatus.setHp(attackerStatus.getHpMax());
			}
			Random rand = new Random();
			int genEquip = rand.nextInt(2);
			LootEvent lEvent = new LootEvent();
			if(genEquip == 0) {
				lEvent.setKeyword("generateEquipment");
			} else {
				lEvent.setKeyword("generatePotion");
			}
			system.getEventQueue().add(lEvent);
		}
	}
}