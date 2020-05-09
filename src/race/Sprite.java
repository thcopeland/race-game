package race;

import javafx.scene.canvas.GraphicsContext;

public class Sprite {
    private int width, height, offsetX, offsetY;
    private int[][][] animations;
    private long clock;

    /**
     * @param width      sprite width
     * @param height     sprite height
     * @param offsetX    the x-offset of the sprite on the source image. Keeping
     *                   this separate from the animation parameters allows reuse.
     * @param offsetY    the y-offset of the sprite
     * @param animations an array describing the different animations that this
     *                   sprite can perform and the coordinates for each frame.
     */
    public Sprite(int width, int height, int offsetX, int offsetY, int[][][] animations) {
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.animations = animations;
        clock = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOffsetX() {
        return offsetX * width;
    }

    public int getOffsetY() {
        return offsetY * height;
    }

    public void tick(long t) {
        clock += t;
    }

    public void render(Renderer renderer, double x, double y, int animation) {
        int frame = (int) (clock / 75000 % animations[animation].length);
        int[] animationCoords = animations[animation][frame];

        renderer.renderImage(Assets.SPRITES, (offsetX + animationCoords[0]) * width,
                                             (offsetY + animationCoords[1]) * height,
                                             width, height, x, y);
    }

    public void renderDirectly(GraphicsContext ctx, double x, double y, int width, int height, int animation, int frame) {
        int[] animationCoords = animations[animation][frame];

        ctx.drawImage(Assets.SPRITES, (offsetX + animationCoords[0]) * this.width,
                                      (offsetY + animationCoords[1]) * this.height,
                                      this.width, this.height, x, y, width, height);
    }

}
