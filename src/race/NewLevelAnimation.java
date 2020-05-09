package race;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import race.level.Level;
import race.level.Obstacle;

public class NewLevelAnimation extends Animation {
    private int levelNumber;
    private Level level;
    private long clock;

    public NewLevelAnimation(Level level, int levelNumber) {
        this.level = level;
        this.levelNumber = levelNumber;
        clock = 0;
    }

    @Override
    public boolean isDone() {
        return clock > 3000000;
    }

    @Override
    public void render(Renderer renderer) {
        GraphicsContext ctx = renderer.getContext();
        ctx.setFill(Color.BLACK);

        if (clock < 500000) {
            ctx.fillRect(0, 0, renderer.getViewportWidth(), renderer.getViewportHeight());
            ctx.setFill(Color.WHITE);

            ctx.save();
            ctx.setTextAlign(TextAlignment.CENTER);
            ctx.setFont(Font.font(clock / 2500));
            ctx.fillText("Level " + levelNumber, renderer.getViewportWidth() / 2, renderer.getViewportHeight() / 2);
            ctx.restore();
        } else if (clock > 1500000) {
            level.getMap().render(renderer);

            for (Obstacle o : level.getObstacles()) {
                o.render(renderer);
            }

            ctx.setFill(Color.BLACK);
            ctx.setGlobalAlpha(1 - (clock - 1500000) / 1500000.0);
            ctx.fillRect(0, 0, renderer.getViewportWidth(), renderer.getViewportHeight());
            ctx.setGlobalAlpha(1.0);
        }

    }

    @Override
    public void update(long t) {
        clock += t;
    }
}
