package application;
	
import java.net.URL;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import application.controller.AppController;
import color.AsciiColorUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import network.NetworkUtil;

public class RenderServerApp extends Application {
	private static Logger logger = Logger.getLogger(RenderServerApp.class.getName());
	
	@Override
	public void start(Stage primaryStage) {
		loadFXMLandAttachController(primaryStage);
		primaryStage.setTitle("Render Server App");
		connectUDP();
	}
	
	public void loadFXMLandAttachController(Stage primaryStage) {
		try {
			URL fxmlUrl = getClass().getResource("/main/resources/power.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
			VBox vbox = (VBox) FXMLLoader.load(fxmlUrl);
			
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
	
	private ConcurrentLinkedQueue<String> messages = new ConcurrentLinkedQueue<>();
	
	private void connectUDP() {
		logger.info(AsciiColorUtil.BLUE+"Attempting to Connecti to UDP"+AsciiColorUtil.DEFAULT);
		NetworkUtil.connectClientUDP("192.168.1.91",7777);
		// logger.info(AsciiColorUtil.BLUE+"UDP Connection Obtained Successfully"+AsciiColorUtil.DEFAULT);
	}
	
	public static void main(String[] args) {
		launch(args);	
	}	
}
