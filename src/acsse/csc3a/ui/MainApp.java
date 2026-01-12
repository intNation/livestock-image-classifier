package acsse.csc3a.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class MainApp extends Application {
	 @Override
	  public void start(Stage primary) throws Exception {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
	    Scene scene = new Scene(loader.load(), 500, 500);
	    primary.setScene(scene);
	    primary.setTitle("Farm Manager");
	    primary.show();

	    // initialize the two-stage arrangement
	    MainController ctrl = loader.getController();
	    ctrl.initStages(primary);
	  }

	  public static void main(String[] args) { launch(args); }
}
