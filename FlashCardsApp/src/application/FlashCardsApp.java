package application;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

import data.InputValidationUtil;
import data.StringParseUtil;
import file.FileUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import performance.PerformanceUtil;
import random.RandomUtil;


public class FlashCardsApp extends Application {
	private static Logger logger = Logger.getLogger(FlashCardsApp.class.getName());
	
	private int currentTerm=0;
	
	Scene scene;
	
	VBox vbox;
	HashMap<String, String> termDefinitionMap = new HashMap<>();
	Label termLabel;
	TextFlow textFlow = new TextFlow();
	Text definitionLabel = new Text();
	String keyArray[];
	TextField textField = new TextField();
	Label potentialMismatch = new Label();
	Label instructionLabel;
	
	Label typingSpeedLastAttemptLabel = new Label("Typing Speed Last Attempt: 0.0 WPM");
	Label typingSpeedLabel = new Label("Typing Average Speed: 0.0 WPM");
	Label typingSpeedMaxThroughputAchievedLabel = new Label("Max Typing Throughput Achieved: 0.0 WPM");
	Label neverSayDieLabel = new Label("Never Say Die!");	
	boolean timerStarted=false;
	ArrayList<String> flaggedMismatches = new ArrayList<>();
	int totalWordsTypedAndVerified=0;
	
	PerformanceUtil OverallTime = new PerformanceUtil();
	
	ArrayList<Canvas> progressBarCanvases = new ArrayList<>();
	
	Label wordsTyped = new Label("0/600");
	
	
	public void canvasToColor(Canvas canvas, String colorString) {
		Color color = Color.valueOf(colorString);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		graphicsContext.setFill(Paint.valueOf(color.toString()));
		graphicsContext.fillRect(0, 0, 25, 25);
	}
	
	public void setupProgressBar() {
		HBox progressBar = new HBox();
		
		for (int i=0; i<10; i++) {
			Canvas canvas = new Canvas(25,25);
			canvasToColor(canvas, "#EEFFEE");
			progressBarCanvases.add(canvas);
			progressBar.getChildren().add(canvas);
		}
		progressBar.setMinWidth(250);
		vbox.getChildren().add(progressBar);
		wordsTyped.setPadding(new Insets(0, 0, 0, 10));
		wordsTyped.setMinWidth(150);
		// wordsTyped.setTextAlignment(TextAlignment.RIGHT);
		wordsTyped.setFont(Font.font(16));
		progressBar.getChildren().add(wordsTyped);
	}
	
	public void updateProgressBar(double progress) {
		double elementsToTurnOn = progress*10;
		int index = (int) elementsToTurnOn;
		
		for (int i=1; i<=elementsToTurnOn; i++) {
			canvasToColor(progressBarCanvases.get(i-1),"#AAFFAA");
		}
		
		wordsTyped.setText(totalWordsTypedAndVerified+"/600");
	}
	
