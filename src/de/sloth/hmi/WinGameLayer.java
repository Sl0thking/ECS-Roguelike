package de.sloth.hmi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.system.game.core.GameEvent;
import de.sloth.system.hmi.hmiMenu.HMIMenuEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class WinGameLayer extends GameInterfaceLayer {
	
	public WinGameLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);
		BorderPane pane = null;
		try {
			pane = (BorderPane) this.loadInterfaceLayerFXML();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getChildren().add(pane);
		this.setVisible(false);
		this.setDisable(true);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	@FXML
	public void toMenu() {
		HMIMenuEvent event = new HMIMenuEvent("showMenu");
		this.getEventQueue().add(event);
	}

}
