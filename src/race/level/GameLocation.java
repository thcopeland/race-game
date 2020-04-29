package race.level;

import race.Player;

public class GameLocation {
    protected int x, y;

    public GameLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @return a String representation of a GameLocation.
     */
    public String serialize() {
        return String.format("%d %d", x, y);
    }

    /**
     * Read a GameLocation from a string
     */
    public static GameLocation deserialize(String src) {
        String[] split = src.split(" ");

        return new GameLocation(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public boolean isAtLocation(Player p) {
        return Math.hypot(x - p.getX(), y - p.getY()) < 20 && p.getZ() < 60;
    }
}
