package race;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import race.level.Level;

public class Renderer {
    private GraphicsContext ctx;
    private int scaleX, scaleY, viewportWidth, viewportHeight;

    public Renderer(GraphicsContext context) {
        ctx = context;
        scaleX = scaleY = 32;

        // this seems to be fairly slow, so we store it
        viewportWidth = (int) context.getCanvas().getWidth();
        viewportHeight = (int) context.getCanvas().getHeight();
    }

    public GraphicsContext getContext() {
        return ctx;
    }

    public void renderImage(Image img, double sx, double sy, double w, double h, double dx, double dy) {
        ctx.drawImage(img, sx, sy, w, h, (int) (getScaleX() * dx), (int) (getScaleY() * dy),
                      Math.round(w * getScaleX() / 32), Math.round(h * getScaleY() / 32));
    }

    public void setRenderingScale(Level level) {
        scaleX = (int) Math.round(ctx.getCanvas().getWidth() / level.getMap().getWidth());
        scaleY = (int) Math.round(ctx.getCanvas().getHeight() / level.getMap().getHeight());
    }

    public int getScaleX() {
        return scaleX;
    }

    public int getScaleY() {
        return scaleY;
    }

    public int getViewportWidth() {
        return viewportWidth;
    }

    public int getViewportHeight() {
        return viewportHeight;
    }
}
