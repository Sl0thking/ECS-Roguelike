package de.sloth.hmi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.startGame.StartGameEvent;

public class MainMenuLayer extends GameInterfaceLayer implements Initializable {
	
	@FXML 
	Button startGame;
	@FXML 
	Button exitGame;
	@FXML 
	Label wuppiTitle;
	
	public MainMenuLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);	
		BorderPane root = null;
		try {
			root = (BorderPane) this.loadInterfaceLayerFXML();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getChildren().add(root);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	@FXML
	public void startGame() {
		getEventQueue().add(new StartGameEvent());
	}
	
	@FXML
	public void exitGame() {
		System.exit(0);
	}
}
