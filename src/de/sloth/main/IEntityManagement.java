package de.sloth.main;

import java.util.LinkedList;
import java.util.List;

import de.sloth.entity.Entity;

public interface IEntityManagement {
	
	public List<Entity> getAllEntities();
	public List<Entity> getPlayableEntities();
	public Entity getActivePlayabaleEntity();
	public List<Entity> getChunkEntities();
	public List<Entity> getOtherEntities();
	public void addEntity(Entity entity);
	public void clear();
	public void removeEntity(Entity entity);
	
	public static List<Entity> filterEntitiesByComponent(List<Entity> entities, Class<?> compClass) {
		List<Entity> matchingEntities = new LinkedList<Entity>();
		for(Entity entity : entities) {
			if(entity.getComponentClasses().contains(compClass)) {
				matchingEntities.add(entity);
			}
		}
		return matchingEntities;
	}

	public static List<Entity> filterEntitiesByComponents(List<Entity> entities, List<Class<?>> compClasses) {
		List<Entity> matchingEntities = new LinkedList<Entity>();
		for(Entity entity : entities) {
			if(entity.getComponentClasses().containsAll(compClasses)) {
				matchingEntities.add(entity);
			}
		}
		return matchingEntities;
	}
}
