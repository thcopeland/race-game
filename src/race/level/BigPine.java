package race.level;

import race.Assets;
import race.Renderer;

public class BigPine extends Obstacle {
    public BigPine(double x, double y) {
        super(x, y);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderImage(Assets.OBSTACLES, 0, 137, 96, 137, getX() - 1.5, getY() - 3.75);
    }
}
