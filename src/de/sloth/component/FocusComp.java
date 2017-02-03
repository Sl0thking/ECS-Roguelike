package de.sloth.component;

public class FocusComp extends Component {
	private boolean isFocus;

	public FocusComp(boolean isFocus) {
		super();
		this.isFocus = isFocus;
	}

	public boolean isFocus() {
		return isFocus;
	}

	public void setFocus(boolean isFocus) {
		this.isFocus = isFocus;
	}
}