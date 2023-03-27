package application.controller;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

import application.controller.viewSetup.SetupInitialView;
import audio.AudioUtil;
import graphicsView.components.ViewComponentUtil;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import story.StoryOutlineUtil;
import support.SupportMessageUtil;

public class AppController {
	private static Logger logger = Logger.getLogger(AppController.class.getName());
	
	Application application;
	Stage primaryStage;
	Parent root;
	HashMap<String, Node> idMap;
	
	public Parent getRoot() {
		return root;
	}

	public void setRoot(Parent root) {
		this.root = root;
		idMap=ViewComponentUtil.getIdNodeMap(root);
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
	
	TextArea textAreaOutput;
	
	public void launchStartupSequence() {
		if (null==application || null==primaryStage) {
			logger.info("Must first inject Application and PrimaryStage objects before starting up application.");			
		}
		
		if (null==root) {
			logger.info("Must first inject a root User Interface Component.");
		}
		
		SetupInitialView setupInitialView = new SetupInitialView(application,primaryStage,root);
		setupInitialView.run();
		
		Button buttonSend = (Button) idMap.get("buttonSend");
		TextField textFieldInput = (TextField) idMap.get("textFieldCommand");
		textAreaOutput = (TextArea) idMap.get("textAreaOutputConsole");
		
		Runnable handler = () -> {
			System.out.println("Send command handler evoked");
			String command = textFieldInput.getText();
			String displayText = ">>> "+command+"\n";
			textAreaOutput.appendText(displayText);
			textFieldInput.setText("");
			handleCommand(command);
		};
		
		textFieldInput.setOnKeyPressed((event) -> {
			if (event.getCode()==KeyCode.ENTER) {
				handler.run();
			} else if (event.getCode()==KeyCode.UP) {
				commandIndex++;
				if (commandIndex>commandHistory.size()) {
					commandIndex=1;
				}
				String commandFromHistory = commandHistory.get(commandHistory.size()-commandIndex);
				textFieldInput.setText(commandFromHistory);
			} else if (event.getCode()==KeyCode.DOWN) {
				commandIndex--;
				if (commandIndex<=0) {
					commandIndex=commandHistory.size();
				}
				String commandFromHistory = commandHistory.get(commandHistory.size()-commandIndex);
				textFieldInput.setText(commandFromHistory);
			}
		});
		buttonSend.setOnAction((event) -> handler.run());
	}
	
	private void updateAndLog(String message) {
		logger.info(message);
		textAreaOutput.appendText(message+"\n");
	}
	
	private ArrayList<String> commandHistory = new ArrayList<>();
	int commandIndex=0;
	
	private void handleCommand(String command) {
		commandIndex=0;
		commandHistory.add(command);
		String commandsArray[] = {
			"generateSupport()",
			"generateStoryOutline()",
			"Audio.playC()",
			"Audio.playD()",
			"Audio.playE()",
			"Audio.playFarandolePhrase1()",
			"Audio.playFarandolePhrase2()",
			"supportedCommands()"
		};
		
		final StringWriter supportedCommands = new StringWriter();
		
		Arrays.asList(commandsArray).forEach((element) -> {
			supportedCommands.append(element+"\n");
		});
		
		switch (command) {
		case "generateSupport()":
			String supportMessage = SupportMessageUtil.generateSupport();
			updateAndLog(supportMessage);			
			break;
		case "generateStoryOutline()":
			String storyOutline = StoryOutlineUtil.generateStoryOutline();			
			updateAndLog(storyOutline);			
			break;
		case "supportedCommands()":
			updateAndLog(supportedCommands.toString());
			break;
		case "Audio.playC()":
			updateAndLog("Playing Middle C Frequency");
			AudioUtil.playMiddleC(2);
			break;
		case "Audio.playD()":
			updateAndLog("Playing D Frequency");
			AudioUtil.playD(2);
			break;
		case "Audio.playE()":
			updateAndLog("Playing E Frequency");
			AudioUtil.playE(2);
			break;
		case "Audio.playFarandolePhrase1()":
			updateAndLog("Playing Franadole Phrase 1");
			AudioUtil.playFarandolePhrase1();
			break;
		case "Audio.playFarandolePhrase2()":
			updateAndLog("Playing Franadole Phrase 2");
			AudioUtil.playFarandolePhrase2();
			break;			
		default:
			String displayText = "Unsupported command... try supportedCommands()\n";
			updateAndLog(displayText);		
		}
	}
}
