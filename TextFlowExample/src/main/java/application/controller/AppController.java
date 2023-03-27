package application.controller;

import java.util.HashMap;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import color.AsciiColorUtil;
import file.HtmlUtil;
import graphicsView.components.ViewComponentUtil;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import multithreading.ThreadUtil;

public class AppController {
	private static Logger logger = Logger.getLogger(AppController.class.getName());
	
	Application application;
	Scene scene;
	Stage primaryStage;
	int width;
	int height;
	
	Parent root;
	HashMap<String, Node> idMap;
	
	public Parent getRoot() {
		return root;
	}

	public void setRoot(Parent root) {
		this.root = root;
		idMap=ViewComponentUtil.getIdNodeMap(root);
	}	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	TextArea textAreaOutput;
	TextField textFieldCommand;
	Label labelCommand;
	Button buttonSend;
	
	public void launchStartupSequence() {
		scene = new Scene(root,width,height);
		
		LoadCssCommand loadCssComand = new LoadCssCommand(application,scene);
		loadCssComand.run();
		
		primaryStage.setScene(scene);
		primaryStage.show();
		textAreaOutput = (TextArea) idMap.get("textAreaOutputConsole");
		textAreaOutput.setEditable(false);
		textAreaOutput.setBackground(Background.fill(Color.BLACK));
		textAreaOutput.setFont(Font.font(20));
		textFieldCommand = (TextField) idMap.get("textFieldCommand");
		
		labelCommand = (Label) idMap.get("labelCommand");
		buttonSend = (Button) idMap.get("buttonSend");
		
		addResizeScreenHandler();
		redirectSystemOutToTextArea();
		resize();
	}
	
	
	public void addResizeScreenHandler() {		
		scene.widthProperty().addListener((obs,oldValue,newValue)-> {
			resize();
		});
		
		scene.heightProperty().addListener((obs,oldValue,newValue)-> {
			resize();
		});
	}
	
	public void resize() {
		double combinedDifference = labelCommand.getPrefWidth()+buttonSend.getPrefHeight()+250;
		textFieldCommand.setPrefWidth(scene.getWidth()-combinedDifference);
	}
	
	public void redirectSystemOutToTextArea() {
		Logger.getLogger("").addHandler(new Handler() {
			
			@Override
			public void publish(LogRecord record) {
				// TODO Auto-generated method stub
				String removeAsciiColor = AsciiColorUtil.stripAsciiColorsFromString(record.getMessage());
				String messageAsHtml = HtmlUtil.createHtmlParagraphsFromEndlines(removeAsciiColor);
				
				textAreaOutput.setWrapText(true);
				textAreaOutput.appendText(messageAsHtml);
			}

			@Override
			public void flush() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void close() throws SecurityException {
				// TODO Auto-generated method stub
				
			}
		});
		
		for (int i=0; i<1000; i++) {
			logger.info("Test Message "+i);
		}
	}
}
