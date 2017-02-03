package de.sloth.component;

import de.sloth.entity.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryComponent extends Component {
	
	private ObservableList<Entity> inventory;
	private int currentSlotX;
	private int currentSlotY;
	
	public InventoryComponent() {
		inventory = FXCollections.observableArrayList();
		setCurrentSlotX(0);
		setCurrentSlotY(0);
	}
	
	public ObservableList<Entity> getInventoryList() {
		return inventory;
	}

	public int getCurrentSlotY() {
		return currentSlotY;
	}

	public void setCurrentSlotY(int currentSlotY) {
		this.currentSlotY = currentSlotY;
	}

	public int getCurrentSlotX() {
		return currentSlotX;
	}

	public void setCurrentSlotX(int currentSlotX) {
		this.currentSlotX = currentSlotX;
	}
}