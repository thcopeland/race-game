package race.level;

import race.Assets;
import race.Player;
import race.Renderer;

public class Cup extends Obstacle {
    public Cup(double x, double y) {
        super(x, y);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderImage(Assets.OBSTACLES, 192, 64, 32, 64, getX() - 0.5, getY() - 1.72);
    }

    @Override
    public void update(Player... players) {
        // nothing
    }
}
