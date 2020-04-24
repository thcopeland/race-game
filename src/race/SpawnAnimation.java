package race;

import javafx.scene.canvas.GraphicsContext;
import race.level.GameLocation;

public class SpawnAnimation extends Animation {
	private GameLocation spawnPoint;
	private Player player;
	private long clock;

	public SpawnAnimation(GameLocation spawnPoint, Player player) {
		this.spawnPoint = spawnPoint;
		this.player = player;
		clock = 2*spawnPoint.getY();
	}

	@Override
	public boolean isDone() {
		return clock < 0;
	}

	@Override
	public void render(GraphicsContext ctx) {
		player.getSprite().render(ctx, player.getX()-24 , player.getY()-60, 2);
	}

	@Override
	public void update() {
		player.setX(spawnPoint.getX());
		player.setY(spawnPoint.getY() - clock--);
	}
}
