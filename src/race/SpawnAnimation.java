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
		this.clock = spawnPoint.getY();
	}

	@Override
	public boolean isDone() {
		return this.clock < 0;
	}

	@Override
	public void render(GraphicsContext ctx) {
		this.player.getSprite().render(ctx, player.getX()-24 , player.getY()-60, 2);
	}

	@Override
	public void update() {
		this.player.setX(this.spawnPoint.getX());
		this.player.setY(this.spawnPoint.getY() - this.clock--);
	}

}
