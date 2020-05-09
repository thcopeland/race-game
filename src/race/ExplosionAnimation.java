package race;

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
        return clock > 1408000;
    }

    @Override
    public void render(Renderer renderer) {
        long frame = (clock / 22000) & 63;

        renderer.renderImage(Assets.EFFECTS, 100 * frame, 0, 100, 100, source.getX() - 1.56, source.getY() - 3.13);
    }

    @Override
    public void update(long t) {
        clock += t;
    }
}
