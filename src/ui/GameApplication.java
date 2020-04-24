package ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import race.Game;
import race.Player;
import race.Sprite;

/**
 * This class is a layer between the race instance and the window/event handling
 * stuff. The scene is created and managed by this class, and events (e.g. mouse
 * clicks, key presses, etc) are handled here as well before being passed onto
 * a Game as appropriate.
 */
public class GameApplication {
	private Stage stage;

	private Game game;

	private Canvas canvas;
	private GraphicsContext context;

	private AnimationTimer clock;

	private HashSet<KeyCode> keys;

	/**
	 * Create a new GameApplication instance.
	 * @param stage the race window
	 * @throws IOException if the race fails to load a level
	 * @throws ParseException if the race fails to load a level
	 */
	public GameApplication(Stage stage, Sprite s1, Sprite s2) throws IOException, ParseException {
		this.stage = stage;
		this.canvas = new Canvas(stage.getWidth(), stage.getHeight());
		this.context = this.canvas.getGraphicsContext2D();

		this.game = new Game(s1, s2);

		this.clock = new AnimationTimer() {
			public void handle(long t) {
				tick();
			}
		};

		this.keys = new HashSet<KeyCode>(8);
	}

	/**
	 * Create a canvas instance, grab the drawing context and add it to the
	 * window.
	 */
	public void activate() {
		// render the race sceen
		VBox pane = new VBox(this.canvas);
		pane.setOnKeyPressed(e -> keys.add(e.getCode()));
		pane.setOnKeyReleased(e -> keys.remove(e.getCode()));

		this.stage.getScene().setRoot(pane);
		pane.requestFocus();

		// start the race timer (and the race)
		this.clock.start();
	}

	/**
	 * this function is called around 60 times per second. it sends a tick to
	 * the race object and renders the race to the canvas.
	 */
	public void tick() {
		handleKeys();
		this.game.tick();
		this.game.render(this.context);

		if (this.game.isFinished()) {
			this.clock.stop();

			new EndgameScreen(this.stage, this.game.getPlayer1(), this.game.getPlayer2()).activate();
		}
	}

	public void handleKeys() {
		if(keys.contains(KeyCode.W))      this.game.movePlayer1(Player.Direction.UP);
		else if(keys.contains(KeyCode.A)) this.game.movePlayer1(Player.Direction.LEFT);
		else if(keys.contains(KeyCode.S)) this.game.movePlayer1(Player.Direction.DOWN);
		else if(keys.contains(KeyCode.D)) this.game.movePlayer1(Player.Direction.RIGHT);

		if(keys.contains(KeyCode.C)) this.game.jumpPlayer1();

		if(keys.contains(KeyCode.I))      this.game.movePlayer2(Player.Direction.UP);
		else if(keys.contains(KeyCode.J)) this.game.movePlayer2(Player.Direction.LEFT);
		else if(keys.contains(KeyCode.K)) this.game.movePlayer2(Player.Direction.DOWN);
		else if(keys.contains(KeyCode.L)) this.game.movePlayer2(Player.Direction.RIGHT);

		if(keys.contains(KeyCode.N)) this.game.jumpPlayer2();
	}
}
