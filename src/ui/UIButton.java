package ui;

import javafx.scene.control.Button;

public class UIButton extends Button {
	public UIButton(String text) {
		super(text);

		getStyleClass().add("ui-btn-basic");
	}

	public UIButton(String text, String style) {
		super(text);

		getStyleClass().addAll("ui-btn-basic", "ui-btn-"+style);
	}
}
