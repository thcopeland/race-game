package race;

import java.util.ArrayDeque;

import javafx.scene.canvas.GraphicsContext;

public class AnimationQueue {
    private ArrayDeque<Animation> animations;

    public AnimationQueue() {
        animations = new ArrayDeque<Animation>();
    }

    public void update() {
        current().update();

        if (current().isDone())
            animations.pop().onCompletion();
    }

    public void render(GraphicsContext ctx) {
        current().render(ctx);
    }

    public boolean isEmpty() {
        return animations.isEmpty();
    }

    public void add(Animation a) {
        animations.add(a);
    }

    public Animation current() {
        return animations.getLast();
    }
}
