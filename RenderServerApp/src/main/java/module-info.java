module RenderServerApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive java.logging;
	requires transitive java.desktop;
	requires java.base;
	requires FileUtilityLibrary;
    requires GraphicsUtilityLibrary;
	requires org.apache.commons.imaging;
	requires javafx.swing;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.controller to javafx.fxml;
}
