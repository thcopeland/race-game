package mapmaker;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import race.level.*;

/**
 * A Node that renders as a grid of images. When one of the images is clicked,
 * it is marked as selected.
 */
public class Palette extends GridPane {
	private Node selectedNode;
	private Tiled selected;

	/**
	 * Create a new Palette node.
	 * @param source an image to which the Tiled cells point
	 * @param cells an array from which to build the cells
	 * @param rows the number of rows in the grid. If the number of cells in the
	 * 	array exceeds rows*columns, they're wrapped back to row zero.
	 * @param columns the number of columns in the grid
	 */
	public Palette(Image source, Tiled[] cells, int rows, int columns) {
		super();

		constructPalette(source, cells, rows, columns);
	}

	/**
	 * @return the selected Tiled object
	 */
	public Tiled getSelected() { return selected; }

	/**
	 * Populate the underlying GridPane with images
	 */
	private void constructPalette(Image source, Tiled[] cells, int rows, int columns) {
		for (int x = 0; x < cells.length; x++) {
			int row = (x % (rows*columns)) / columns,
				col = x % columns + (x / rows / columns) * columns;

			this.add(constructChoice(source, cells[x]), col, row);
		}
	}

	/**
	 * Create a new Node to hold an image choice and adds a selection handler
	 */
	private Node constructChoice(Image source, Tiled cell) {
		// take the correct portion of the source image
		ImageView img = new ImageView(source);
		img.setViewport(new Rectangle2D(cell.getTile().getOffsetX(),
										cell.getTile().getOffsetY(),
										cell.getTile().getWidth(),
										cell.getTile().getWidth()));

		// a separate container is necessary for the black border on selection
		BorderPane container = new BorderPane(img);
		container.getStyleClass().add("palette-cell");

		// add handlers
		container.setOnMousePressed(e -> {
			// remove border from previously selected node
			if (selectedNode != null) {
				selectedNode.getStyleClass().remove("palette-cell-selected");
			}

			// add the border to this one
			selectedNode = container;
			selectedNode.getStyleClass().add("palette-cell-selected");

			// update the selection
			selected = cell;
		});

		return container;
	}
}
