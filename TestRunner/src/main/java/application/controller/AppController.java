package application.controller;

import color.AsciiColorUtil;
import file.HtmlUtil;
import graphicsView.components.ViewComponentUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import testing.TestResult;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

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
	
	HTMLEditor textAreaOutput;
	TableView<TestResult> tableView;
	
	public void launchStartupSequence() {
		scene = new Scene(root,width,height);
		
		LoadCssCommand loadCssComand = new LoadCssCommand(application,scene);
		loadCssComand.run();
		
		primaryStage.setScene(scene);
		primaryStage.show();
		textAreaOutput = (HTMLEditor) idMap.get("textAreaOutputConsole");
		tableView = (TableView) idMap.get("tableViewTestResults");

		redirectSystemOutToTextArea();
		runTestCases();
	}
	
	public void redirectSystemOutToTextArea() {
		Logger.getLogger("").addHandler(new Handler() {
			
			@Override
			public void publish(LogRecord record) {
				// TODO Auto-generated method stub
				String removeAsciiColor = AsciiColorUtil.stripAsciiColorsFromString(record.getMessage());
				String messageAsHtml = HtmlUtil.createHtmlParagraphsFromEndlines(removeAsciiColor);
				textAreaOutput.setHtmlText(textAreaOutput.getHtmlText()+messageAsHtml);
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
	}


	
	private void runTestCases() {
		RunTestsCommand runTestsCommand = new RunTestsCommand();
		runTestsCommand.run();

		List<TestResult> testResults = runTestsCommand.getTestResults();

		logger.info(testResults.toString());

		updateTable(testResults);
	}

	private void addColumn(final String name, final String property) {
		TableColumn<TestResult, String> tableColumn = new TableColumn<TestResult, String>();
		tableColumn.setText(name);
		tableColumn.setCellValueFactory(new PropertyValueFactory<>(property));
		tableView.getColumns().add(tableColumn);
	}
	private void updateTable(List<TestResult> testResults) {
		addColumn("Test", "testName");
		addColumn("Message", "testMessage");
		addColumn("Time", "timeToComplete");
		addColumn("Success?", "testSuccessful");

		ObservableList<TestResult> tableData = FXCollections.observableArrayList(testResults);
		tableView.setItems(tableData);
	}
}
