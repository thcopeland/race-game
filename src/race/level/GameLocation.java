package race.level;

import race.Player;

public class GameLocation {
    protected double x, y;

    public GameLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String serialize() {
        return String.format("%.2f %.2f", x, y);
    }

    public static GameLocation deserialize(String src) {
        String[] split = src.split(" ");

        return new GameLocation(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
    }

    public boolean isAtLocation(Player p) {
        return Math.hypot(x - p.getX(), y - p.getY()) < 0.8 && p.getZ() < 1.5;
    }
}
