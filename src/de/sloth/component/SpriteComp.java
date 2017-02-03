package de.sloth.component;

public class SpriteComp extends Component {
	
	private String spritePath;
	private String direction;
	
	public SpriteComp(String imagePath) {
		this.spritePath = imagePath;
		this.direction = "right";
	}

	public String getSpritePath() {
		return spritePath;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getDirection() {
		return this.direction;
	}

	public void setSpritePath(String spritePath) {
		this.spritePath = spritePath;
	}
}
