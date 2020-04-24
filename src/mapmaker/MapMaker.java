package mapmaker;

import java.io.IOException;
import java.text.ParseException;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import race.Assets;
import race.Game;
import race.level.Barrel;
import race.level.BigMaple;
import race.level.BigPine;
import race.level.Cup;
import race.level.GameLocation;
import race.level.Level;
import race.level.LittleMaple;
import race.level.LittlePine;
import race.level.MapTile;
import race.level.Mine;
import race.level.Obstacle;

public class MapMaker {
    private enum MapMakerMode {
	TILE, ADD_OBSTACLE, REMOVE_OBSTACLE, SET_SPAWN1, SET_SPAWN2, SET_GOAL, DISABLE
    }

    private Level level;

    private GraphicsContext context;

    /**
     * Important UI elements
     */
    private Canvas canvas;
    private ScrollPane canvasContainer;
    private Palette tilePalette;
    private ChoiceBox<String> obstacleSelector;

    private MapMakerMode mode = MapMakerMode.TILE;
    private long lastObstacleTimestamp = 0;

    public MapMaker(Level level) {
	this.level = level;
    }

    public void activate(Stage stage) {
	stage.setScene(buildScene(stage.getWidth(), stage.getHeight()));
    }

    public Scene buildScene(double width, double height) {
	// create a canvas, drawing context, and renderer
	canvas = new Canvas(level.getMap().getWidth() * Game.GAME_SCALE,
		level.getMap().getHeight() * Game.GAME_SCALE);
	canvas.setCursor(Cursor.CROSSHAIR);

	context = canvas.getGraphicsContext2D();
	render();

	// allow scrolling across the canvas
	canvasContainer = new ScrollPane(canvas);
	canvasContainer.setVbarPolicy(ScrollBarPolicy.NEVER);
	canvasContainer.setHbarPolicy(ScrollBarPolicy.NEVER);

	TabPane toolbarPane = (TabPane) buildToolbar();

	// this pane contains the entire UI
	GridPane pane = new GridPane();
	pane.add(toolbarPane, 0, 0);
	pane.add(canvasContainer, 1, 0);

	// add event handlers
	canvas.setOnMouseClicked(e -> handleCanvasClick(e));
	canvas.setOnMouseDragged(canvas.getOnMouseClicked());

	// draw a little rectangle on mouse move
	canvas.setOnMouseMoved(e -> {
	    // convert race coordinates into map coordinates
	    int x = (int) (e.getX() - canvasContainer.getHvalue()) / Game.GAME_SCALE,
		    y = (int) (e.getY() - canvasContainer.getVvalue()) / Game.GAME_SCALE;

	    // draw the map (to clear previously drawn rectangles)
	    render();

	    // draw the rectangle
	    context.setStroke(Paint.valueOf("#00000088"));
	    context.strokeRect(x * Game.GAME_SCALE, y * Game.GAME_SCALE, Game.GAME_SCALE, Game.GAME_SCALE);
	});


	// create and return the constructed scene
	Scene scene = new Scene(pane, width, height);
	scene.getStylesheets().add("assets/stylesheets/mapmaker.css");
	return scene;
    }

    /**
     * Construct a toolbar, consisting of a few tabs.
     */
    public Node buildToolbar() {
	TabPane toolbar = new TabPane();
	toolbar.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

	tilePalette = new Palette(Assets.TILESHEET, MapTile.values(), 18, 3);

	toolbar.getTabs().addAll(new Tab("Tiles", tilePalette),
		new Tab("Export", buildExportView()),
		new Tab("Obstacles", buildObstaclesView()));

	toolbar.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> {
	    if(val.getText().equals("Tiles")) mode = MapMakerMode.TILE;
	    else if(val.getText().equals("Obstacles")) mode = MapMakerMode.ADD_OBSTACLE;
	    else mode = MapMakerMode.DISABLE;
	});

