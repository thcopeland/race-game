package ui;

import java.io.IOException;
import java.text.ParseException;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import race.PlayerSprites;
import race.Renderer;
import race.Sprite;

public class SpriteSelectionScreen {
    private Stage stage;

    private Sprite player1, player2;

    public SpriteSelectionScreen(Stage stage) {
        this.stage = stage;
    }

    public void activate() {
        stage.getScene().setRoot(buildScene());
    }

    public Parent buildScene() {
        GridPane mainPane = new GridPane();
        mainPane.setVgap(130);

        GridPane spritePane = new GridPane();

        Text status = new Text("Player One");
        status.setFill(Color.GOLD);
        status.setFont(Font.font("", FontWeight.BOLD, 35));

        UIButton continueButton = new UIButton("Continue", "green");
        continueButton.setDisable(true);

        Sprite[] sprites = PlayerSprites.sprites();
        int width = (int) stage.getScene().getWidth() / sprites.length,
                height = PlayerSprites.HEIGHT * width / PlayerSprites.WIDTH;

        for (int i = 0; i < sprites.length; i++) {
            int spriteIndex = i;
            Sprite sprite = sprites[i];
            Canvas display = new Canvas(width, height);
            GraphicsContext ctx = display.getGraphicsContext2D();
            Renderer renderer = new Renderer(ctx);
            ctx.setImageSmoothing(false);
            sprite.renderDirectly(renderer.getContext(), 0, 0, width, height, 2, 0);

            display.setOnMouseClicked(e -> {
                if (player1 == null) {
                    player1 = PlayerSprites.sprites()[spriteIndex];
                    display.getStyleClass().add("selected-glow");
                    status.setText("Player Two");
                } else if (player2 == null) {
                    player2 = PlayerSprites.sprites()[spriteIndex];
                    display.getStyleClass().add("selected-glow");
                    continueButton.setDisable(false);
                }
            });

            spritePane.add(display, i, 1);
        }

        continueButton.setOnMouseClicked(e -> {
            try {
                new GameApplication(stage, player1, player2).activate();
            } catch (IOException | ParseException e1) {
                stage.close();
                e1.printStackTrace();
            }
        });

        HBox statusContainer = new HBox(status);
        statusContainer.setAlignment(Pos.CENTER);

        mainPane.add(spritePane, 0, 0, 3, 1);
        mainPane.add(statusContainer, 0, 1, 3, 1);
        mainPane.add(continueButton, 3, 1);

        mainPane.getStyleClass().add("ui-parchment-background");
        return mainPane;
    }
}
