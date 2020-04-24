package race;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import race.level.Level;
import race.level.Obstacle;

public class NewLevelAnimation extends Animation {
    private int levelNumber;
    private Level level;
    private long clock;

    public NewLevelAnimation(Level level, int levelNumber) {
	this.level = level;
	this.levelNumber = levelNumber;
	clock = System.currentTimeMillis();
    }

    @Override
    public boolean isDone() {
	return System.currentTimeMillis() - clock > 3000;
    }

    @Override
    public void render(GraphicsContext ctx) {
	ctx.setFill(Color.BLACK);

	long time = System.currentTimeMillis() - clock;

	if (time < 400) {
	    ctx.fillRect(0, 0, level.getWidth(), level.getHeight());
	    ctx.setFill(Color.WHITE);

	    ctx.save();
	    ctx.setTextAlign(TextAlignment.CENTER);
	    ctx.setFont(Font.font(time/2));
	    ctx.fillText("Level "+levelNumber, level.getWidth()/2, level.getHeight()/2);
	    ctx.restore();
	} else if(time > 1500) {
	    level.getMap().render(ctx);
	    for (Obstacle o : level.getObstacles()) {
		o.render(ctx);
	    }

	    ctx.setFill(Color.BLACK);
	    ctx.setGlobalAlpha(1 - (time - 1500)/1500.0);
	    ctx.fillRect(0, 0, level.getWidth(), level.getHeight());
	    ctx.setGlobalAlpha(1.0);
	}

    }

    @Override
    public void update() {
	clock++;
    }
}
