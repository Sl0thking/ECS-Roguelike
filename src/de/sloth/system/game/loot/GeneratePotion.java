package de.sloth.system.game.loot;

import de.sloth.component.HealComp;
import de.sloth.component.LootableComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.game.inventory.InventoryEvent;

public class GeneratePotion implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		InventoryEvent iEvent = new InventoryEvent("collectItem");
		Entity drop = new Entity();
		drop.addComponent(new LootableComp());
		drop.addComponent(new HealComp(10));
		iEvent.setCollectingEntity(drop);
		system.getEventQueue().add(iEvent);
	}
}