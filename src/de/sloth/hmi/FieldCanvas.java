package de.sloth.hmi;

import javafx.scene.canvas.Canvas;

public class FieldCanvas extends Canvas {
	
	public FieldCanvas(int fieldX, int fieldY, int spriteHeight, int spriteWidth) {
		super(fieldX*spriteWidth, fieldY*spriteHeight);
	}
}