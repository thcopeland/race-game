package race.level;

import race.Assets;
import race.Renderer;

public class Barrel extends Obstacle {
    public Barrel(double x, double y) {
        super(x, y);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderImage(Assets.OBSTACLES, 192, 0, 32, 64, getX() - 0.5, getY() - 1.25);
    }

    @Override
    public double collisionRadius() {
        return 0.5;
    }

    @Override
    public double collisionHeight() {
        return 1;
    }
}
