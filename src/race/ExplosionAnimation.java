package race;

import javafx.scene.canvas.GraphicsContext;
import race.level.Mine;

public class ExplosionAnimation extends Animation {

    private Mine source;
    private long clock;

    public ExplosionAnimation(Mine source) {
        this.source = source;
        clock = 0;
    }

    @Override
    public boolean isDone() {
        return clock > 320;
    }

    @Override
    public void render(GraphicsContext ctx) {
        long frame = clock / 5;
        ctx.drawImage(Assets.EFFECTS, 100 * frame, 0, 100, 100, source.getX() - 50, source.getY() - 100, 100, 100);
    }

    @Override
    public void update() {
        clock += 1;
    }
}
