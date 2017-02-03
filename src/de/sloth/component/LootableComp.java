package de.sloth.component;

import javafx.scene.image.Image;

public class LootableComp extends Component {
	
	Image image;
	String name;
	int goldValue;
	
	public LootableComp(Image image, String name, int gValue) {
		super();
		this.image = image;
		this.name = name;
		this.goldValue = gValue;
	}

	public LootableComp() {
		super();
		this.image = new Image("file:./sprites/health_potion.png");
		this.name = "Potion";
		this.goldValue = 1;
	}
	
	public Image getImage() {
		return this.image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGoldBounty(int i) {
		this.goldValue = i;
	}

	public void setImage(String string) {
		this.image = new Image(string);
	}
}
