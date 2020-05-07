package race.level;

public enum Terrain {
    GRASS    (0.93, 0.025, 0),
    DIRT     (0.93, 0.031, 0),
    ROCK     (0.93, 0.031, 0),
    LAVA     (0.93, 0.009, -0.31),
    WATER    (0.95, 0.013, -0.31),
    BRACKISH (0.95, 0.003, -0.31),
    HOLE     (0.95, 0.025, 0);

    private double friction;
    private double speed;
    private double depth;

    private Terrain(double friction, double speed, double depth) {
        this.friction = friction;
        this.speed = speed;
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
