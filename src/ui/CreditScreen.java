package ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class CreditScreen {
    private Stage stage;

    public CreditScreen(Stage stage) {
        this.stage = stage;
    }

    public void activate() {
        stage.getScene().setRoot(buildScene());
    }

    public Parent buildScene() {
        VBox pane = new VBox();
        pane.setSpacing(50);
        pane.setAlignment(Pos.CENTER);
        pane.getStyleClass().add("ui-parchment-background");

        TextFlow content;
        try {
            content = buildRichTextNode(new FileReader("src/assets/credits.txt"));
        } catch (FileNotFoundException e) {
            content = new TextFlow(new Text("unable to load credits: " + e.getMessage()));
        }

        UIButton exitButton = new UIButton("Back", "green");
        exitButton.setOnMouseClicked(e -> new MainScreen(stage).activate());

        ScrollPane container = new ScrollPane(content);
        container.getStyleClass().add("text-scroll-container");
        content.setMaxWidth(stage.getWidth() / 2);
        container.setMaxWidth(stage.getWidth() / 1.9);
        container.setMaxHeight(stage.getHeight() / 1.5);

        pane.getChildren().addAll(container, exitButton);

        return pane;
    }

    private TextFlow buildRichTextNode(FileReader source) {
        TextFlow t = new TextFlow();
        Scanner s = new Scanner(source);

        while (s.hasNextLine()) {
            t.getChildren().add(getNextSection(s));
        }

        return t;
    }

    private Text getNextSection(Scanner s) {
        String line = s.nextLine();
        Text node = new Text(line + " ");
        String klass = "rt-text";

        if (line.isBlank()) {
            node = new Text("\n");
        } else if (line.matches("^#+.*")) {
            int i = 1;
            while (i < 3 && line.charAt(i) == '#')
                i++;
            node = new Text(line.substring(i) + "\n");
            klass = "rt-header-" + i;
        } else if (line.matches("\\s*-.*")) {
            node = new Text("•" + line.substring(line.indexOf('-') + 1) + "\n");
            klass = "rt-bullet";
        }

        node.getStyleClass().add(klass);
        return node;
    }
}