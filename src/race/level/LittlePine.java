package race.level;

import race.Assets;
import race.Renderer;

public class LittlePine extends Obstacle {
    public LittlePine(double x, double y) {
        super(x, y);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderImage(Assets.OBSTACLES, 96, 137, 96, 137, getX() - 1.5, getY() - 3.75);
    }
}
