package de.sloth.hmi;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.LivingComp;
import de.sloth.component.LvlComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PlayerInformationLayer extends GameInterfaceLayer {
	private GameBar hBar;
	private GameBar eBar;
	
	public PlayerInformationLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);
		BorderPane pane = new BorderPane();
		hBar = new GameBar("red");
		eBar = new GameBar("orange");
		VBox vbox = new VBox();
		vbox.getChildren().add(hBar);
		vbox.getChildren().add(eBar);
		pane.setBottom(vbox);
		this.getChildren().add(pane);
		this.setDisable(true);
		this.setVisible(false);
	}
	
	public GameBar gethBar() {
		return hBar;
	}
	
	public GameBar getEBar() {
		return eBar;
	}

	public void sethBar(GameBar hBar) {
		this.hBar = hBar;
	}
	
	public void setObservableEntity(Entity entity) throws NotSupportedEntityException {
		LivingComp lComp = (LivingComp) entity.getComponent(LivingComp.class);
		LvlComp lvlcomp = (LvlComp) entity.getComponent(LvlComp.class);
		if(lComp != null && lvlcomp != null) {
			this.getEBar().getCurrValue().bind(lvlcomp.getCurrExp());
			this.getEBar().getMaxValue().bind(lvlcomp.getMaxExp());
			this.gethBar().getCurrValue().bind(lComp.getHpProperty());
			this.gethBar().getMaxValue().bind(lComp.getHpMaxProperty());
		} else {
			throw new NotSupportedEntityException();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1){}
}