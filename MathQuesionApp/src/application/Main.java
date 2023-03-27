package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import performance.PerformanceUtil;
import support.SupportMessageUtil;

public class Main extends Application {
	private Logger logger = Logger.getLogger(Main.class.getName());
	private Random random = new Random(System.nanoTime());
	private ArrayList<Integer> recordedTimes = new ArrayList<>();
	private int value1=2;
	private int value2=2;
	int questionsAnsweredCorrectly=0;
	
	public Canvas createCanvas() {
		Canvas canvas = new Canvas(25,25);
		canvasToColor(canvas,Color.RED);
		canvas.setWidth(25);
		canvas.setHeight(25);
		
		return canvas;
	}
	
	public void canvasToColor(Canvas canvas, Color color) {
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		graphicsContext.setFill(Paint.valueOf(color.toString()));
		graphicsContext.fillRect(0, 0, 25, 25);
	}
	
	Label encouragementLabel = new Label("Never Say Die!");
	Label problemSolvedInLabel = new Label("Problem Solved in:  ms");
	Label averageLabel = new Label("Average:  ms");
	
	class Question {
		int a;
		int b;
		int reanswerdRight=0;
	}
	
	ArrayList<Question> questionsAnsweredWrong = new ArrayList<>();
	
	BorderPane root = new BorderPane();
	Scene scene;
	VBox vbox = new VBox();
	Canvas canvas = createCanvas();
	Label answerCheckLabel;
	HBox hbox = new HBox();
	Label problemLabel;
	Label equalsLabel = new Label(" = ");
	TextField answerField = new TextField();
	
	private void setupComponents(Stage primaryStage) {
		setupRootElement(primaryStage);
		
		root.getChildren().add(vbox);
		vbox.getChildren().add(canvas);
		vbox.setMinHeight(400);

		setupAnswerCheckLabel();
		
		vbox.getChildren().add(hbox);			
		setupFormattingOnHorizontalBox();
		
		setupAndAddProblemLabels();
		
		hbox.getChildren().add(answerField);
		hbox.autosize();

		vbox.getChildren().addAll(encouragementLabel,problemSolvedInLabel,averageLabel);			
		vbox.autosize();
	}
	
	private void setupRootElement(Stage primaryStage) {
		scene = new Scene(root,400,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);			
		primaryStage.show();
	}
	
	private void setupAnswerCheckLabel() {
		answerCheckLabel = new Label("Test");
		answerCheckLabel.setMinWidth(400);
		answerCheckLabel.setMinHeight(200);
		answerCheckLabel.setAlignment(Pos.CENTER);
		vbox.getChildren().add(answerCheckLabel);
	}
	
	private void setupFormattingOnHorizontalBox() {
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setMinWidth(400);
		hbox.setMinHeight(200);
		hbox.setAlignment(Pos.CENTER);
	}
	
	private void setupAndAddProblemLabels() {
		problemLabel = new Label(value1+" + "+value2);
		problemLabel.setFont(Font.font(20));
		PerformanceUtil.getInstance().start();
		hbox.getChildren().add(problemLabel);
		hbox.getChildren().add(equalsLabel);
	}
	
	private void processCorrectAnswer() {
		logAndDisplayAnswerIsCorrect();
	}
	
	private void logAndDisplayAnswerIsCorrect() {
		canvasToColor(canvas,Color.GREEN);
		answerCheckLabel.setText("Answer is correct");
		System.out.println("Answer is correct");
		PerformanceUtil.getInstance().stop();
		System.out.println("Problem solved in: "+PerformanceUtil.getInstance().getRecordedTime()+" ns");
		System.out.println("Problem solved in: "+PerformanceUtil.getInstance().getRecordedTimeMilliseconds()+" ms");
		recordedTimes.add(PerformanceUtil.getInstance().getRecordedTimeMilliseconds());
		problemSolvedInLabel.setText("Problem Solved in: "+PerformanceUtil.getInstance().getRecordedTimeMilliseconds()+" ms");
		
		questionsAnsweredCorrectly++;
		
		displayEncouragementAlertOrAskAnotherQuestion();
	}
	
