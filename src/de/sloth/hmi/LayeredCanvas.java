package de.sloth.hmi;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public class LayeredCanvas extends StackPane {
	int layers;
	int screenWidth;
	int screenHeight;
	
	public LayeredCanvas(int layers, int screenWidth, int screenHeight) {
		this.layers = layers;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		for(int i = 0; i < layers; i++) {
			this.getChildren().add(new TripleBufferCanvas(2.5, screenWidth, screenHeight));
		}
	}
	
	public void clear() {
		((Canvas) this.getChildren().get(0)).getGraphicsContext2D().fillRect(0, 0, screenWidth, screenHeight);
		for(int i = 1; i < layers; i++) {
			((Canvas) this.getChildren().get(i)).getGraphicsContext2D().clearRect(0, 0, screenWidth, screenHeight);
		}
	}
	
	public GraphicsContext getGraphicContext(int layer) {
		return ((Canvas) this.getChildren().get(layer)).getGraphicsContext2D();
	}

	public int getLayers() {
		return this.layers;
	}

	public TripleBufferCanvas getLayer(int layer) {
		// TODO Auto-generated method stub
		return (TripleBufferCanvas) this.getChildren().get(layer);
	}
}