package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
 * This class (specifically, the #start method) is the entry point for the race.
 */
public class StartupApplication extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("The Great Race");
		stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
		stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
		stage.setScene(new Scene(new Pane()));
		stage.getScene().getStylesheets().add("assets/stylesheets/ui.css");
		stage.show();

		new MainScreen(stage).activate();
	}
}
