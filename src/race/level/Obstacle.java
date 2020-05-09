package race.level;

import java.text.ParseException;
import java.util.Scanner;

import race.Player;
import race.Renderer;

public abstract class Obstacle {
    protected double x, y;

    public Obstacle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Renderer renderer);

    public void update(long t, Player... players) {
        for (Player p : players) {
            double dist2 = Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2);

            if (dist2 < Math.pow(this.collisionRadius(), 2) && p.getZ() <= this.collisionHeight()) {
                p.setVx(p.getVx() + Math.copySign(0.000001 / dist2, p.getX() - x));
                p.setVy(p.getVy() + Math.copySign(0.000001 / dist2, p.getY() - y));
            }
        }
    };

    public double collisionRadius() {
        return 0.4;
    }

    public double collisionHeight() {
        return 3;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String serialize() {
        return String.format("%s %.2f %.2f", this.getClass().getSimpleName().toUpperCase(), getX(), getY());
    }

    public static Obstacle deserialize(String source) throws ParseException {
        Scanner s = new Scanner(source);

        switch (s.next()) {
        case "CUP":
            return new Cup(s.nextDouble(), s.nextDouble());
        case "BIGMAPLE":
            return new BigMaple(s.nextDouble(), s.nextDouble());
        case "LITTLEMAPLE":
            return new LittleMaple(s.nextDouble(), s.nextDouble());
        case "BIGPINE":
            return new BigPine(s.nextDouble(), s.nextDouble());
        case "LITTLEPINE":
            return new LittlePine(s.nextDouble(), s.nextDouble());
        case "BARREL":
            return new Barrel(s.nextDouble(), s.nextDouble());
        case "MINE":
            return new Mine(s.nextDouble(), s.nextDouble());
        default:
            throw new ParseException("unknown obstacle type", 0);
        }
    }
}
