package race.level;

import javafx.scene.canvas.GraphicsContext;
import race.Assets;
import race.Player;

public class Mine extends Obstacle {
	public static final int offsetX = -16;
	public static final int offsetY = -8;

	private double transparency;
	private boolean expended;

	public Mine(int x, int y) {
		super(x, y);
		expended = false;
		transparency = 0.0;
	}

	@Override
	public void render(GraphicsContext ctx) {
		if (!expended) {
			ctx.setGlobalAlpha(transparency);
			ctx.drawImage(Assets.OBSTACLES, 192, 192, 32, 64, getX() + offsetX, getY() + offsetY, 32, 64);
			ctx.setGlobalAlpha(1.0);
		}
	}

	@Override
	public void update(Player... players) {
		if (!expended) {
			double proximity = -1;

			for (Player p : players) {
				double dist2 = Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2);

				if (dist2 < 180 && p.getZ() < 10) {
					p.explode(this);
					expended = true;
				}

				if (proximity < 0 || dist2 < proximity)
					proximity = dist2;
			}

			transparency = calculateTransparency(proximity);
		}
	}

	private double calculateTransparency(double d) {
		if (d < 600)
			return 1.0;
		if (d < 3600)
			return 1 - (d - 600) / 3000;
		return 0;
	}

}
