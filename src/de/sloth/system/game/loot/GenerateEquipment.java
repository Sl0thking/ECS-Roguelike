package de.sloth.system.game.loot;

import de.sloth.entity.Entity;
import de.sloth.generators.LootGenerator;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.game.inventory.InventoryEvent;

public class GenerateEquipment implements IBehavior {
	LootGenerator lGen;
	
	public GenerateEquipment() {
		this.lGen = LootGenerator.getInstance();
	}
	
	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		InventoryEvent iEvent = new InventoryEvent("collectItem");
		Entity drop = lGen.generateEquipment(1);
		iEvent.setCollectingEntity(drop);
		system.getEventQueue().add(iEvent);
	}
}