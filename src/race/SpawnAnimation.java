package race;

import race.level.GameLocation;

public class SpawnAnimation extends Animation {
    private GameLocation spawnPoint;
    private Player player;
    private double speed;

    public SpawnAnimation(GameLocation spawnPoint, Player player) {
        this.spawnPoint = spawnPoint;
        this.player = player;
        speed = 0;

        player.setY(0);
        player.setZ(0);
        player.setX(spawnPoint.getX());
    }

    @Override
    public boolean isDone() {
        return player.getY() >= spawnPoint.getY();
    }

    @Override
    public void render(Renderer renderer) {
        player.getSprite().render(renderer, player.getX() - 0.75, player.getY() - 1.88, 2);
    }

    @Override
    public void update(long t) {
        player.setY(player.getY() + (speed += 0.00000008*t));
    }
}
