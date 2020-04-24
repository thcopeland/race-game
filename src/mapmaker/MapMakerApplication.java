package mapmaker;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import race.level.Level;
import race.level.Map;
import race.level.Obstacle;

/**
 * The startup screens for the MapMaker. This isn't really part of the map
 * maker application, but it gathers information (specifically, the loaded or
 * newly created Map).
 */
public class MapMakerApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
	stage.setTitle("Map Builder");
	stage.setMaximized(true);
	stage.setScene(buildStartScene(stage));
	stage.show();
    }

    /**
     * Create the startup scene. Includes a "logo," and a choice between creating
     * a new map or loading one from the filesystem.
     * @return the created scene
     */
    public Scene buildStartScene(Stage stage) {
	GridPane pane = new GridPane();
	pane.setHgap(10);
	pane.setVgap(10);
	pane.setAlignment(Pos.CENTER);

	Text header = new Text("Mapmaker 1.0");
	header.setFont(Font.font("", FontWeight.BOLD, 25));

	// this is used to show map loading errors
	Text errorText = new Text("");
	errorText.setFill(Color.RED);

	Button createMapButton = new Button("New Map"),
		loadMapButton   = new Button("Load from File");

	HBox buttonContainer = new HBox(10, createMapButton, loadMapButton);

	pane.addColumn(0, header, buttonContainer, errorText);

	// switch over to a screen asking for map dimensions
	createMapButton.setOnAction(e -> stage.setScene(buildNewMapScene(stage)));

	// open the OS file selector, load it if possible, and switch to the
	// mapmaker application
	loadMapButton.setOnAction(evt -> {
	    FileChooser chooser = new FileChooser();
	    File f = chooser.showOpenDialog(stage);

	    if (f != null) {
		try {
		    new MapMaker(Level.load(f.getAbsolutePath())).activate(stage);
		} catch (IOException | ParseException e) {
		    errorText.setText(String.format("Error: %s", e.getMessage()));
		}
	    } else {
		// no file chosen
		errorText.setText("Error: Select a file");
	    }
	});

	return new Scene(pane, stage.getWidth(), stage.getHeight());
    }

    /**
     * Create a Scene asking for map dimensions.
     */
    public Scene buildNewMapScene(Stage stage) {
	GridPane pane = new GridPane();
	pane.setHgap(10);
	pane.setVgap(10);
	pane.setAlignment(Pos.CENTER);

	Text header = new Text("Enter map dimensions");
	header.setFont(Font.font("Times New Roman", FontWeight.BOLD, 17));

	Text error = new Text();
	error.setFill(Color.RED);

	TextField mapWidth  = new TextField("43"),
		mapHeight = new TextField("23");

	Label widthLabel = new Label("Map Width:"),
		heightLabel = new Label("Map Height:");

	Button createEmptyMapButton = new Button("Create Map");

	createEmptyMapButton.setOnAction(e -> {
	    // check whehter the dimensions are numeric
	    if (!mapWidth.getText().matches("^\\d+$")) {
		error.setText("Invalid map width");
	    } else if (!mapHeight.getText().matches("^\\d+$")) {
		error.setText("Invalid map height");
	    } else {
		// create a new Level
		Map map = new Map(Integer.parseInt(mapWidth.getText()),
			Integer.parseInt(mapHeight.getText()));

		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

		// switch to the MapMaker application
		new MapMaker(new Level(map, obstacles)).activate(stage);
	    }
	});

	pane.add(header, 0, 0, 2, 1);
	pane.add(widthLabel, 0, 1);
	pane.add(mapWidth, 1, 1);
	pane.add(heightLabel, 0, 2);
	pane.add(mapHeight, 1, 2);
	pane.add(error, 0, 3, 2, 1);
	pane.add(createEmptyMapButton, 0, 4);

	return new Scene(pane, stage.getWidth(), stage.getHeight());
    }
}
