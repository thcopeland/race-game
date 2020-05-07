package race;

import race.level.GameLocation;
import race.level.Level;
import race.level.Terrain;

public class Player {
    private static final double acceleration = 0.01;

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
        z = 0;
        vx = 0;
        vy = 0;
        vz = 0;
        sprite = s;
        facing = Direction.DOWN;
        score = 0;

        animations = new AnimationQueue();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public double getVz() {
        return vz;
    }

    public int getScore() {
        return score;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public GameLocation getSpawnPoint() {
        return spawnPoint;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void score() {
        score += 1;
    }

    public void move(Direction dir) {
        if (animations.isEmpty()) {
            facing = dir;

            double acceleration = onGround() ? Player.acceleration : Player.acceleration / 4;
            if (dir == Direction.UP)
                vy -= acceleration;
            else if (dir == Direction.DOWN)
                vy += acceleration;
            else if (dir == Direction.LEFT)
                vx -= acceleration;
            else if (dir == Direction.RIGHT)
                vx += acceleration;
        }
    }

    public void jump() {
        if (animations.isEmpty() && onGround()) {
            vz = 0.063;
        }
    }

    public void update(Level level) {
        if (animations.isEmpty()) {
            if (onGround()) {
                terrain = level.getTerrainAt(x, y);

                if (onHole(level)) {
                    animations.add(new FallAnimation(this, getSpawnPoint()));
                } else if (terrain == Terrain.LAVA) {
                    animations.add(new BurningAnimation(this));
                }

                vx *= terrain.getFriction();
                vy *= terrain.getFriction();
            }

            if (getSpeed() > terrain.getSpeed()) {
                double factor = Math.sqrt(getSpeed() / terrain.getSpeed());

                vx /= factor;
                vy /= factor;
            }

            x += vx;
            y += vy;
            z += vz;

            if (x < 0 || x > level.getMap().getWidth()) {
                x -= vx;
                vx *= -1;
            }
            if (y < 0 || y > level.getMap().getHeight()) {
                y -= vy;
                vy *= -1;
            }

            vz -= 0.00063;

            handleGroundCollision();

            sprite.tick();
        } else {
            animations.update();
        }
    }

    public void render(Renderer renderer) {
        if (animations.isEmpty()) {
            sprite.render(renderer, getX() - 0.75, getY() - 1.88 - getZ() * 2 / 3, getAnimationIndex());
        } else {
            animations.render(renderer);
        }
    }

    public void respawn(GameLocation s) {
        spawnPoint = s;
        vx = vy = 0;
        facing = Direction.DOWN;
        animations.add(new SpawnAnimation(s, this));
    }

    public double getSpeed() {
        return Math.hypot(vx, vy);
    }

    public boolean onGround() {
        return Math.abs(z - terrain.getDepth()) < 1;
    }

    public boolean onHole(Level level) {
        return terrain == Terrain.HOLE && level.getTerrainAt(x - vx, y - vy) == Terrain.HOLE;
    }

    public void handleGroundCollision() {
        if (z < terrain.getDepth()) {
            vz = 0;
            z = terrain.getDepth();
        }
    }

    public int getAnimationIndex() {
        if (getSpeed() < 0.003)
            return facing.ordinal();
        return facing.ordinal() + 4;
    }
}
