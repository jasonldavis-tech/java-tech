package application.controller.viewSetup;

import application.controller.LoadCssCommand;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SetupScene implements Runnable {
	private Stage primaryStage;
	private VBox vbox;
	private Scene scene;
	private Application application;

	public SetupScene(final Application application, final Stage primaryStage,
			final Parent root) {
		this.application = application;
		this.primaryStage = primaryStage;
		this.vbox = (VBox) root;
	}
	
	@Override
	public void run() {
		scene = new Scene(vbox,700,400);
		
		LoadCssCommand loadCssComand = new LoadCssCommand(application,scene);
		loadCssComand.run();
		
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	public Scene getGeneratedScene() {
		return scene;
	}
	
	public VBox getGeneratedVBox() {
		return vbox;
	}

}
