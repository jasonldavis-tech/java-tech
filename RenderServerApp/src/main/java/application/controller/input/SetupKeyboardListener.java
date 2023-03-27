package application.controller.input;

import commonModel.RenderEngineModel;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class SetupKeyboardListener implements Runnable {
	Scene scene;
	RenderEngineModel model;

	public SetupKeyboardListener(Scene scene, RenderEngineModel model) {
		this.scene = scene;
		this.model = model;
	}
	
	@Override
	public void run() {
		scene.setOnKeyPressed((event) -> {
			// Process Keyboard Input
			KeyCode keyCode = event.getCode();
			model.getKeyBoardEventQueue().add(keyCode);
		});		
	}
}
