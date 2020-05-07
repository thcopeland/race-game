package race;

public abstract class Animation {
    /**
     * @return true when the animation is over
     */
    public abstract boolean isDone();

    /**
     * Called on completion of the animation
     */
    public void onCompletion() {};

    public abstract void render(Renderer renderer);

    public abstract void update();
}
