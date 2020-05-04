package race.level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import race.Game;

public class Level {
    private Map map;
    private ArrayList<Obstacle> obstacles;
    private GameLocation playerSpawn1, playerSpawn2, goal;

    public Level(Map map, ArrayList<Obstacle> obstacles) {
        this(map, obstacles, null, null, null);
    }

    public Level(Map map, ArrayList<Obstacle> obstacles, GameLocation spawn1, GameLocation spawn2, GameLocation goal) {
        this.map = map;
        this.obstacles = obstacles;
        playerSpawn1 = spawn1;
        playerSpawn2 = spawn2;
        this.goal = goal;

        reorderObstacles();
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public GameLocation getPlayerSpawn1() {
        return playerSpawn1;
    }

    public GameLocation getPlayerSpawn2() {
        return playerSpawn2;
    }

    public GameLocation getGoal() {
        return goal;
    }

    public void setPlayerSpawn1(GameLocation sp) {
        playerSpawn1 = sp;
    }

    public void setPlayerSpawn2(GameLocation sp) {
        playerSpawn2 = sp;
    }

    public void setGoal(GameLocation goal) {
        this.goal = goal;
    }

    public double getWidth() {
        return map.getWidth() * Game.GAME_SCALE;
    }

    public double getHeight() {
        return map.getHeight() * Game.GAME_SCALE;
    }

    /**
     * @return the terrain type at the given coordinates
     */
    public Terrain getTerrainAt(double x, double y) {
        int row = (int) (y / Game.GAME_SCALE), col = (int) (x / Game.GAME_SCALE);

        return map.getTile(row, col).getTerrain((int) x % Game.GAME_SCALE, (int) y % Game.GAME_SCALE);
    }

    /**
     * Put the obstacles in render order (y-coordinate ascending)
     */
    public void reorderObstacles() {
        obstacles.sort((a, b) -> a.getY() - b.getY());
    }

    /**
     * @return true if the level has enough data to be exported
     */
    public boolean isExportable() {
        return map != null && obstacles != null && playerSpawn1 != null && playerSpawn2 != null && goal != null;
    }

    /**
     * Export the level data to a file
     * 
     * @throws IOException
     */
    public void export(String path) throws IOException {
        FileWriter f = new FileWriter(path);

        try {
            // write a header and the map dimensions
            f.write(String.format("MAP\n%d %d\n", map.getWidth(), map.getHeight()));

            // write the map data
            for (int row = 0; row < map.getHeight(); row++) {
                for (int col = 0; col < map.getWidth(); col++) {
                    f.write(MapTile.serialize(map.getTile(row, col)));
                    f.write(' ');
                }
            }

            // write obstacle data
            f.write(String.format("\n%d\n", obstacles.size()));

            reorderObstacles();
            for (Obstacle ob : obstacles) {
                f.write(ob.serialize());
                f.write('\n');
            }

            // write spawn point data
            f.write(getPlayerSpawn1().serialize());
            f.write('\n');
            f.write(getPlayerSpawn2().serialize());
            f.write('\n');

            // write goal location
            f.write(getGoal().serialize());
        } finally {
            f.close();
        }
    }

    /**
     * Import level data from a file
     * 
     * @throws IOException
     * @throws ParseException
     */
    public static Level load(String path) throws IOException, ParseException {
        Scanner s = new Scanner(new File(path));

        try {
            // check the file signature
            String identifier = s.nextLine();
            if (!identifier.equals("MAP"))
                throw new ParseException("Not a recognized map", 0);

            // read map dimensions
            int width = s.nextInt(), height = s.nextInt();

            // create a map instance
            Map map = new Map(width, height);

            // read map data
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    map.setTile(y, x, MapTile.deserialize(s.next()));
                }
            }

            // finish current line
            s.nextLine();

            // read obstacle information
            int obstacleCount = Integer.parseInt(s.nextLine());
            ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>(obstacleCount);

            for (int i = 0; i < obstacleCount; i++) {
                obstacles.add(Obstacle.deserialize(s.nextLine()));
            }

            // read spawn and goal locations
            GameLocation sp1 = GameLocation.deserialize(s.nextLine()), sp2 = GameLocation.deserialize(s.nextLine()),
                    goal = GameLocation.deserialize(s.nextLine());

            return new Level(map, obstacles, sp1, sp2, goal);
        } catch (NoSuchElementException e) {
            throw new ParseException("Invalid map structure", 0);
        } finally {
            s.close();
        }
    }
}
