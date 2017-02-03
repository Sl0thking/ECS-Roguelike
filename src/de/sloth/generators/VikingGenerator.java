package de.sloth.generators;

import de.sloth.component.InventoryComponent;
import de.sloth.component.LivingComp;
import de.sloth.component.LvlComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;

public class VikingGenerator {
	
	private static VikingGenerator instance;
	
	private VikingGenerator() {}
	
	public static VikingGenerator getInstance() {
		if(instance == null) {
			instance = new VikingGenerator();
		}
		return instance;
	}
	
	public Entity generateViking(int spriteWidth, int spriteHeight) {
		Entity viking = new Entity();
		viking.setId(-1);
		viking.setName("Viking");
		viking.addComponent(new Position3DComp());
		LivingComp lComp = new LivingComp(true);
		lComp.setHp(50);
		lComp.setHpMax(50);
		LvlComp lvlcomp = new LvlComp();
		viking.addComponent(lComp);
		viking.addComponent(lvlcomp);
		viking.addComponent(new InventoryComponent());
		viking.addComponent(new SpriteComp("Viking_right.png"));
		return viking;
	}

}