	private void updateProgress() {
		double progress = (double) totalWordsTypedAndVerified/600;
		updateProgressBar(progress);
	}
	
	
	private void resizeEvent() {
	    double newWidth = scene.getWidth()-50;
		termLabel.setMinWidth(newWidth);
		instructionLabel.setMinWidth(newWidth);
		textFlow.setMinWidth(newWidth);
		textFlow.setMaxWidth(newWidth);
		definitionLabel.setWrappingWidth(newWidth);		
		textField.setMinWidth(newWidth);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setTitle("Flash Card Definition Typing Throughput");
			
			vbox = new VBox();
			vbox.getStyleClass().clear();
			vbox.getStyleClass().add("mainElementCustomClass");
			root.getChildren().add(vbox);
			setupProgressBar();
			
			logger.info("Working path: "+FileUtil.getWorkingPath());
			String economicTerms = FileUtil.readFileAsString("src\\main\\resources\\economicsTerms.txt");			
			
			String lines[] = economicTerms.split("\n");
			
			for (int i=0; i<lines.length; i++) {
				String elements[] = lines[i].split(" - ");
				termDefinitionMap.put(elements[0], elements[1]);
			}
			
			termDefinitionMap.forEach((term,def) -> {
				logger.info("\nTerm: "+term+"\n"+def+"\n");
			});
						
			Set<String> keys = termDefinitionMap.keySet();
			currentTerm = RandomUtil.getRandomNumber(0, keys.size());
			keyArray = keys.toArray(new String[0]);
			termLabel = new Label("Term: "+keyArray[currentTerm]);
			termLabel.setFont(Font.font(25));
			String definition = termDefinitionMap.get(keyArray[currentTerm]).trim();
			definitionLabel.setText("Definition: "+definition);

			
			
			
			Button button = new Button("Next");
			button.setOnAction((event) -> next() );
			
			button.setMinWidth(200);
			termLabel.setMinWidth(200);
			
			textFlow.setMinWidth(scene.getWidth()-50);
			textFlow.setMaxWidth(scene.getWidth()-50);
			definitionLabel.setWrappingWidth(scene.getWidth()-50);
			
			scene.widthProperty().addListener((obs,oldValue,newValue) -> {
				resizeEvent();
			});;
			
			textFlow.getChildren().add(definitionLabel);
			vbox.getChildren().addAll(termLabel,textFlow);
			
			
			textField.setPrefWidth(scene.getWidth());
			
			textField.setOnKeyReleased((event) -> {
				KeyEvent keyEvent = (KeyEvent) event;
				
				if (keyEvent.getCode()==KeyCode.ENTER) {
					System.out.println("Enter Key Released");
					String string1 = textField.getText().trim();
					String string2 = termDefinitionMap.get(keyArray[currentTerm]).trim();
					System.out.println("String 1: "+string1);
					System.out.println("String 2: "+string2);
					
					if (string1.compareTo(string2)==0) {						
						totalWordsTypedAndVerified+=StringParseUtil.getWordCount(string1);
						updateProgress();
						if (!firstGoalAchieved&&totalWordsTypedAndVerified>=600) {
							firstGoal();
						}
						logger.info("totalWordsTypedAndVerified = "+totalWordsTypedAndVerified);
						potentialMismatch.setVisible(false);
						next();
						textField.setStyle("-fx-focus-color: #00CCFF");
					} else {
						textField.setStyle("-fx-focus-color: #FF0000");
						String mismatch = InputValidationUtil.flagPotentialMismatches(string2, string1);
						potentialMismatch.setText("Potential Mismatch: "+mismatch);
						potentialMismatch.setVisible(true);
						flaggedMismatches.add(mismatch);
						
					}
				}
				
				if (keyEvent.getCode()==KeyCode.ESCAPE) {
					String mismatches = getMismatches();
					logger.info("Mismatches: "+mismatches);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Flagged Mismatches");
					alert.setHeaderText("Flagged Mismatches");
					alert.setContentText(mismatches);
					alert.show();					
				}
				
				if (!timerStarted) {
					OverallTime.startInstant();
					PerformanceUtil.getInstance().startInstant();
					timerStarted = true;
				}
			});
			
			instructionLabel = new Label("Type the definition in the space below to present the next term");
			
			vbox.getChildren().addAll(potentialMismatch,instructionLabel,textField,typingSpeedLastAttemptLabel,typingSpeedLabel);
			vbox.getChildren().addAll(typingSpeedMaxThroughputAchievedLabel, neverSayDieLabel);
			
			vbox.autosize();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	boolean firstGoalAchieved = false;
	
	private void firstGoal() {
		OverallTime.stopInstant();
		long timeInMilliseconds = OverallTime.getInstantDifferenceMilliseconds();
		double seconds = timeInMilliseconds/1000;
		double minutes = seconds/60;
		firstGoalAchieved = true;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Words Typed: "+totalWordsTypedAndVerified);
		alert.setHeaderText("Words Typed: "+totalWordsTypedAndVerified);
		
		double wordsPerMinute = totalWordsTypedAndVerified/minutes;
		
		String wpmContent = totalWordsTypedAndVerified+" words typed over a period of "
			    +String.format("%.1f", seconds)+" seconds, "
			    +String.format("%.1f", minutes)+" minutes. "
			    +String.format("%.1f", wordsPerMinute)+" WPM Words per Minute";
		
		TextFlow textFlow = new TextFlow();
		Text text = new Text(wpmContent);
		textFlow.setMaxWidth(350);
		textFlow.setPadding(new Insets(20));		
		text.setWrappingWidth(350);
		textFlow.getChildren().add(text);
		alert.getDialogPane().setMaxWidth(350);
		alert.getDialogPane().setContent(textFlow);
		alert.show();
		logger.info(wpmContent);
	}
	
	private String getMismatches() {
		final StringWriter stringWriter = new StringWriter();
		
		flaggedMismatches.stream().forEach((word) -> stringWriter.append(word+", "));
		String mismatches = stringWriter.toString().trim();
		mismatches = mismatches.substring(0,mismatches.length()-1);
		return mismatches;
	}
	
	private class TypingSpeed {		
		long timeInMilliseconds;
		int words;
		double WPM;
	}
	
	ArrayList<TypingSpeed> typingSpeedReadings = new ArrayList<>();
	
	double max = 0;
	
	private void updateTypingSpeed() {
		long timeInMilliseconds = PerformanceUtil.getInstance().getInstantDifferenceMilliseconds();
		String sentence = textField.getText().trim();
		String words[] = sentence.split(" ");
		
		TypingSpeed typingSpeed = new TypingSpeed();
		typingSpeed.timeInMilliseconds = timeInMilliseconds;
		typingSpeed.words = words.length;
		
		double seconds = timeInMilliseconds/1000;
		double minutes = seconds/60;
		typingSpeed.WPM = typingSpeed.words/minutes;
		
		typingSpeedReadings.add(typingSpeed);
		
		double sum=0;
		for (int i=0; i<typingSpeedReadings.size(); i++) {
			sum+=typingSpeedReadings.get(i).WPM;
		}
		
		double averageWPM = sum/typingSpeedReadings.size();
		
		typingSpeedLastAttemptLabel.setText("Typing Speed Last Attempt: "+String.format("%.1f",typingSpeed.WPM)+" WPM");
		typingSpeedLabel.setText("Typing Speed Average: "+String.format("%.1f",averageWPM)+" WPM");
		
		if (typingSpeed.WPM>max) {
			max = typingSpeed.WPM;
		}
		
		typingSpeedMaxThroughputAchievedLabel.setText("Max Typing Throughput Achieved: "+String.format("%.1f", max)+" WPM");
		
		
		logger.info("Typing Speed Average: "+String.format("%.1f",averageWPM)+" WPM");
	}
	
	private void next() {		
		PerformanceUtil.getInstance().stopInstant();
		timerStarted = false;
		System.out.println("Time required to type: "+PerformanceUtil.getInstance().getInstantDifferenceMilliseconds()+" ms");
		
		updateTypingSpeed();
		
		System.out.println("Next card called");
		textField.setText("");
		if (currentTerm>=keyArray.length-1) {
			currentTerm=0;
		} else {
			currentTerm++;
		}

		termLabel.setText("Term: "+keyArray[currentTerm]);
		String definition = termDefinitionMap.get(keyArray[currentTerm]).trim();
		definitionLabel.setText("Definition: "+definition);
		
		PerformanceUtil.getInstance().startInstant();
		timerStarted = true;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
