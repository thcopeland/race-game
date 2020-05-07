package race;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VictoryAnimation extends Animation {
    private Player player;
    private Game game;
    private long clock;

    public VictoryAnimation(Player victor, Game game) {
        player = victor;
        this.game = game;
        clock = 0;
    }

    @Override
    public boolean isDone() {
        return clock > 720;
    }

    @Override
    public void onCompletion() {
        game.advanceLevel();
    }

    @Override
    public void render(Renderer renderer) {
        if (clock < 120) {
            game.renderGame(renderer);
            renderFadeToBlack(renderer, clock / 120.0);
        } else if (clock < 180) {
            /* nothing */
        } else if (clock < 600) {
            renderWinner(renderer, (clock - 180) / 360.0);
        } else {
            renderWinner(renderer, 1.0);
            renderFadeToBlack(renderer, (clock - 600) / 120.0);
        }
    }

    @Override
    public void update() {
        clock += 1;
    }

    private void renderWinner(Renderer renderer, double completion) {
        GraphicsContext ctx = renderer.getContext();

        ctx.setFill(Color.BLACK);
        ctx.fillRect(0, 0, renderer.getViewportWidth(), renderer.getViewportHeight());
        ctx.setImageSmoothing(false);

        int y = renderer.getViewportHeight() / 2 - 370;
        if (completion < 0.3)
            y += (0.3 - completion) * 1500;

        player.getSprite().renderDirectly(renderer.getContext(), renderer.getViewportWidth() / 2 - 240, y, 480, 640, 2, 0);

        ctx.setImageSmoothing(true);
    }

    private void renderFadeToBlack(Renderer renderer, double completion) {
        renderer.getContext().setFill(Color.rgb(0, 0, 0, completion));
        renderer.getContext().fillRect(0, 0, renderer.getViewportWidth(), renderer.getViewportHeight());
    }
}
