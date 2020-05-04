package ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
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
 * clicks, key presses, etc) are handled here as well before being passed onto a
 * Game as appropriate.
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
     * 
     * @param stage the race window
     * @throws IOException    if the race fails to load a level
     * @throws ParseException if the race fails to load a level
     */
    public GameApplication(Stage stage, Sprite s1, Sprite s2) throws IOException, ParseException {
        this.stage = stage;
        canvas = new Canvas(stage.getWidth(), stage.getHeight());
        context = canvas.getGraphicsContext2D();

        game = new Game(s1, s2);

        clock = new AnimationTimer() {
            @Override
            public void handle(long t) {
                tick();
            }
        };

        keys = new HashSet<KeyCode>(8);
    }

    /**
     * Create a canvas instance, grab the drawing context and add it to the window.
     */
    public void activate() {
        // render the race sceen
        VBox pane = new VBox(canvas);
        pane.setOnKeyPressed(e -> keys.add(e.getCode()));
        pane.setOnKeyReleased(e -> keys.remove(e.getCode()));

        stage.getScene().setRoot(pane);
        pane.requestFocus();

        // start the race timer (and the race)
        clock.start();
    }

    /**
     * this function is called around 60 times per second. it sends a tick to the
     * race object and renders the race to the canvas.
     */
    public void tick() {
        handleKeys();
        game.tick();
        game.render(context);

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
