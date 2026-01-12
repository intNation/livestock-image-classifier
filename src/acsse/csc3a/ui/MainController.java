package acsse.csc3a.ui;

import java.io.File;

import acsse.csc3a.processing.SimilarityEngine;
import acsse.csc3a.util.FeatureVector;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private StackPane  mainPane;
	@FXML
	private javafx.scene.control.ScrollPane galleryScroll;
	@FXML
	private HBox galleryBox;
	@FXML
	private Button scrollLeft, scrollRight;

	private SimilarityEngine engine = new SimilarityEngine();
	private GraphVisualizer viz = new GraphVisualizer();

	// called by MainApp at startup:
	public void initStages(Stage primary) {
		// 1) set up graph Stage
		Stage graphStage = new Stage();
		graphStage.setTitle("Similarity Graph");
		graphStage.setScene(new Scene((Parent) viz.getView()));
		graphStage.show();

		// 2) hook up scrolling buttons
		scrollLeft.setOnAction(e -> galleryScroll.setHvalue(Math.max(0, galleryScroll.getHvalue() - 0.2)));
		scrollRight.setOnAction(e -> galleryScroll.setHvalue(Math.min(1, galleryScroll.getHvalue() + 0.2)));
	}

	@FXML
	private void onLoadFolder() {
		DirectoryChooser dc = new DirectoryChooser();
		dc.setTitle("Select Image Folder");
		File dir = dc.showDialog(mainPane.getScene().getWindow());
		if (dir == null)
			return;
		engine.loadFolder(dir.getAbsolutePath());
		viz.sync(engine.getGraph());
		refreshGallery(null); // clear gallery
	}

	@FXML
	private void onUploadImage() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Select Animal Image");
		File f = fc.showOpenDialog(mainPane.getScene().getWindow());
		if (f == null)
			return;

		// 1) add new image to graph
		FeatureVector q = engine.addNewImage(f.getAbsolutePath());
		viz.sync(engine.getGraph());

		// 2) get top-5 similar
		FeatureVector[] top = engine.topK(q, 5);
		refreshGallery(top);
	}

	@FXML
	private void onShowGraph() {
		// Already showing in its own Stage; just re-sync
		viz.sync(engine.getGraph());
	}

	private void refreshGallery(FeatureVector[] items) {
		galleryBox.getChildren().clear();
		if (items == null)
			return;
		for (FeatureVector fv : items) {
			ImageView thumb = new ImageView(new Image("file:" + fv.getPath(), 150, 150, true, true));
			thumb.setOnMouseClicked(evt -> {
				Alert confirm = new Alert(AlertType.CONFIRMATION, "Sell this animal?", ButtonType.OK,
						ButtonType.CANCEL);
				confirm.setHeaderText(null);
				confirm.showAndWait().ifPresent(bt -> {
					if (bt == ButtonType.OK) {
						engine.sell(fv);
						viz.sync(engine.getGraph());
						galleryBox.getChildren().remove(thumb);
					}
				});
			});
			galleryBox.getChildren().add(thumb);
		}
		// reset scroll to leftmost
		galleryScroll.setHvalue(0);
	}
}
