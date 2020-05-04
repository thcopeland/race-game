package race.level;

import javafx.scene.canvas.GraphicsContext;
import race.Assets;

public class Barrel extends Obstacle {
    public static final int offsetX = -16;
    public static final int offsetY = -40;

    public Barrel(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(GraphicsContext ctx) {
        ctx.drawImage(Assets.OBSTACLES, 192, 0, 32, 64, getX() + offsetX, getY() + offsetY, 32, 64);
    }

    @Override
    public int collisionRadius() {
        return 15;
    }

    @Override
    public int collisionHeight() {
        return 40;
    }
}
