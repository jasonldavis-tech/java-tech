package application.controller.shutdown;

import commonModel.RenderEngineModel;
import javafx.stage.Stage;
import multithreading.ThreadUtil;

public class SetupOnExitCommand implements Runnable {	
	Stage primaryStage;
	RenderEngineModel model;
	
	public SetupOnExitCommand(final Stage primaryStage, final RenderEngineModel renderEngineModel) {
		this.primaryStage = primaryStage;
		this.model = renderEngineModel;
	}
	
	@Override
	public void run() {
		primaryStage.setOnCloseRequest((event) -> {
			model.exit();
			ThreadUtil.exitingApp();
		});
	}

}
