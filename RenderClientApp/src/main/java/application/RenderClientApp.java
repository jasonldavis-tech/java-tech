package application;
	
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import application.controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import multithreading.ThreadUtil;
import network.NetworkUtil;


public class RenderClientApp extends Application {
	private static Logger logger = Logger.getLogger(RenderClientApp.class.getName());
	
	private LinkedBlockingQueue<String> receivedMessages =  new LinkedBlockingQueue<>();
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("RenderClientApp");
		loadFXMLandAttachController(primaryStage);
		// TODO:
		// Server able to draw to client using TwoColorImage
		// TwoColorImage encoded in bits, then hex
	    // hex string then zipped using CompressionUtil
		// unzipped and drawn on client side
		// Potential for Throughput efficient collaboration app
		// Ultra efficient low cost low data bandwidth requirements
		
		NetworkUtil.setupServerUDP(receivedMessages, 7777);
		
		ThreadUtil.addTask(()->{
			while(true) {
				try {
					String message = receivedMessages.poll(200, TimeUnit.MILLISECONDS);
					if (null!=message) {
						System.out.println("Message received: "+message);
					}
				} catch (InterruptedException e) {
					
				}
			}
		});
	}
	
	public void loadFXMLandAttachController(Stage primaryStage) {
		try {
			URL fxmlUrl = getClass().getResource("/main/resources/power.fxml");
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
