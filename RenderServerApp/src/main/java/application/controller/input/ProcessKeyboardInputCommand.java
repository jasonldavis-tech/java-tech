package application.controller.input;

import java.util.logging.Logger;

import commonModel.RenderEngineModel;
import javafx.scene.input.KeyCode;

public class ProcessKeyboardInputCommand implements Runnable {
	private static Logger logger = Logger.getLogger(ProcessKeyboardInputCommand.class.getName());
	
	RenderEngineModel model;
	
	public ProcessKeyboardInputCommand(final RenderEngineModel renderEngineModel) {
		this.model = renderEngineModel;
	}
	
	@Override
	public void run() {
		KeyCode keyCode = model.getKeyBoardEventQueue().poll();
		logger.info("Key pressed: "+keyCode.getName());
		
		if (keyCode.isArrowKey())
		{
			if (keyCode.getCode()==KeyCode.DOWN.getCode()) {
				model.setCarVerticalOffset(model.getCarVerticalOffset()+3);
			}
			if (keyCode.getCode()==KeyCode.UP.getCode()) {
				model.setCarVerticalOffset(model.getCarVerticalOffset()-3);
			}						
		} else if (keyCode.getCode()==KeyCode.Q.getCode()) {
			model.setToggleAddFilterOn(!model.isToggleAddFilterOn());
			model.setAddOffset(0);
		} else if (keyCode.getCode()==KeyCode.W.getCode()) {
			model.setToggleSubtractFilterOn(!model.isToggleSubtractFilterOn());		
			model.setSubtractOffset(0);
		} else if (keyCode.getCode()==KeyCode.Z.getCode()) {
			model.setToggleRenderingOn(!model.isToggleRenderingOn());
		}	
	}
}
