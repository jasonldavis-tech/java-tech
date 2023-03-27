package application.controller.viewSetup;

import java.util.logging.Logger;

import application.ScreenContants;
import application.controller.EventBus;
import application.controller.events.WindowResizeEvent;
import commonModel.RenderEngineModel;
import graphicsView.components.StreamImageView;
import graphicsView.render.RenderEngine;
import javafx.scene.layout.VBox;
import multithreading.ThreadUtil;

public class WireStreamViewToRenderEngine implements Runnable {
    private static Logger logger = Logger.getLogger(WireStreamViewToRenderEngine.class.getName());
	VBox vbox;
	RenderEngineModel model;
	StreamImageView streamImageView;
	
	public WireStreamViewToRenderEngine(VBox vbox) {
		this.vbox=vbox;
	}
	
	RenderEngine renderEngine;
	
	@Override
	public void run() {
		streamImageView = new StreamImageView(null, 30, vbox.getScene().getWidth(), vbox.getScene().getHeight());
		vbox.getChildren().add(streamImageView.getImageView());		
		model = new RenderEngineModel(streamImageView.getImageQueue());
		streamImageView.setSleepTimeMilliseconds(model.getSleepTimeMilliseconds());
		renderEngine = new RenderEngine(model,vbox.getScene().getWidth(),vbox.getScene().getHeight());
		ThreadUtil.addTask(renderEngine);
		
		EventBus.get().addHandler(WindowResizeEvent.RESIZE, this::handleResizeEvent);
	}
	
	private void handleResizeEvent(String eventMessage) {
		double newWidth = vbox.getScene().getWidth()-40;
		double newHeight= vbox.getScene().getHeight()-100;
		streamImageView.setRenderWidth(newWidth);
		streamImageView.setRenderHeight(newHeight);
		streamImageView.autosize();
		renderEngine.setRenderWidth(newWidth);
		renderEngine.setRenderHeight(newHeight);
	}
	
	public RenderEngineModel getGeneratedModel() {
		return model;
	}

}
