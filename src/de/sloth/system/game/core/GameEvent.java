package de.sloth.system.game.core;

public abstract class GameEvent {
	
	private String keyword;
	
	public GameEvent(String keyword) {
		this.keyword = keyword;
	}
	
	public GameEvent() {
		this.keyword = "Any";
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return this.getClass() + " [keyword=" + keyword + "]";
	}
	
	
}
