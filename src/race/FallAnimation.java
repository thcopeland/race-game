package race;

import race.level.GameLocation;

public class FallAnimation extends Animation {
    private GameLocation spawn;
    private Player player;
    private long clock;

    double x, y, vx, vy;

    public FallAnimation(Player player, GameLocation spawn) {
        this.player = player;
        this.spawn = spawn;
        clock = 0;

        x = player.getX();
        y = player.getY();
        vx = player.getVx();
        vy = player.getVy() / 4 - player.getVz() / 4;

        player.setX(-10);
        player.setY(-10);
    }

    @Override
    public boolean isDone() {
        return clock > 120;
    }

    @Override
    public void onCompletion() {
        player.respawn(spawn);
    }

    @Override
    public void render(Renderer renderer) {
        double transparency = 1.0 - clock / 120.0;

        renderer.getContext().setGlobalAlpha(transparency);
        player.getSprite().render(renderer, x - 0.75, y - 1.88, player.getAnimationIndex());
        renderer.getContext().setGlobalAlpha(1.0);
    }

    @Override
    public void update(long t) {
        x += vx*t;
        y += vy*t;
        vx *= 0.99;
        vy += 0.00000000003*t;

        clock += 1;
    }
}
