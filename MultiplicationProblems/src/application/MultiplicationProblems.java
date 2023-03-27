package application;
	
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import javafx.stage.Stage;
import performance.PerformanceUtil;

public class MultiplicationProblems extends Application {
	private static Logger logger = Logger.getLogger(MultiplicationProblems.class.toString());
	private Random random = new Random(System.nanoTime());
	private ArrayList<Integer> recordedTimes = new ArrayList<>();
	private int value1=2;
	private int value2=2;
	
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
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);			
			primaryStage.show();
			VBox vbox = new VBox();
			root.getChildren().add(vbox);
			Canvas canvas = createCanvas();
			vbox.getChildren().add(canvas);
			vbox.setMinHeight(400);
			Label answerCheckLabel = new Label("Test");
			answerCheckLabel.setMinWidth(400);
			answerCheckLabel.setMinHeight(200);
			answerCheckLabel.setAlignment(Pos.CENTER);
			vbox.getChildren().add(answerCheckLabel);
			HBox hbox = new HBox();
			vbox.getChildren().add(hbox);			
			hbox.setPadding(new Insets(10,10,10,10));
			hbox.setMinWidth(400);
			hbox.setMinHeight(200);
			hbox.setAlignment(Pos.CENTER);			
			Label problemLabel = new Label(value1+" * "+value2);
			problemLabel.setFont(Font.font(20));
			PerformanceUtil.getInstance().start();
			hbox.getChildren().add(problemLabel);
			Label equalsLabel = new Label(" = ");
			hbox.getChildren().add(equalsLabel);
			TextField answerField = new TextField();
			hbox.getChildren().add(answerField);
			hbox.autosize();

			vbox.getChildren().addAll(encouragementLabel,problemSolvedInLabel,averageLabel);			
			vbox.autosize();
			
			answerField.setFont(Font.font(20));
			answerField.setOnKeyReleased((event) -> {
				KeyEvent keyEvent = (KeyEvent) event;
				if (event.getCode()==KeyCode.ENTER) {
					System.out.println("Enter Key Released: "+answerField.getText());
					try {					
						int value = Integer.parseInt(answerField.getText());
						int product = value1*value2;
						if (value == product) {
							canvasToColor(canvas,Color.GREEN);
							answerCheckLabel.setText("Answer is correct");
							System.out.println("Answer is correct");
							PerformanceUtil.getInstance().stop();
							System.out.println("Problem solved in: "+PerformanceUtil.getInstance().getRecordedTime()+" ns");
							System.out.println("Problem solved in: "+PerformanceUtil.getInstance().getRecordedTimeMilliseconds()+" ms");
							recordedTimes.add(PerformanceUtil.getInstance().getRecordedTimeMilliseconds());
							problemSolvedInLabel.setText("Problem Solved in: "+PerformanceUtil.getInstance().getRecordedTimeMilliseconds()+" ms");
							computeAverage();
							
							answerField.setText("");
							
							value1 = random.nextInt(0,10);
							value2 = random.nextInt(0,10);
							PerformanceUtil.getInstance().start();
							problemLabel.setText(value1+" * "+value2);							
						} else {
							canvasToColor(canvas,Color.RED);
							answerCheckLabel.setText("Answer is incorrect");
							System.out.println("Answer is incorrect");
							System.out.println("Problem solving running time: "+PerformanceUtil.getInstance().getRunningTime()+" ns");
						} 
					} catch (NumberFormatException e) {
						System.out.println("Could not parse number");
					}
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
