package de.sloth.hmi;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ProgressBar;

public class GameBar extends ProgressBar {
	private SimpleIntegerProperty maxValue;
	private SimpleIntegerProperty currValue;
	
	public GameBar(String color) {
		maxValue = new SimpleIntegerProperty();
		maxValue.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				setProgress(currValue.doubleValue() / newValue.doubleValue());
			}
		});
		currValue = new SimpleIntegerProperty();
		currValue.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.doubleValue() >= 0) {
					setProgress(newValue.doubleValue() / maxValue.doubleValue());	
				} else {
					setProgress(0);
				}
			}
		});
		this.setMinWidth(200.0);
		this.setMinHeight(35.0);
		if(color.equals("red")) {
			this.setStyle("-fx-accent: red");
		} else {
			this.setStyle("-fx-accent: orange");
		}
	}

	public SimpleIntegerProperty getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(SimpleIntegerProperty maxValueProp) {
		this.maxValue = maxValueProp;
	}

	public SimpleIntegerProperty getCurrValue() {
		return currValue;
	}

	public void setCurrValue(SimpleIntegerProperty currValue) {
		this.currValue = currValue;
	}
	
	

}
