package application.controller.viewSetup;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class SetupInitialView implements Runnable {
	private Application application;
	private Stage primaryStage;	
	private Parent root;
	
	public SetupInitialView(final Application application, final Stage primaryStage, final Parent root) {
		this.application = application;
		this.primaryStage = primaryStage;
		this.root = root;
	}
	
	@Override
	public void run() {
		SetupScene setupScene = new SetupScene(application,primaryStage,root);
		setupScene.run();

		/* Scene scene = setupScene.getGeneratedScene();
		VBox vbox = setupScene.getGeneratedVBox();
		
		SetupStreamViewComponent setupStreamViewComponent = new SetupStreamViewComponent(vbox);
		setupStreamViewComponent.run();
		RenderEngineModel model = setupStreamViewComponent.getGeneratedModel();
		
		SetupKeyboardListener setupKeyboardListener = new SetupKeyboardListener(scene, model);
		setupKeyboardListener.run(); 

		SetupOnExitCommand setupOnExitCommand = new SetupOnExitCommand(primaryStage, model);
		setupOnExitCommand.run(); */
	}

}
