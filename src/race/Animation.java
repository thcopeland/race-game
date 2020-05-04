package race;

import javafx.scene.canvas.GraphicsContext;

public abstract class Animation {
    /**
     * @return true when the animation is over
     */
    public abstract boolean isDone();

    /**
     * Called on completion of the animation
     */
    public void onCompletion() {
    };

    public abstract void render(GraphicsContext ctx);

    public abstract void update();
}
