package race;

import javafx.scene.canvas.GraphicsContext;

public class ExplosionAnimation extends Animation {

	private Player player;
	private double sourceX, sourceY;
	private long clock;

	public ExplosionAnimation(Player player, double x, double y) {
		this.player = player;
		sourceX = x;
		sourceY = y;
		clock = 0;
	}

	@Override
	public boolean isDone() {
		return clock > 320;
	}

	@Override
	public void onCompletion() {
		player.respawn(player.getSpawnPoint());
	}

	@Override
	public void render(GraphicsContext ctx) {
		long frame = clock / 5;
		ctx.drawImage(Assets.EFFECTS, 100 * frame, 0, 100, 100, sourceX - 50, sourceY - 100, 100, 100);
	}

	@Override
	public void update() {
		clock += 1;
	}
}
