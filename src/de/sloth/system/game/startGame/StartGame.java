package de.sloth.system.game.startGame;

import de.sloth.component.EnemyComp;
import de.sloth.component.FocusComp;
import de.sloth.component.LivingComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import de.sloth.generators.VikingGenerator;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.game.systemActivation.SystemActivationEvent;
import de.sloth.system.hmi.hmiMenu.HMIMenuEvent;

public class StartGame implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		System.out.println("START");
		//int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
		//int screenHeight = (int) Screen.getPrimary().getBounds().getHeight(); 
		int spriteHeight = 32;
		int spriteWidth = 32;
		int xFields = 512;
		int yFields = 512;
		system.getEntityManager().clear();
		int id = 1;
			
		Entity viking = VikingGenerator.getInstance().generateViking(spriteWidth, spriteHeight);
		viking.setId(id);
		viking.addComponent(new FocusComp(true));
		((Position3DComp) viking.getComponent(Position3DComp.class)).setX(1);
		((Position3DComp) viking.getComponent(Position3DComp.class)).setY(1);
		system.getEntityManager().addEntity(viking);
		for(int y = 0; y < yFields; y++) {
			for(int x = 0; x < xFields; x++) {
						
				Position3DComp posComp = new Position3DComp();
				id+=1;
				if(x > 0 && x < xFields-1) {
					Entity floor = new Entity();
					floor.setId(id);
					floor.setName("Floor");
					posComp.setX(x);
					posComp.setY(y);
					posComp.setZ(0);
					floor.addComponent(posComp);
					floor.addComponent(new SpriteComp("field.png"));
					system.getEntityManager().addEntity(floor);
				}
				if(y == 0 || y == yFields-1 || x == 0 || x == xFields-1) {
					Entity wall = new Entity();
					wall.setId(id);
					wall.setName("Wall");
					posComp = new Position3DComp();
					posComp.setX(x);
					posComp.setY(y);
					wall.addComponent(posComp);
					if(posComp.getX() == 0) {
						wall.addComponent(new SpriteComp("wall_side_left.png"));
					} else if(posComp.getX() == xFields-1) {
						wall.addComponent(new SpriteComp("wall_side_right.png"));
					} else {
						wall.addComponent(new SpriteComp("wall.png"));
					}
					system.getEntityManager().addEntity(wall);
				} else if((y != 1 || x != 1) && Math.random() < 0.02) {
					Entity enemy = new Entity();
					enemy.setId(id		);
					enemy.setName("Enemy");
					posComp = new Position3DComp();
					posComp.setX(x);
					posComp.setY(y);
					enemy.addComponent(posComp);
					enemy.addComponent(new SpriteComp("orc.png"));
					enemy.addComponent(new LivingComp(true));
					enemy.addComponent(new EnemyComp());
					system.getEntityManager().addEntity(enemy);
				}
			}
		}
		//system.getEventQueue().add(new SystemActivationEvent("single", "ecSys"));
		system.getEventQueue().add(new SystemActivationEvent("single", "renderSys"));
		HMIMenuEvent hmiEvent;
		hmiEvent = new HMIMenuEvent("showGame");
		
		system.getEventQueue().add(hmiEvent);
		System.out.println("Generated Map");
	}
}