module FlashCardsApp {
	requires javafx.controls;
	requires java.logging;
	requires FileUtilityLibrary;
	
	opens application to javafx.graphics, javafx.fxml;
}
