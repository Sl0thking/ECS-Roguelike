package de.sloth.main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.sloth.component.FocusComp;
import de.sloth.component.Position3DComp;
import de.sloth.entity.Entity;

public class EntityManager implements IEntityManagement {
	private List<Entity> playableEntities;
	private Entity activeEntity;
	private Map<Integer, List<Entity>> chunkLists;
	private List<Entity> other;
	private static final int CHUNKS_PER_ROW = 8;
	private static final int CHUNKS_ROWS = 8;
	private static final int CHUNK_ROWS = 64;
	private static final int CHUNK_COLUMNS = 64;
	
	public EntityManager() {
		this.playableEntities = new LinkedList<Entity>();
		this.activeEntity = null;
		this.other = new LinkedList<Entity>();
		this.chunkLists = new HashMap<Integer, List<Entity>>();
		for(int x = 0; x < CHUNKS_PER_ROW * CHUNKS_ROWS; x++) {
			this.chunkLists.put(x, new LinkedList<Entity>());
		}
	}
	
	@Override
	public List<Entity> getAllEntities() {
		List<Entity> allEntities = new LinkedList<Entity>();
		for(Entity pE:playableEntities) {
			if(!allEntities.contains(pE)) {
				allEntities.add(pE);
			}
		}
		for(Entity pE:other) {
			if(!allEntities.contains(pE)) {
				allEntities.add(pE);
			}
		}
		for(List<Entity> entities:this.chunkLists.values()) {
			for(Entity pE:entities) {
				if(!allEntities.contains(pE)) {
					allEntities.add(pE);
				}
			}
		}
		return allEntities;
	}

	@Override
	public List<Entity> getPlayableEntities() {
		return this.playableEntities;
	}

	@Override
	public Entity getActivePlayabaleEntity() {
		return this.activeEntity;
	}

	@Override
	public List<Entity> getChunkEntities() {
		if(this.activeEntity != null) {
			Position3DComp pos = (Position3DComp) this.activeEntity.getComponent(Position3DComp.class);
			List<Entity> chunkRoom = new LinkedList<Entity>();
			int chunkNr = this.getChunkIndex(pos);
			chunkRoom.addAll(this.chunkLists.get(this.getChunkIndex(pos)));
			if(chunkNr % CHUNKS_PER_ROW > 0) {
				chunkRoom.addAll(this.chunkLists.get(chunkNr-1));
			}
			if(chunkNr % CHUNKS_PER_ROW < CHUNKS_PER_ROW ) {
				chunkRoom.addAll(this.chunkLists.get(chunkNr+1));
			}
			if(chunkNr % CHUNKS_ROWS > 0) {
				chunkRoom.addAll(this.chunkLists.get(chunkNr-CHUNKS_PER_ROW));
			}
			if(chunkNr % CHUNKS_ROWS < CHUNKS_ROWS ) {
				chunkRoom.addAll(this.chunkLists.get(chunkNr+CHUNKS_PER_ROW));
			}
			
			return chunkRoom;
		} else {
			return new LinkedList<Entity>();
		}
	}
	
	private int getChunkIndex(Position3DComp pos) {
		int xPos = pos.getX();
		int yPos = pos.getY();
		int chunkX = (int) xPos / CHUNK_COLUMNS;
		int chunkY = (int) yPos / CHUNK_ROWS;
		return chunkX + (CHUNKS_PER_ROW*chunkY);
	}

	@Override
	public void addEntity(Entity entity) {
		FocusComp fComp = (FocusComp) entity.getComponent(FocusComp.class);
		Position3DComp pos = (Position3DComp) entity.getComponent(Position3DComp.class);
		
		if(fComp != null) {
			this.activeEntity = entity;
			this.playableEntities.add(entity);
		} 
		
		if(pos != null) {
			this.chunkLists.get(this.getChunkIndex(pos)).add(entity);
		}
		
		if(pos == null && fComp == null) {
			this.other.add(entity);
		}
	}

	@Override
	public List<Entity> getOtherEntities() {
		return this.other;
	}

	@Override
	public void clear() {
		this.activeEntity = null;
		this.playableEntities.clear();
		this.other.clear();
		for(List<Entity> chunkEntities : this.chunkLists.values()) {
			chunkEntities.clear();
		}
	}

	@Override
	public void removeEntity(Entity entity) {
		if(this.activeEntity.equals(entity)) {
			this.activeEntity = null;
		}
		this.playableEntities.remove(entity);
		this.other.remove(entity);
		for(List<Entity> chunkEntities : this.chunkLists.values()) {
			chunkEntities.remove(entity);
		}
	}
}
