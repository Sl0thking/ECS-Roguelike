package de.sloth.hmi;

import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;

public class FPSCalculator {
	private double startInSec;
	private SimpleIntegerProperty fps;
	
	public FPSCalculator() {
		startInSec = 0;
		fps = new SimpleIntegerProperty();
	}
	
	public void start() {
		startInSec = new Date().getTime()/1000.0;
	}
	
	public void stop() {
		double secondsAfter = new Date().getTime()/1000.0;
		fps.setValue((int) (1 / (secondsAfter - this.startInSec)));
	}

	public SimpleIntegerProperty getFpsProperty() {
		// TODO Auto-generated method stub
		return this.fps;
	}
}
