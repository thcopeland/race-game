package race;

import java.io.IOException;
import java.text.ParseException;
import java.util.Stack;

import race.level.GameLocation;
import race.level.Level;
import race.level.Obstacle;

public class Game {
    private LevelManager levels;

    private Player player1, player2;

    private AnimationQueue animations;

    private boolean finished;

    public Game(Sprite s1, Sprite s2) throws IOException, ParseException {
        levels = new LevelManager();

        player1 = new Player(0, 0, s1);
        player2 = new Player(0, 0, s2);

        animations = new AnimationQueue();

        finished = false;

        player1.respawn(levels.getLevel().getPlayerSpawn1());
        player2.respawn(levels.getLevel().getPlayerSpawn2());

        animations.add(new NewLevelAnimation(levels.getLevel(), levels.getLevelNumber()));
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void movePlayer1(Player.Direction dir) {
        if (animations.isEmpty())
            player1.move(dir);
    }

    public void movePlayer2(Player.Direction dir) {
        if (animations.isEmpty())
            player2.move(dir);
    }

    public void jumpPlayer1() {
        if (animations.isEmpty())
            player1.jump();
    }

    public void jumpPlayer2() {
        if (animations.isEmpty())
            player2.jump();
    }

    public void tick(long t) {
        if (animations.isEmpty()) {
            player1.update(t, levels.getLevel());
            player2.update(t, levels.getLevel());

            for (Obstacle o : levels.getLevel().getObstacles())
                o.update(player1, player2);

            checkForWinner();
        } else {
            animations.update();
        }
    }

    public void render(Renderer renderer) {
        renderer.setRenderingScale(levels.getLevel());

        if (animations.isEmpty()) {
            renderGame(renderer);
        } else {
            animations.render(renderer);
        }
    }

    public void renderGame(Renderer renderer) {
        levels.getLevel().getMap().render(renderer);

        // render the players and obstacles so that "near" objects are rendered
        // after "far" objects, creating a feeling of depth
        Stack<Player> stack = new Stack<Player>();
        if (player1.getY() > player2.getY()) {
            stack.add(player1);
            stack.add(player2);
        } else {
            stack.add(player2);
            stack.add(player1);
        }

        for (Obstacle o : levels.getLevel().getObstacles()) {
            while (!stack.isEmpty() && stack.peek().getY() < o.getY())
                stack.pop().render(renderer);
            o.render(renderer);
        }

        while (!stack.isEmpty())
            stack.pop().render(renderer);
    }

    public void checkForWinner() {
        GameLocation goal = getLevel().getGoal();

        if (goal.isAtLocation(player1)) {
            animations.add(new VictoryAnimation(player1, this));
            player1.score();
        } else if (goal.isAtLocation(player2)) {
            animations.add(new VictoryAnimation(player2, this));
            player2.score();
        }
    }

    public Level getLevel() {
        return levels.getLevel();
    }

    public boolean isFinished() {
        return finished;
    }

    public void advanceLevel() {
        if (levels.hasNext()) {
            try {
                levels.advance();
                animations.add(new NewLevelAnimation(levels.getLevel(), levels.getLevelNumber()));
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e.getMessage());
            }

            player1.respawn(getLevel().getPlayerSpawn1());
            player2.respawn(getLevel().getPlayerSpawn2());
        } else {
            finished = true;
        }
    }
}
