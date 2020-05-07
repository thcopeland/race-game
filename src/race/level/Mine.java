package race.level;

import race.Assets;
import race.ExplosionAnimation;
import race.Player;
import race.Renderer;

public class Mine extends Obstacle {
    private double transparency;
    private boolean expended;
    private ExplosionAnimation explosion;

    public Mine(double x, double y) {
        super(x, y);

        expended = false;
        transparency = 0.0;
        explosion = new ExplosionAnimation(this);
    }

    @Override
    public void render(Renderer renderer) {
        if (!expended) {
            renderer.getContext().setGlobalAlpha(transparency);
            renderer.renderImage(Assets.OBSTACLES, 192, 192, 32, 64, getX() - 0.5, getY() - 0.25);
            renderer.getContext().setGlobalAlpha(1.0);
        } else if (!explosion.isDone()) {
            explosion.render(renderer);
        }
    }

    @Override
    public void update(Player... players) {
        if (!expended) {
            double proximity = -1;

            for (Player p : players) {
                double dist2 = Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2);

                if (dist2 < 0.5 && p.getZ() < 0.3) {
                    expended = true;
                    p.respawn(p.getSpawnPoint());
                }

                if (proximity < 0 || dist2 < proximity) proximity = dist2;
            }

            transparency = calculateTransparency(proximity);
        } else if (!explosion.isDone()) {
            explosion.update();
        }
    }

    private double calculateTransparency(double d) {
        if (d < 0.59) return 1.0;
        if (d < 3.5) return 1 - (d - 0.59) / 2.91;
        return 0;
    }
}
