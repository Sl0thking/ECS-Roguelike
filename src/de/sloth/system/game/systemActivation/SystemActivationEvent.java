package de.sloth.system.game.systemActivation;

import de.sloth.system.game.core.GameEvent;

public class SystemActivationEvent extends GameEvent {
	private boolean isActive;
	private String targetSystemID;
	
	public SystemActivationEvent(String keyword, String targetSystemID) {
		super(keyword);
		this.isActive = true;
		this.targetSystemID = targetSystemID;
	}
	
	public SystemActivationEvent(String keyword, String targetSystemID, boolean isActive) {
		super();
		this.isActive = isActive;
		this.targetSystemID = targetSystemID;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getTargetSystemID() {
		return targetSystemID;
	}

	public void setTargetSystemID(String targetSystemID) {
		this.targetSystemID = targetSystemID;
	}
}
