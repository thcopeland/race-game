package race;

import javafx.scene.canvas.GraphicsContext;
import race.level.GameLocation;

public class FallAnimation extends Animation {
	private GameLocation spawn;
	private Player player;
	private long clock;

	double x, y, vx, vy;

	public FallAnimation(Player player, GameLocation spawn) {
		this.player = player;
		this.spawn = spawn;
		this.clock = 0;

		this.x = player.getX();
		this.y = player.getY();
		this.vx = player.getVx();
		this.vy = player.getVy()/4 - player.getVz()/4;

		this.player.setX(-100);
		this.player.setY(-100);
	}

	@Override
	public boolean isDone() {
		return this.clock > 120;
	}

	@Override
	public void onCompletion() {
		player.respawn(spawn);
	}

	@Override
	public void render(GraphicsContext ctx) {
		double transparency = 1.0 - this.clock / 120.0;

		ctx.setGlobalAlpha(transparency);
		player.getSprite().render(ctx, x-24, y-60, player.getAnimationIndex());
		ctx.setGlobalAlpha(1.0);
	}

	@Override
	public void update() {
		x += vx;
		y += vy;
		vx *= 0.99;
		vy += 0.005;

		this.clock += 1;
	}
}
