package de.sloth.hmi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.InventoryComponent;
import de.sloth.component.LivingComp;
import de.sloth.component.LootableComp;
import de.sloth.component.LvlComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class InventoryGameLayer extends GameInterfaceLayer {
	@FXML
	Label currLvlLbl;
	
	@FXML
	ProgressBar currExpBar;
	
	@FXML
	ProgressBar currHpBar;
	
	@FXML
	Label currDmgLbl;
	
	@FXML
	Label currDefLbl;
	
	@FXML
	Label itemLbl;
	
	int xPos = 0;
	
	@FXML
	GridPane invGridPane;
	
	public InventoryGameLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);
		Pane pane = null;
		try {
			pane = (Pane) this.loadInterfaceLayerFXML();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getChildren().add(pane);
		this.setVisible(false);
		this.setDisable(true);
	}
	
	public void setObservableEntity(Entity entity) {
		InventoryComponent ic = (InventoryComponent) entity.getComponent(InventoryComponent.class);
		LivingComp lc = (LivingComp) entity.getComponent(LivingComp.class);
		LvlComp lvlc = (LvlComp) entity.getComponent(LvlComp.class);
		//Inventory thingies
		for(Node node : invGridPane.getChildren()) {
			if(node instanceof VBox) {
				VBox vbox = (VBox) node;
				((Label) vbox.getChildren().get(0)).setText("");
				((ImageView) vbox.getChildren().get(1)).setImage(null);
			}
		}
		if(ic != null) {
			ic.getInventoryList().addListener(new ListChangeListener<Entity>() {
				@Override
				public void onChanged(javafx.collections.ListChangeListener.Change<? extends Entity> c) {
					c.next();
					if(c.wasAdded()) {
						int beginIndex = c.getFrom();
						//Label label = (Label)((Pane) invGridPane.getChildren().get(beginIndex)).getChildren().get(0);
						ImageView view = (ImageView)((Pane) invGridPane.getChildren().get(beginIndex)).getChildren().get(1);
						LootableComp lcomp = (LootableComp) ic.getInventoryList().get(beginIndex).getComponent(LootableComp.class);
						itemLbl.setText(lcomp.getName());
						view.setImage(lcomp.getImage());
					} else if(c.wasRemoved()){
						int beginIndex = c.getFrom();
						for (int i = beginIndex; i<invGridPane.getChildren().size()-1; i++) {
							//Label labelNow = (Label)((Pane) invGridPane.getChildren().get(i)).getChildren().get(0);
							ImageView viewNow = (ImageView)((Pane) invGridPane.getChildren().get(i)).getChildren().get(1);
							//Label labelAfter = (Label)((Pane) invGridPane.getChildren().get(i+1)).getChildren().get(0);
							ImageView viewAfter = (ImageView)((Pane) invGridPane.getChildren().get(i+1)).getChildren().get(1);
							viewNow.setImage(viewAfter.getImage());
							itemLbl.setText(itemLbl.getText());
						}
					}
				}
			});
			currLvlLbl.textProperty().bind(lvlc.getLvl().asString());
			lc.getHpMaxProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						currHpBar.setProgress(lc.getHpProperty().doubleValue() / newValue.doubleValue());
				}
			});
			
			lc.getHpProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					if(lc.getHpProperty().doubleValue() >= 0) {
						currHpBar.setProgress(newValue.doubleValue() / lc.getHpMaxProperty().doubleValue());	
					} else {
						currHpBar.setProgress(0);
					}
				}
			});
			currHpBar.setProgress(1);
			lvlc.getMaxExp().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						currExpBar.setProgress(lvlc.getCurrExp().getValue() / newValue.doubleValue());
				}
			});
			
			lvlc.getCurrExp().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					System.out.println(newValue);
					if(lvlc.getCurrExp().doubleValue() >= 0) {
						currExpBar.setProgress(lvlc.getCurrExp().doubleValue() / lvlc.getMaxExp().doubleValue());	
					} else {
						currExpBar.setProgress(0);
					}
				}
			});
			
			currDmgLbl.textProperty().bind(lc.getAttackMin().asString().concat(" - ").concat(lc.getAttackMax().asString()));
		}
		this.setActiveSlot(0, 0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

	public void setActiveSlot(int posX, int posY) {
		for(Node node : invGridPane.getChildren()) {
			if(node instanceof Pane) {
				node.getStyleClass().remove("inv-slot-selected");
				node.getStyleClass().add("inv-slot");
			}
		}
		invGridPane.getChildren().get(posX).getStyleClass().remove("inv-slot");
		invGridPane.getChildren().get(posX).getStyleClass().add("inv-slot-selected");
	}

}
