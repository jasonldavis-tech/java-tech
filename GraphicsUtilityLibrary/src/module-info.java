module GraphicsUtilityLibrary {
	requires transitive java.logging;
	requires transitive java.desktop;
	requires transitive FileUtilityLibrary;
	requires transitive javafx.graphics;
	requires transitive javafx.controls;
	requires javafx.swing;
	requires org.apache.commons.imaging;
	exports graphicsView.components;
	exports graphicsView.render;
	exports commonController;
	exports commonModel;
}