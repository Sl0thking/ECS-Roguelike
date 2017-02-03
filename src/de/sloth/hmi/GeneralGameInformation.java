package de.sloth.hmi;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.system.game.core.GameEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class GeneralGameInformation extends GameInterfaceLayer {
	private Label label;
	
	public GeneralGameInformation(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);
		BorderPane pane = new BorderPane();
		this.label = new Label("Test");
		this.label.setTextFill(Color.WHITE);
		pane.setTop(label);
		this.getChildren().add(pane);
	}

	public Label getLabel() {
		return label;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}