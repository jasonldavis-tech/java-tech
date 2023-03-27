package application;
	
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import application.controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextFlowExample extends Application {
	private static Logger logger = Logger.getLogger(TextFlowExample.class.getName());
	
	private LinkedBlockingQueue<String> receivedMessages =  new LinkedBlockingQueue<>();
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("RenderClientApp");
		loadFXMLandAttachController(primaryStage);
	}
	
	public void loadFXMLandAttachController(Stage primaryStage) {
		try {
			URL fxmlUrl = getClass().getResource("/main/resources/textFlowExample.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
			VBox vbox = (VBox) fxmlLoader.load(fxmlUrl);
			
			AppController appController = (AppController) fxmlLoader.getController();
			if (appController == null) {
				logger.info("Warning controller from FXML not loading correctly");
				appController = new AppController();
			}			

			vbox.setMinWidth(400);
			vbox.setMinHeight(400);	
			appController.setApplication(this);
			appController.setPrimaryStage(primaryStage);
			appController.setRoot(vbox);
			appController.launchStartupSequence();			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}