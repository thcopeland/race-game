package race;

import javafx.scene.canvas.GraphicsContext;
import race.level.*;

public class Player {
	private static final double acceleration = 0.1;

	/**
	 * The various directions a player may go. The order of these directions is
	 * important, it <b>must</b> correspond to PlayerSprites.ANIMATIONS.
	 */
	public enum Direction {
		UP, RIGHT, DOWN, LEFT
	}

	private double x, y, z;
	private double vx, vy, vz;
	private Sprite sprite;
	private Direction facing;

	private int score;

	private Terrain terrain = Terrain.GRASS;

	private GameLocation spawnPoint;

	private AnimationQueue animations;

	public Player(int x, int y, Sprite s) {
		this.x = x;
		this.y = y;
		this.z = 0;
		this.vx = 0;
		this.vy = 0;
		this.vz = 0;
		this.sprite = s;
		this.facing = Direction.DOWN;
		this.score = 0;

		this.animations = new AnimationQueue();
	}

	public double getX() { return x; }
	public double getY() { return y; }
	public double getZ() { return z; }
	public double getVx() { return vx; }
	public double getVy() { return vy; }
	public double getVz() { return vz; }
	public int getScore() { return score; }
	public Sprite getSprite() { return sprite; }
	public GameLocation getSpawnPoint() { return spawnPoint; }
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }
	public void setVx(double vx) { this.vx = vx; }
	public void setVy(double vy) { this.vy = vy; }
	public void score() { this.score += 1; }

	public void move(Direction dir) {
		if (animations.isEmpty()) {
			this.facing = dir;

			double acceleration = onGround() ? Player.acceleration : Player.acceleration/4;
			if (dir == Direction.UP)         this.vy -= acceleration;
			else if (dir == Direction.DOWN)  this.vy += acceleration;
			else if (dir == Direction.LEFT)  this.vx -= acceleration;
			else if (dir == Direction.RIGHT) this.vx += acceleration;
		}
	}

	public void jump() {
		if (this.animations.isEmpty() && onGround()) {
			this.vz = 2;
		}
	}

	public void update(Level level) {
		if (animations.isEmpty()) {
			if (onGround()) {
				terrain = level.getTerrainAt(x, y);


				if (onHole(level)) {
					animations.add(new FallAnimation(this, getSpawnPoint()));
				} else if(terrain == Terrain.LAVA) {
					animations.add(new BurningAnimation(this));
				}

				this.vx *= terrain.getFriction();
				this.vy *= terrain.getFriction();
			}

			if (getSpeed() > terrain.getSpeed()) {
				double factor = getSpeed() / terrain.getSpeed();

				this.vx /= Math.sqrt(factor);
				this.vy /= Math.sqrt(factor);
			}

			this.x += vx;
			this.y += vy;
			this.z += vz;

			if (this.x < 0 || this.x > level.getWidth()) {
				this.x -= vx;
				this.vx *= -1;
			}
			if (this.y < 0 || this.y > level.getHeight()) {
				this.y -= vy;
				this.vy *= -1;
			}

			this.vz -= 0.02;

			handleGroundCollision();

			this.sprite.tick();
		} else {
			animations.update();
		}
	}

	public void render(GraphicsContext ctx) {
		if (animations.isEmpty()) {
			sprite.render(ctx, getX()-24, getY()-60-getZ()/1.5, getAnimationIndex());
		} else {
			animations.render(ctx);
		}
	}

	public void respawn(GameLocation s) {
		this.spawnPoint = s;
		this.vx = this.vy = 0;
		this.facing = Direction.DOWN;
		this.animations.add(new SpawnAnimation(s, this));
	}

	public void explode(Obstacle source) {
		this.animations.add(new ExplosionAnimation(this, source.getX(), source.getY()));
	}

	public double getSpeed() {
		return Math.hypot(this.vx, this.vy);
	}

	public boolean onGround() {
		return Math.abs(this.z - this.terrain.getDepth()) < 1;
	}

	public boolean onHole(Level level) {
		return this.terrain == Terrain.HOLE && level.getTerrainAt(x-vx, y-vy) == Terrain.HOLE;
	}

	public void handleGroundCollision() {
		if (this.z < this.terrain.getDepth()) {
			this.vz = 0;
			this.z = this.terrain.getDepth();
		}
	}

	public int getAnimationIndex() {
		if (getSpeed() < 0.1)
			return this.facing.ordinal();
		return this.facing.ordinal() + 4;
	}
}
