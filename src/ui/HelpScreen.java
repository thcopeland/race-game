package ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelpScreen {
    private Stage stage;

    public HelpScreen(Stage stage) {
        this.stage = stage;
    }

    public void activate() {
        stage.getScene().setRoot(buildScene());
    }

    public Parent buildScene() {
        VBox pane = new VBox();
        pane.setSpacing(50);
        pane.setAlignment(Pos.CENTER);
        pane.getStyleClass().add("ui-parchment-background");

        Text content = new Text("Player 1 can use the WASD keys to move around\n"
                + "  and C to jump. In general, player 1 spawns\n" + "  on the left side of the screen.\n" + "\n"
                + "Player 2 uses IJKL to move, and N to jump. He\n" + "  generally spawns on the right side of the\n"
                + "  screen.\n" + "\n" + "May the best man win!");

        content.getStyleClass().add("ui-large-text-blob");
        content.setFill(Color.BLACK);

        UIButton exitButton = new UIButton("Back", "green");
        exitButton.setOnMouseClicked(e -> new MainScreen(stage).activate());

        pane.getChildren().addAll(content, exitButton);

        return pane;
    }
}