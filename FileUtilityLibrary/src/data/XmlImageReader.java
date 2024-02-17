package data;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import data.xmlImageFormat.RgbColor;
import data.xmlImageFormat.XmlImage;
import data.xmlImageFormat.XmlImagePixel;

public class XmlImageReader {
	private static Logger logger = Logger.getLogger(XmlImageReader.class.toString());
	
	private String fileData;
	private XmlImage xmlImage;

	public XmlImage getXmlImage() {
		return xmlImage;
	}

	public XmlImageReader(final String fileData) {
		this.fileData = fileData;
		
		try {
			SAXParser xmlParser = SAXParserFactory.newInstance().newSAXParser();
		    InputStream inputStream = StringParseUtil.convertStringToInputStream(fileData);
		    
		    XmlImageHandler xmlImageHandler = new XmlImageHandler();
		    
		    xmlParser.parse(inputStream, xmlImageHandler);
		    xmlImage = xmlImageHandler.getXmlImage();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class XmlImageHandler extends DefaultHandler {
		private XmlImage xmlImage;
		
		public XmlImage getXmlImage() {
			return xmlImage;
		}
		
		private int repeatYIncrement=0;
		private boolean repeatY=false;
		private int repeatYQuantity=0;

		@Override
	    public void startElement (String uri, String localName,
                String attributeName, Attributes attributes)
                		throws SAXException {
			logger.info("attributeName: "+attributeName);
			for (int i=0; i<attributes.getLength(); i++) {
				logger.info(attributes.getQName(i)+" : "+attributes.getValue(i));
			}
			
			if (attributeName.equals("image")) {
				parseImage(attributes);				
			} else if (attributeName.equals("element")) {
				parseElement(attributes);				
			} else if (attributeName.equals("repeatY")) {
				repeatY=true;
				repeatYIncrement = Integer.valueOf(attributes.getValue("increment"));
				repeatYQuantity = Integer.valueOf(attributes.getValue("quantity"));
			}
		}

		@Override
	    public void endElement(String uri, String localName,
                String attributeName) throws SAXException {
			if (attributeName.equals("repeatY")) {
				repeatY=false;
			}
		}
		
		private void parseImage(Attributes attributes) {
			int width = Integer.valueOf(attributes.getValue("width"));
			int height = Integer.valueOf(attributes.getValue("height"));
			RgbColor rgbColor = new RgbColor(attributes.getValue("backgroundColor"));
			xmlImage = new XmlImage(width, height, rgbColor);
		}
		
		private void parseElement(Attributes attributes) {
			int x = Integer.valueOf(attributes.getValue("x"));
			int y = Integer.valueOf(attributes.getValue("y"));
			RgbColor rgbColor = new RgbColor(attributes.getValue("rgb"));
			int quantity = Integer.valueOf(attributes.getValue("quantity"));
			
			if (!repeatY) {				
				for (int i=0; i<quantity; i++) {
					XmlImagePixel pixel = new XmlImagePixel();
				
					pixel.setX(x+i);
					pixel.setY(y);
					pixel.setRgbColor(rgbColor);
					
					xmlImage.setPixel(pixel);
				}
			} else {
				for (int i=0; i<quantity; i++) {
					for (int j=0;j<repeatYQuantity; j+=repeatYIncrement) {
						XmlImagePixel pixel = new XmlImagePixel();
						pixel.setX(x+i);
						pixel.setY(y+j);
						pixel.setRgbColor(rgbColor);
						
						xmlImage.setPixel(pixel);					
					}
				}
			}
		}
	}
	
	public static void main(String args[]) {
		String testData = "<!-- image document that is 1920 by 1080 pixels with a black background (RGB=(0,0,0)) !-->\r\n"
				+ "<image width=\"20\" height=\"11\" backgroundColor=\"0,0,0\">\r\n"
				+ "	<!-- draw a blue horizontal line that is 20 pixels long -->\r\n"
				+ "	<element x=\"0\" y=\"0\" quantity=\"20\" rgb=\"0,0,255\"/>\r\n"
				+ "	<!-- draw a 10 px by 10 px red box with 5 px of blue on each side -->\r\n"
				+ "        <repeatY increment=\"1\" quantity=\"10\">\r\n"
				+ "		<element x=\"0\" y=\"1\" quantity=\"5\" rgb=\"0,0,255\"/>\r\n"
				+ "		<element x=\"5\" y=\"1\" quantity=\"10\" rgb=\"255,0,0\"/>\r\n"
				+ "		<element x=\"15\" y=\"1\" quantity=\"5\" rgb=\"0,0,255\"/>\r\n"
				+ "        </repeatY>	\r\n"
				+ "</image>";
		
		XmlImageReader xmlImageReader = new XmlImageReader(testData);
		XmlImage testImage = xmlImageReader.getXmlImage();
		logger.info("XML Form of Data: "+testImage.getXMLFormOfImage());		
		testImageToXmlString();		
	}
	
	public static void testImageToXmlString() {
		XmlImage imageTest = new XmlImage(10, 10, new RgbColor(0,0,0));
		imageTest.setPixel(new XmlImagePixel(0,5,new RgbColor(0,255,0)));
		imageTest.setPixel(new XmlImagePixel(5,5,new RgbColor(0,0,255)));
		imageTest.setPixel(new XmlImagePixel(8,5,new RgbColor(125,125,125)));
		imageTest.setPixel(new XmlImagePixel(9,5,new RgbColor(0,255,0)));
		logger.info("XML Form of Data: "+imageTest.getXMLFormOfImage());		
	}
	
	

}
