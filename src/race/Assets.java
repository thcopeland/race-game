package race;

import javafx.scene.image.Image;

public final class Assets {
	/**
	 * Map tile image source
	 */
	public static final Image TILESHEET = new Image("assets/images/tiles.png");

	/**
	 * Obstacle image source
	 */
	public static final Image OBSTACLES = new Image("assets/images/obstacles.png");

	/**
	 * Sprite (probably just players) image source
	 */
	public static final Image SPRITES = new Image("assets/images/sprites.png");

	public static final Image EFFECTS = new Image("assets/images/effects.png");

	/**
	 * The directory in which to search for level information. Level files should
	 * be of the form 'level_#'; see {@link Game#loadLevel}
	 */
	public static final String LEVEL_DIR = "src/assets/levels";
}
