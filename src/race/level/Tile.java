package race.level;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A Tile contains information about a small portion of a tilesheet.
 */
public class Tile {
    private int offsetX, offsetY, width, height;

    public Tile(int offsetX, int offsetY, int width, int height) {
	this.offsetX = offsetX;
	this.offsetY = offsetY;
	this.width = width;
	this.height = height;
    }

    public int getOffsetX() { return offsetX; }
    public int getOffsetY() { return offsetY; }
    public int getWidth()   { return width;  }
    public int getHeight()  { return height; }

    public void render(GraphicsContext ctx, Image source, int x, int y, int w, int h) {
	ctx.drawImage(source, offsetX, offsetY, width, height, x, y, w, h);
    }
}
