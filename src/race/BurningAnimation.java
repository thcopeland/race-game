package race;

public class BurningAnimation extends Animation {
    private Player player;
    private double x, y, vx, vy;
    private long clock;

    public BurningAnimation(Player player) {
        this.player = player;
        x = player.getX();
        y = player.getY();
        vx = player.getVx();
        vy = player.getVy();
        clock = 0;
    }

    @Override
    public boolean isDone() {
        return clock > 1500000;
    }

    @Override
    public void onCompletion() {
        player.respawn(player.getSpawnPoint());
    }

    @Override
    public void render(Renderer renderer) {
        long frame = clock / 12000 % 60;

        if (clock < 1000000 || clock % 16 < 10) {
            renderer.renderImage(Assets.EFFECTS, 64 * frame, 100, 64, 64, x-1, y-2);
        }
    }

    @Override
    public void update(long t) {
        x += vx*t;
        y += vy*t;
        vy *= 0.95;
        vx *= 0.95;

        clock += t;
    }
}
