package race;

import javafx.scene.canvas.GraphicsContext;

public class BurningAnimation extends Animation {

	private Player player;
	private double x, y, z, vx, vy, vz;
	private long clock;

	public BurningAnimation(Player player) {
		this.player = player;
		this.x = player.getX();
		this.y = player.getY();
		this.vx = player.getVx();
		this.vy = player.getVy();
		this.z = 0;
		this.vz = 0;
		this.clock = 0;

		this.player.setX(-100);
		this.player.setY(-100);
	}


	@Override
	public boolean isDone() {
		return this.z >= 64;
	}

	@Override
	public void onCompletion() {
		player.respawn(player.getSpawnPoint());
	}

	@Override
	public void render(GraphicsContext ctx) {
		long frame = this.clock / 3 % 60;

		int depth = (int) z;
		ctx.drawImage(Assets.EFFECTS, 64*frame, 100, 64, 64-depth, x-32, y-64+depth, 64, 64-depth);
	}

	@Override
	public void update() {
		this.x += vx;
		this.y += vy;
		this.z += vz;
		this.vy *= 0.96;
		this.vx *= 0.96;

		if (this.clock > 100) {
			this.vz += 0.01;
		}

		this.clock++;
	}

}
