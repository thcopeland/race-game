package race.level;

import java.text.ParseException;
import java.util.Scanner;

import javafx.scene.canvas.GraphicsContext;
import race.Player;

public abstract class Obstacle {
    protected int x, y;

    public Obstacle(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public abstract void render(GraphicsContext ctx);

    public void update(Player ...players) {
	for (Player p : players) {
	    double dist2 = Math.pow(p.getX()-x, 2) + Math.pow(p.getY()-y, 2);

	    if (dist2 < Math.pow(this.collisionRadius(), 2) && p.getZ() <= this.collisionHeight()) {
		p.setVx(p.getVx() + Math.copySign(32/dist2, p.getX()-x));
		p.setVy(p.getVy() + Math.copySign(32/dist2, p.getY()-y));
	    }
	}
    };

    public int collisionRadius() {
	return 16;
    }

    public int collisionHeight() {
	return 100;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public String serialize() {
	return String.format("%s %d %d", this.getClass().getSimpleName().toUpperCase(), getX(), getY());
    }

    public static Obstacle deserialize(String source) throws ParseException {
	Scanner s = new Scanner(source);

	switch (s.next()) {
	case "CUP":
	    return new Cup(s.nextInt(), s.nextInt());
	case "BIGMAPLE":
	    return new BigMaple(s.nextInt(), s.nextInt());
	case "LITTLEMAPLE":
	    return new LittleMaple(s.nextInt(), s.nextInt());
	case "BIGPINE":
	    return new BigPine(s.nextInt(), s.nextInt());
	case "LITTLEPINE":
	    return new LittlePine(s.nextInt(), s.nextInt());
	case "BARREL":
	    return new Barrel(s.nextInt(), s.nextInt());
	case "MINE":
	    return new Mine(s.nextInt(), s.nextInt());
	default:
	    throw new ParseException("unknown obstacle type", 0);
	}
    }
}
