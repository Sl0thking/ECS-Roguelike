package de.sloth.persistence.gameConstants;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class SpriteLoader {
	
	private Map<String, Image> sprites;
	private int spriteWidth;
	private int spriteHeight;
	private static SpriteLoader instance;
	
	
	public static SpriteLoader getInstance(double scaling, int spriteWidth, int spriteHeight) {
		if(instance == null) {
			instance = new SpriteLoader(scaling, spriteWidth, spriteHeight);
		}
		return instance;
	}
	
	
	private SpriteLoader() {
		sprites = new HashMap<String, Image>();
		spriteWidth = 32;
		spriteHeight = 32;
		refresh(1.0);
	}
	
	private SpriteLoader(double scaling, int spriteWidth, int spriteHeight) {
		sprites = new HashMap<String, Image>();
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		refresh(scaling);
	}
	
	public void refresh(double scaling) {
		sprites.clear();
		File spriteDir = new File("./sprites");
		File[] spritesInDir = spriteDir.listFiles();
		for (File sprite : spritesInDir) {
			sprites.put(sprite.getName(), new Image(("file:" + sprite.getAbsolutePath()), spriteWidth*scaling, spriteHeight*scaling, true, false));
		}
	}
	
	public Image getSprite(String name) {
		return this.sprites.get(name);
	}

}
