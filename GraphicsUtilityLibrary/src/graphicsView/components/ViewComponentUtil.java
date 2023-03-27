package graphicsView.components;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.logging.Logger;

public class ViewComponentUtil {
	private static Logger logger = Logger.getLogger(ViewComponentUtil.class.getName());
	public static HashMap<String, Node> getIdNodeMap(Parent root) {
		HashMap<String, Node> map = new HashMap<>();
		addElementsToMap(map,root);
		return map;
	}
	
	private static void addElementsToMap(HashMap<String, Node> map, Node element) {
		map.put(element.getId(), element);
		if (element instanceof Parent p) {
			p.getChildrenUnmodifiable().forEach((node) -> {
				map.put(node.getId(), node);
				if (node instanceof Parent parent) {
					addElementsToMap(map,node);
				}
			});
		}		
	}

	public static void infoAlert(String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
		logger.info("Alert Shown: title="+title+", header="+header+", content="+content);
	}

}
