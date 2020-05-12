package race.level;

public enum Terrain {
    GRASS    (0.93, 0.0000100, 0),
    DIRT     (0.93, 0.0000124, 0),
    ROCK     (0.93, 0.0000124, 0),
    LAVA     (0.93, 0.0000036, -0.31),
    WATER    (0.95, 0.0000052, -0.31),
    BRACKISH (0.95, 0.0000012, -0.31),
    HOLE     (0.95, 0.0000100, 0),
    SNOW     (0.8,  0.0000030, 0),
    ICE      (0.995, 0.0000120, -0.31);

    private double friction;
    private double speed;
    private double depth;

    private Terrain(double friction, double speed, double depth) {
        this.friction = friction;
        this.speed = speed; // units (generally around 32px) per microsecond
        this.depth = depth;
    }

    public double getFriction() {
        return friction;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDepth() {
        return depth;
    }
}
