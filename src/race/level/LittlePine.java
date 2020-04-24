package race.level;

import javafx.scene.canvas.GraphicsContext;
import race.Assets;

public class LittlePine extends Obstacle {
    public static final int offsetX = -48;
    public static final int offsetY = -120;

    public LittlePine(int x, int y) {
	super(x, y);
    }

    @Override
    public void render(GraphicsContext ctx) {
	ctx.drawImage(Assets.OBSTACLES, 96, 137, 96, 137, getX()+offsetX, getY()+offsetY, 96, 137);
    }
}
