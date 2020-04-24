package race;

import javafx.scene.canvas.GraphicsContext;

public class BurningAnimation extends Animation {

    private Player player;
    private double x, y, z, vx, vy, vz;
    private long clock;

    public BurningAnimation(Player player) {
	this.player = player;
	x = player.getX();
	y = player.getY();
	vx = player.getVx();
	vy = player.getVy();
	z = 0;
	vz = 0;
	clock = 0;

	this.player.setX(-100);
	this.player.setY(-100);
    }


    @Override
    public boolean isDone() {
	return z >= 64;
    }

    @Override
    public void onCompletion() {
	player.respawn(player.getSpawnPoint());
    }

    @Override
    public void render(GraphicsContext ctx) {
	long frame = clock / 3 % 60;

	int depth = (int) z;
	ctx.drawImage(Assets.EFFECTS, 64*frame, 100, 64, 64-depth, x-32, y-64+depth, 64, 64-depth);
    }

    @Override
    public void update() {
	x += vx;
	y += vy;
	z += vz;
	vy *= 0.96;
	vx *= 0.96;

	if (clock > 100) {
	    vz += 0.01;
	}

	clock++;
    }

}
