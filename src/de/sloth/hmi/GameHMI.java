package de.sloth.hmi;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.layout.StackPane;

public class GameHMI extends StackPane {

	private LayeredCanvas canvas;
	private List<GameInterfaceLayer> gameInterfaceLayers;
	
	public GameHMI(int width, int height) {
		gameInterfaceLayers = new LinkedList<GameInterfaceLayer>();
		this.setWidth(width);
		this.setHeight(height);
		this.canvas = new LayeredCanvas(4, width, height);
		this.getChildren().add(canvas);
	}

	public LayeredCanvas getCanvas() {
		return canvas;
	}
	
	public void registerGameInterfaceLayer(GameInterfaceLayer gil) {
		this.gameInterfaceLayers.add(gil);
		this.getChildren().add(gil);
	}
	
	public GameInterfaceLayer getGameInterfaceLayer(String uid) {
		for(GameInterfaceLayer gil : this.gameInterfaceLayers) {
			if(gil.getUid().equals(uid)) {
				return gil;
			}
		}
		return null;
	}
}