package ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainScreen {
    private Stage stage;

    public MainScreen(Stage stage) {
        this.stage = stage;
    }

    public void activate() {
        stage.getScene().setRoot(buildScene());
    }

    public Parent buildScene() {
        GridPane pane = new GridPane();
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);

        UIButton playButton = new UIButton("Continue", "green"), helpButton = new UIButton("Help", "purple"),
                creditsButton = new UIButton("Credits", "blue");

        playButton.setOnMouseClicked(e -> new SpriteSelectionScreen(stage).activate());
        helpButton.setOnMouseClicked(e -> new HelpScreen(stage).activate());
        creditsButton.setOnMouseClicked(e -> new CreditScreen(stage).activate());

        pane.add(playButton, 0, 0);
        pane.add(helpButton, 0, 1);
        pane.add(creditsButton, 0, 2);

        pane.getStyleClass().add("ui-parchment-background");

        return pane;
    }
}
