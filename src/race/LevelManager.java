package race;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import race.level.Level;

public class LevelManager {
    private int number;
    private Level level;

    public LevelManager() throws IOException, ParseException {
        number = 1;
        level = loadLevel(number);
    }

    /**
     * @return true if there is at least one more level
     */
    public boolean hasNext() {
        return new File(buildFilename(number + 1)).exists();
    }

    /**
     * Load the next level
     */
    public void advance() throws IOException, ParseException {
        level = loadLevel(++number);
    }

    /**
     * @return the current level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return the current level number
     */
    public int getLevelNumber() {
        return number;
    }

    /**
     * Load a level from a file. Searches in <code>Assets.LEVEL_DIR</code>.
     * 
     * @param level the level number
     * @return the Level object loaded from the filesystem
     * @throws IOException    generally, file does not exist
     * @throws ParseException improperly structured file
     */
    private Level loadLevel(int level) throws IOException, ParseException {
        return Level.load(buildFilename(level));
    }

    /**
     * Construct level path
     */
    private String buildFilename(int level) {
        return String.format("%s/level_%d", Assets.LEVEL_DIR, level);
    }
}
