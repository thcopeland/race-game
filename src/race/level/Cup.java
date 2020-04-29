package race.level;

import javafx.scene.canvas.GraphicsContext;
import race.Assets;
import race.Player;

public class Cup extends Obstacle {
    public static final int offsetX = -16;
    public static final int offsetY = -55;

    public Cup(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(GraphicsContext ctx) {
        ctx.drawImage(Assets.OBSTACLES, 192, 64, 32, 64, getX() + offsetX, getY() + offsetY, 32, 64);
    }

    @Override
    public void update(Player... players) {
        // nothing
    }
}
