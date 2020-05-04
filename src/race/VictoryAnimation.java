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
    public void render(GraphicsContext ctx) {
        if (clock < 120) {
            game.renderGame(ctx);
            renderFadeToBlack(ctx, clock / 120.0);
        } else if (clock < 180) {
            /* nothing */
        } else if (clock < 600) {
            renderWinner(ctx, (clock - 180) / 360.0);
        } else {
            renderWinner(ctx, 1.0);
            renderFadeToBlack(ctx, (clock - 600) / 120.0);
        }
    }

    @Override
    public void update() {
        clock += 1;
    }

    private void renderWinner(GraphicsContext ctx, double completion) {
        int width = (int) game.getLevel().getWidth(), height = (int) game.getLevel().getHeight();

        ctx.setFill(Color.BLACK);
        ctx.fillRect(0, 0, width, height);
        ctx.setImageSmoothing(false);

        int y = height / 2 - 370;
        if (completion < 0.3)
            y += (0.3 - completion) * 1500;

        player.getSprite().render(ctx, width / 2 - 240, y, 480, 640, 2);

        ctx.setImageSmoothing(true);
    }

    private void renderFadeToBlack(GraphicsContext ctx, double completion) {
        ctx.setFill(Color.rgb(0, 0, 0, completion));
        ctx.fillRect(0, 0, game.getLevel().getWidth(), game.getLevel().getHeight());
    }
}
