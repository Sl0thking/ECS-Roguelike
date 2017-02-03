package de.sloth.system.game.inventory;

import java.util.LinkedList;
import java.util.List;

import de.sloth.component.EquipableComp;
import de.sloth.component.FocusComp;
import de.sloth.component.HealComp;
import de.sloth.component.InventoryComponent;
import de.sloth.component.LivingComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class UseItem implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		List<Class<?>> filterComps = new LinkedList<Class<?>>();
		filterComps.add(InventoryComponent.class);
		filterComps.add(FocusComp.class);
		Entity mainEntity = system.getEntityManager().getActivePlayabaleEntity();
		InventoryComponent icomp = (InventoryComponent) mainEntity.getComponent(InventoryComponent.class);
		//to be excluded
		if(icomp.getCurrentSlotX() < icomp.getInventoryList().size()) {
			Entity usedItem = icomp.getInventoryList().get(icomp.getCurrentSlotX());
			if(usedItem.getComponent(HealComp.class) != null) {
				HealComp hc = (HealComp) usedItem.getComponent(HealComp.class);
				LivingComp lc = (LivingComp) mainEntity.getComponent(LivingComp.class);
				lc.setHp(lc.getHp() + hc.getHealValue());
			} else if(usedItem.getComponent(EquipableComp.class) != null) {
				EquipableComp ecomp = (EquipableComp) usedItem.getComponent(EquipableComp.class);
				LivingComp lc = (LivingComp) mainEntity.getComponent(LivingComp.class);
				lc.setAttackMax(lc.getAttackMax().getValue() + ecomp.getDmgBonus());
				lc.setAttackMin(lc.getAttackMin().getValue() + ecomp.getDmgBonus());
				lc.setDefense(lc.getDefense() + ecomp.getArmorBonus());
			}
			icomp.getInventoryList().remove(icomp.getCurrentSlotX());
		}
	}
}