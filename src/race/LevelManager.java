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

    public boolean hasNext() {
        return new File(buildFilename(number + 1)).exists();
    }

    public void advance() throws IOException, ParseException {
        level = loadLevel(++number);
    }

    public Level getLevel() {
        return level;
    }

    public int getLevelNumber() {
        return number;
    }

    private Level loadLevel(int level) throws IOException, ParseException {
        return Level.load(buildFilename(level));
    }

    private String buildFilename(int level) {
        return String.format("%s/level_%d", Assets.LEVEL_DIR, level);
    }
}
