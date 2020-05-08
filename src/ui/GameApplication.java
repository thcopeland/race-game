package ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import race.Game;
import race.Player;
import race.Renderer;
import race.Sprite;

/**
 * This class is a layer between the race instance and the window/event handling
 * stuff. The scene is created and managed by this class, and events (e.g. mouse
 * clicks, key presses, etc) are handled here as well before being passed onto a
 * Game as appropriate.
 */
public class GameApplication {
    private Stage stage;

    private Game game;

    private Canvas canvas;
    private Renderer renderer;

    private AnimationTimer clock;

    private HashSet<KeyCode> keys;

    /**
     * Create a new GameApplication instance.
     *
     * @param stage the race window
     * @throws IOException    if the race fails to load a level
     * @throws ParseException if the race fails to load a level
     */
    public GameApplication(Stage stage, Sprite s1, Sprite s2) throws IOException, ParseException {
        this.stage = stage;
        canvas = new Canvas(stage.getWidth(), stage.getHeight());
        renderer = new Renderer(canvas.getGraphicsContext2D());
        game = new Game(s1, s2);

        clock = new AnimationTimer() {
            private long last = System.nanoTime();

            @Override
            public void handle(long t) {
                tick((t - last)/1000); // convert to microseconds
                last = t;
            }
        };

        keys = new HashSet<KeyCode>(8);
    }

    public void activate() {
        VBox pane = new VBox(canvas);
        pane.setOnKeyPressed(e -> keys.add(e.getCode()));
        pane.setOnKeyReleased(e -> keys.remove(e.getCode()));

        stage.getScene().setRoot(pane);
        pane.requestFocus();

        clock.start();
    }

    public void tick(long t) {
        handleKeys();
        game.tick(t);
        game.render(renderer);

        if (game.isFinished()) {
            clock.stop();

            new EndgameScreen(stage, game.getPlayer1(), game.getPlayer2()).activate();
        }
    }

    public void handleKeys() {
        if (keys.contains(KeyCode.W))
            game.movePlayer1(Player.Direction.UP);
        else if (keys.contains(KeyCode.A))
            game.movePlayer1(Player.Direction.LEFT);
        else if (keys.contains(KeyCode.S))
            game.movePlayer1(Player.Direction.DOWN);
        else if (keys.contains(KeyCode.D))
            game.movePlayer1(Player.Direction.RIGHT);

        if (keys.contains(KeyCode.C))
            game.jumpPlayer1();

        if (keys.contains(KeyCode.I))
            game.movePlayer2(Player.Direction.UP);
        else if (keys.contains(KeyCode.J))
            game.movePlayer2(Player.Direction.LEFT);
        else if (keys.contains(KeyCode.K))
            game.movePlayer2(Player.Direction.DOWN);
        else if (keys.contains(KeyCode.L))
            game.movePlayer2(Player.Direction.RIGHT);

        if (keys.contains(KeyCode.N))
            game.jumpPlayer2();
    }
}
