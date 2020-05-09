package race;

public abstract class Animation {
    public abstract boolean isDone();

    public void onCompletion() {};

    public abstract void render(Renderer renderer);

    public abstract void update(long t);
}
