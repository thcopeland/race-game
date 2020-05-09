package race;

import race.level.GameLocation;
import race.level.Level;
import race.level.Terrain;

public class Player {
    private static final double acceleration = 0.000001;

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

    public void setZ(double z) {
        this.z = z;
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
            vz = 0.00003;
        }
    }

    public void update(long t, Level level) {
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

            x += vx*t;
            y += vy*t;
            z += vz*t;

            if (x < 0.4 || x >= level.getMap().getWidth()-0.4) {
                x -= vx*t;
                vx *= -1;
            }
            if (y < 1.5 || y >= level.getMap().getHeight()-0.8) {
                y -= vy*t;
                vy *= -1;
            }

            vz -= 0.00000000008*t;

            handleGroundCollision();

            sprite.tick(t);
        } else {
            animations.update(t);
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
        vx = vy = vz = z = 0;
        facing = Direction.DOWN;
        animations.add(new SpawnAnimation(s, this));
    }

    public double getSpeed() {
        return Math.hypot(vx, vy);
    }

    public boolean onGround() {
        return Math.abs(z - terrain.getDepth()) < 0.1;
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
        if (getSpeed() < 0.00000012)
            return facing.ordinal();
        return facing.ordinal() + 4;
    }
}
