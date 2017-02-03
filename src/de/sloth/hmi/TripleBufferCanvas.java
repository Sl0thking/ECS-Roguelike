package de.sloth.hmi;

import de.sloth.persistence.gameConstants.SpriteLoader;
import javafx.scene.canvas.Canvas;

public class TripleBufferCanvas extends Canvas {

	double scaling;
	SpriteLoader loader;
	
	public TripleBufferCanvas(double scaling) {
		super();
		this.scaling = scaling;
		loader = SpriteLoader.getInstance(scaling, 32, 32);
	}

	public TripleBufferCanvas(double scaling, double width, double height) {
		super(width, height);
		this.scaling = scaling;
		loader = SpriteLoader.getInstance(scaling, 32, 32);
	}
	
	public void drawSprite(String spriteName, int xPos, int yPos) {
		this.getGraphicsContext2D().drawImage(loader.getSprite(spriteName), xPos, yPos);
	}

}
