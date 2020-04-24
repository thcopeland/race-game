package race.level;

public enum Terrain {
    GRASS(0.93, 0.8, 0),
    DIRT(0.93, 1.0, 0),
    ROCK(0.93, 1.0, 0),
    LAVA(0.93, 0.3, -10),
    WATER(0.95, 0.4, -10),
    BRACKISH(0.95, 0.1, -10),
    HOLE(0.95, 0.8, -1);

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
