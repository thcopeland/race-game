package race.level;

import javafx.scene.image.Image;
import race.Renderer;

public class Tile {
    private int offsetX, offsetY, width, height;

    public Tile(int offsetX, int offsetY, int width, int height) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void render(Renderer renderer, Image src, int x, int y) {
        renderer.renderImage(src, offsetX, offsetY, width, height, x, y);
    }
}