	// set the width so that the entire palette will be visible
	toolbar.setMinWidth((Game.GAME_SCALE + 2)*tilePalette.getColumnCount());
	return toolbar;
    }

    /**
     * Construct the import/export UI
     */
    private Node buildExportView() {
	// this pane contains the entire import/export UI
	GridPane exporter = new GridPane();
	exporter.setPadding(new Insets(20));
	exporter.setHgap(10);
	exporter.setVgap(10);

	// create the UI elements
	Text exportHeader = new Text("Export"),
		importHeader = new Text("Import");
	exportHeader.setFont(Font.font("", FontWeight.BOLD, 20));
	importHeader.setFont(Font.font("", FontWeight.BOLD, 20));

	Label exportPathLabel = new Label("Export to:"),
		importPathLabel = new Label("Import from:");
	TextField exportPath  = new TextField(),
		importPath  = new TextField();
	Button exportButton   = new Button("Export"),
		importButton   = new Button("Import");
	Text exportStatus     = new Text(""),
		importStatus     = new Text("");

	exportPath.setPrefWidth(300);
	exportPath.setPromptText("assets/maps/");
	importPath.setPromptText("assets/maps/");
	importPath.setPrefWidth(300);

	// attempt an export on click, and show a little message that describes
	// failure/success
	exportButton.setOnAction(evt -> {
	    if (level.isExportable()) {
		try {
		    level.export(exportPath.getText());

		    exportStatus.setFill(Color.BLACK);
		    exportStatus.setText(String.format("Success! Exported to %s", exportPath.getText()));
		} catch (IOException e) {
		    exportStatus.setFill(Color.RED);
		    exportStatus.setText(String.format("Error: %s", e.getMessage()));
		}
	    } else {
		exportStatus.setFill(Color.RED);
		exportStatus.setText("Error: This level is not complete");
	    }
	});

	// attempt an import on click, and show a failure or success message
	importButton.setOnAction(evt -> {
	    try {
		level = Level.load(importPath.getText());
		render();

		importStatus.setFill(Color.BLACK);
		importStatus.setText(String.format("Success! Imported map %s", importPath.getText()));
	    } catch (IOException | ParseException e) {
		importStatus.setFill(Color.RED);
		importStatus.setText(String.format("Error: %s", e.getMessage()));
	    }
	});

	// add everything to the pane
	exporter.add(exportHeader, 0, 0, 2, 1);
	exporter.add(exportPathLabel, 0, 1);
	exporter.add(exportPath, 0, 2);
	exporter.add(exportButton, 0, 3);
	exporter.add(exportStatus, 0, 4, 3, 1);

	exporter.add(importHeader, 0, 6, 2, 1);
	exporter.add(importPathLabel, 0, 7);
	exporter.add(importPath, 0, 8);
	exporter.add(importButton, 0, 9);
	exporter.add(importStatus, 0, 10, 3, 1);

	return exporter;
    }

    /**
     * Create the obstacles pane, where obstacles can be added to the map
     */
    private Node buildObstaclesView() {
	// this pane holds the obstacle UI
	GridPane pane = new GridPane();
	pane.setPadding(new Insets(20));
	pane.setHgap(10);
	pane.setVgap(10);

	// build ui elements
	Text header = new Text("Create Obstacles");
	header.setFont(Font.font("", FontWeight.BOLD, 20));

	obstacleSelector = new ChoiceBox<String>();
	obstacleSelector.getItems().addAll("Barrel", "Cup", "Big Maple", "Little Maple", "Big Pine", "Little Pine", "Mine");
	obstacleSelector.setPrefWidth(300);
	obstacleSelector.getSelectionModel().select(1);

	CheckBox removeObstacles = new CheckBox("Remove Obstacles");
	removeObstacles.selectedProperty().addListener((obs, old, val) -> {
	    mode = (val ? MapMakerMode.REMOVE_OBSTACLE : MapMakerMode.ADD_OBSTACLE);
	});

	Text header2 = new Text("Set Spawn Points");
	header2.setFont(header.getFont());

	Button setSpawn1 = new Button("Spawn Point 1"),
		setSpawn2 = new Button("Spawn Point 2");
	setSpawn1.setOnMouseClicked(e -> mode = MapMakerMode.SET_SPAWN1);
	setSpawn2.setOnMouseClicked(e -> mode = MapMakerMode.SET_SPAWN2);

	Text header3 = new Text("Place Goal");
	header3.setFont(header.getFont());

	Button setGoal = new Button("Place Goal");
	setGoal.setOnMouseClicked(e -> mode = MapMakerMode.SET_GOAL);

	pane.add(header, 0, 0, 2, 1);
	pane.add(obstacleSelector, 0, 1, 2, 1);
	pane.add(removeObstacles, 0, 2);
	pane.add(header2, 0, 4, 2, 1);
	pane.add(setSpawn1, 0, 5);
	pane.add(setSpawn2, 1, 5);
	pane.add(header3, 0, 7, 2, 1);
	pane.add(setGoal, 0, 8);

	return pane;
    }

    private void render() {
	level.getMap().render(context);

	context.setFill(Color.DARKGOLDENROD);
	if(level.getPlayerSpawn1() != null) context.fillOval(level.getPlayerSpawn1().getX()-16, level.getPlayerSpawn1().getY()-16, 32, 32);
	if(level.getPlayerSpawn2() != null) context.fillOval(level.getPlayerSpawn2().getX()-16, level.getPlayerSpawn2().getY()-16, 32, 32);

	context.setFill(Color.GOLD);
	if(level.getGoal() != null) context.fillOval(level.getGoal().getX()-16, level.getGoal().getY()-16, 32, 32);

	for (Obstacle o : level.getObstacles())
	    o.render(context);
    }

    /**
     * this is called when the canvas is clicked
     */
    private void handleCanvasClick(MouseEvent evt) {
	// the coordinates of the click relative the the canvas, accounting
	// for scrolling
	int x = (int) (evt.getX() - canvasContainer.getHvalue()),
		y = (int) (evt.getY() - canvasContainer.getVvalue());

	switch(mode) {
	case TILE:
	    handleClickTile(x, y);
	    break;
	case ADD_OBSTACLE:
	    handleClickObstacle(x, y);
	    break;
	case REMOVE_OBSTACLE:
	    handleClickRemoveObstacle(x, y);
	    break;
	case SET_SPAWN1: case SET_SPAWN2:
	    handleClickSpawn(x, y);
	    break;
	case SET_GOAL:
	    handleClickGoal(x, y);
	    break;
	default:
	    return; // do nothing
	}

	// render the map
	render();
    }

    private void handleClickTile(int x, int y) {
	if (tilePalette.getSelected() != null) {
	    level.getMap().setTile(y/Game.GAME_SCALE, x/Game.GAME_SCALE,
		    (MapTile) tilePalette.getSelected());
	}
    }

    private void handleClickObstacle(int x, int y) {
	if (System.currentTimeMillis() - lastObstacleTimestamp > 250) {
	    switch(obstacleSelector.selectionModelProperty().getValue().getSelectedItem()) {
	    case "Cup":
		level.getObstacles().add(new Cup(x, y));
		break;
	    case "Big Maple":
		level.getObstacles().add(new BigMaple(x, y));
		break;
	    case "Little Maple":
		level.getObstacles().add(new LittleMaple(x, y));
		break;
	    case "Big Pine":
		level.getObstacles().add(new BigPine(x, y));
		break;
	    case "Little Pine":
		level.getObstacles().add(new LittlePine(x, y));
		break;
	    case "Barrel":
		level.getObstacles().add(new Barrel(x, y));
		break;
	    case "Mine":
		level.getObstacles().add(new Mine(x, y));
		break;
	    }

	    lastObstacleTimestamp = System.currentTimeMillis();
	}
    }

    private void handleClickRemoveObstacle(int x, int y) {
	level.getObstacles().removeIf(o -> Math.hypot(x-o.getX(), y-o.getY()) < 30);
    }

    private void handleClickSpawn(int x, int y) {
	if (mode == MapMakerMode.SET_SPAWN1) {
	    level.setPlayerSpawn1(new GameLocation(x, y));
	} else if (mode == MapMakerMode.SET_SPAWN2) {
	    level.setPlayerSpawn2(new GameLocation(x, y));
	}

	mode = MapMakerMode.ADD_OBSTACLE;
    }

    private void handleClickGoal(int x, int y) {
	level.setGoal(new GameLocation(x, y));
	mode = MapMakerMode.ADD_OBSTACLE;
    }
}
