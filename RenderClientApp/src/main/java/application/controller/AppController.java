package application.controller;

import java.util.logging.Logger;

import application.controller.viewSetup.SetupInitialView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class AppController {
	private static Logger logger = Logger.getLogger(AppController.class.getName());
	
	Application application;
	Stage primaryStage;
	Parent root;
	
	public Parent getRoot() {
		return root;
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}	

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void launchStartupSequence() {
		if (null==application || null==primaryStage) {
			logger.info("Must first inject Application and PrimaryStage objects before starting up application.");			
		}
		
		if (null==root) {
			logger.info("Must first inject a root User Interface Component.");
		}
		
		SetupInitialView setupInitialView = new SetupInitialView(application,primaryStage,root);
		setupInitialView.run();
	}	
}
