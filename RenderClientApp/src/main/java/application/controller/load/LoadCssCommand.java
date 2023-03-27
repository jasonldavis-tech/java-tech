package application.controller.load;

import javafx.application.Application;
import javafx.scene.Scene;

public class LoadCssCommand implements Runnable {
	private Scene scene;
	private Application application;
	
	public LoadCssCommand(final Application application, final Scene scene) {
		this.application = application;
		this.scene = scene;
	}
	
	@Override
	public void run() {		
		scene.getStylesheets().add(application.getClass().getResource("/main/resources/application.css").toExternalForm());
	}
}
