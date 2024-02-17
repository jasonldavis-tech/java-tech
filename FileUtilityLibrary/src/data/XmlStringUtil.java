package data;

import java.io.StringWriter;
import java.util.List;

public class XmlStringUtil {
	public static String addAttribute(String qualifiedName, String value) {
		String attribute = qualifiedName+"=\""+value+"\"";
		return attribute;
	}
	
	public static String createElement(String qualifiedName, List<Attribute> attributes, boolean terminate) {
		StringWriter elementWriter = new StringWriter();
		elementWriter.append("<");
		elementWriter.append(qualifiedName);
		
		if (null!=attributes) {
			attributes.forEach((attribute) -> {
				elementWriter.append(" "+addAttribute(attribute.getQualifiedName(), attribute.getValue()));
			});
		}
		
		if (terminate) {
			elementWriter.append("/>\n");
		} else {
			elementWriter.append(">\n");
		}
		
		return elementWriter.toString();
	}
	
	public static String createEndElement(String qualifiedName) {
		String endElement = "</"+qualifiedName+">\n";
		return endElement;
	}

}