	private void displayEncouragementAlertOrAskAnotherQuestion() {
		if (questionsAnsweredCorrectly%25==0) {
			logger.info("Answered "+questionsAnsweredCorrectly+" Math Addition Problems correctly calling generateSupport method");
			String support = SupportMessageUtil.generateSupport();
			logger.info(support);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Good Work! Never Say Die!");
			alert.setHeaderText(questionsAnsweredCorrectly+" Questions Answered Correctly!");
			alert.setContentText("Good Work! Never Say Die!");
			alert.show();
			alert.setOnCloseRequest((event) -> {
				finishProcessCorrectAnswer();
			});
     	} else {
     		finishProcessCorrectAnswer();
     	}
	}
	
	private void finishProcessCorrectAnswer() {
		computeAverage();
		reduceOrRemoveFromQuestionsAnsweredWrongList();		
		loadNewQuestion();
	}
	
	private void reduceOrRemoveFromQuestionsAnsweredWrongList() {
		List<Question> questions = questionsAnsweredWrong.stream().filter(q -> q.a==value1 && q.b==value2).toList();
		
		if (questions.size()>0) {
			Question question = questions.get(0);
			question.reanswerdRight++;
			if (question.reanswerdRight==3) { // Answered right 3 times, remove from questionsAnsweredWrong
				logger.info("Wrong Question Answered Right 3 times");
				questionsAnsweredWrong.remove(question);
			}
		}		
	}
	
	private void loadNewQuestion() {
		answerField.setText("");
		
		// 1 out of 4 times ask missed question
		if (questionsAnsweredWrong.size()>0 && random.nextInt(0,3)==0) {
			logger.info("Reasking Wrong Question");
			// get random element from questonsAnsweredWrong
			int randomElement = random.nextInt(0,questionsAnsweredWrong.size());
			Question question = questionsAnsweredWrong.get(randomElement);
			value1 = question.a;
			value2 = question.b;
		} else {
			value1 = random.nextInt(0,10);
			value2 = random.nextInt(0,10);
		}
		PerformanceUtil.getInstance().start();
		problemLabel.setText(value1+" + "+value2);		
	}
	
	private void processIncorrectAnswer() {
		List<Question> questions = questionsAnsweredWrong.stream().filter(q -> q.a==value1 && q.b==value2).toList();
		
		if (questions.size()>0) {
			questions.get(0).reanswerdRight--;
		} else {
			Question question = new Question();
			question.a = value1;
			question.b = value2;
			questionsAnsweredWrong.add(question);
		}
		
		
		canvasToColor(canvas,Color.RED);
		answerCheckLabel.setText("Answer is incorrect");
		System.out.println("Answer is incorrect");
		System.out.println("Problem solving running time: "+PerformanceUtil.getInstance().getRunningTime()+" ns");
	}
	
	private void handleAnswerEvent() {
		System.out.println("Enter Key Released: "+answerField.getText());
		try {					
			int value = Integer.parseInt(answerField.getText());
			int sum = value1+value2;
			if (value == sum) {
				processCorrectAnswer();
			} else {
				processIncorrectAnswer();
			} 
		} catch (NumberFormatException e) {
			System.out.println("Could not parse number");
		}		
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			setupComponents(primaryStage);
			
			answerField.setFont(Font.font(20));
			answerField.setOnKeyReleased((event) -> {
				KeyEvent keyEvent = (KeyEvent) event;
				if (event.getCode()==KeyCode.ENTER) {
					handleAnswerEvent();
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void computeAverage() {
		int sum=0;
		for (int i=0; i<recordedTimes.size(); i++) {
			sum+=recordedTimes.get(i);
		}
		int average = sum/recordedTimes.size();
		System.out.println("Current Average: "+average+" ms");
		averageLabel.setText("Average: "+average+" ms");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
