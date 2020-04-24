package race;

import javafx.scene.canvas.GraphicsContext;

public class Sprite {
	public static final int EXPECTED_TICKS_PER_SEC = 60;
	public static final int ANIMATION_FPS = 3;
	public static final int FRAME_DURATION = EXPECTED_TICKS_PER_SEC / ANIMATION_FPS;

	private int width, height, offsetX, offsetY;
	private int[][][] animations;
	private long clock;

	/**
	 * Create a sprite. These are currently used only for
	 * @param width sprite width
	 * @param height sprite height
	 * @param offsetX the x-offset of the sprite on the source image. Keeping
	 * 	this separate from the animation parameters allows reuse.
	 * @param offsetY the y-offset of the sprite
	 * @param animations an array describing the different animations that this
	 * 	sprite can perform and the coordinates for each frame.
	 */
	public Sprite(int width,
			      int height,
			      int offsetX,
			      int offsetY,
			      int[][][] animations) {
		this.width = width;
		this.height = height;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.animations = animations;
		this.clock = 0;
	}

	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getOffsetX() { return offsetX * width; }
	public int getOffsetY() { return offsetY * height; }

	/**
	 * Increment the sprite's counter. Every few ticks, the animation frame
	 * index is advanced.
	 */
	public void tick() {
		this.clock++;
	}

	public void useFrame(int i) {
		this.clock = i * FRAME_DURATION;
	}

	/**
	 * Render the sprite
	 * @param ctx the drawing context
	 * @param x the x-position of the sprite
	 * @param y the y-position of the sprite
	 * @param animation the index of the current animation sequence
	 */
	public void render(GraphicsContext ctx, double x, double y, int animation)  {
		render(ctx, x, y, this.width, this.height, animation);
	}

	public void render(GraphicsContext ctx, double x, double y, int width, int height, int animation) {
		// determine the coordinates of the appropriate animation frame
		int frame = (int) (this.clock / FRAME_DURATION % this.animations[animation].length);
		int[] animationCoords = this.animations[animation][frame];

		ctx.drawImage(Assets.SPRITES, (this.offsetX + animationCoords[0])*this.width,
				(this.offsetY + animationCoords[1])*this.height,
				this.width,
				this.height,
				(int) x,
				(int) y,
				width,
				height);
	}

}
