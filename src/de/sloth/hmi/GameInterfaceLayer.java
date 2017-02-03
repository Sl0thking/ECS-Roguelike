package de.sloth.hmi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.system.game.core.GameEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public abstract class GameInterfaceLayer extends StackPane implements Initializable {
	private String uid;
	private ConcurrentLinkedQueue<GameEvent> eventQueue;

	public GameInterfaceLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super();
		this.uid = uid;
		this.eventQueue = eventQueue;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public ConcurrentLinkedQueue<GameEvent> getEventQueue() {
		return eventQueue;
	}

	public void setEventQueue(ConcurrentLinkedQueue<GameEvent> eventQueue) {
		this.eventQueue = eventQueue;
	}
	
	public Parent loadInterfaceLayerFXML() throws IOException {
		String className = this.getClass().getSimpleName();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/" + className + ".fxml"));
		loader.setController(this);
		return loader.load();
	}

	@Override
	public abstract void initialize(URL arg0, ResourceBundle arg1);
}