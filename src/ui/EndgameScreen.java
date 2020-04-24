package ui;

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
import race.Player;

public class EndgameScreen {
    private Stage stage;
    private Player player1, player2;

    public EndgameScreen(Stage stage, Player player1, Player player2) {
	this.stage = stage;
	this.player1 = player1;
	this.player2 = player2;
    }

    public void activate() {
	stage.getScene().setRoot(buildScene());
    }

    public Parent buildScene() {
	GridPane pane = new GridPane();
	pane.setAlignment(Pos.CENTER);
	Text message = new Text("Game Over");
	HBox messageContainer = new HBox(message);
	messageContainer.setAlignment(Pos.CENTER);
	message.setFont(Font.font("", FontWeight.BOLD, 40));
	message.setFill(Color.GOLD);

	UIButton quit = new UIButton("Exit", "blue");
	HBox quitContainer = new HBox(quit);
	quitContainer.setAlignment(Pos.CENTER);

	quit.setOnMouseClicked(e -> stage.close());

	Canvas canvas = new Canvas(stage.getWidth(), stage.getHeight()/1.5);
	GraphicsContext ctx = canvas.getGraphicsContext2D();

	renderVictory(ctx);

	pane.add(messageContainer, 0, 0);
	pane.add(canvas, 0, 1);
	pane.add(quitContainer, 0, 2);
	pane.setStyle("-fx-background-color: black");

	return pane;
    }

    public void renderVictory(GraphicsContext ctx) {
	player1.getSprite().useFrame(1);
	player2.getSprite().useFrame(1);

	ctx.setImageSmoothing(false);
	ctx.setFill(Color.GOLD);
	ctx.setFont(Font.font(35));

	if (player1.getScore() > player2.getScore()) {
	    renderPlayer1Victory(ctx);
	} else if (player1.getScore() < player2.getScore()) {
	    renderPlayer2Victory(ctx);
	} else {
	    renderTie(ctx);
	}
    }

    public void renderPlayer1Victory(GraphicsContext ctx) {
	player1.getSprite().render(ctx, stage.getWidth()/2-100, 200, 120, 160, 5);

	ctx.save();
	ctx.translate(stage.getWidth()/2+100, 385);
	ctx.rotate(-90);
	player2.getSprite().render(ctx, 0, 0, 120, 160, 3);
	ctx.restore();

	ctx.fillText(Integer.toString(player1.getScore()), stage.getWidth()/2-50, 400);
	ctx.fillText(Integer.toString(player2.getScore()), stage.getWidth()/2+200, 400);
    }

    public void renderPlayer2Victory(GraphicsContext ctx) {
	player2.getSprite().render(ctx, stage.getWidth()/2, 200, 120, 160, 3);

	ctx.save();
	ctx.translate(stage.getWidth()/2-100, 260);
	ctx.rotate(90);
	player1.getSprite().render(ctx, 0, 0, 120, 160, 5);
	ctx.restore();

	ctx.fillText(Integer.toString(player2.getScore()), stage.getWidth()/2+50, 400);
	ctx.fillText(Integer.toString(player1.getScore()), stage.getWidth()/2-200, 400);
    }

    public void renderTie(GraphicsContext ctx) {
	player1.getSprite().render(ctx, stage.getWidth()/2-100, 200, 120, 160, 5);
	player2.getSprite().render(ctx, stage.getWidth()/2, 200, 120, 160, 3);

	ctx.fillText(Integer.toString(player1.getScore()), stage.getWidth()/2-50, 400);
	ctx.fillText(Integer.toString(player2.getScore()), stage.getWidth()/2+50, 400);
    }
}
