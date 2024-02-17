package data.xmlImageFormat;

import java.awt.image.Raster;
import java.io.StringWriter;
import java.util.ArrayList;

import compression.CompressionUtil;
import data.Attribute;
import data.XmlStringUtil;

// The following class is useful for being able to visual and see image data easily
// What is the problem with looking at the sun? Hard to visual the rest of what is going on in the sky
// Looking at a binary file that is hard to decipher, that makes seeing what is happening difficult
// could be likened to looking at the sun, obfuscates the rest of what is going on in the image.
//
// Pros to this image format
//   1. Has the potential to be loss less
//   2. Has the potential to grow with greater bit depth requirements (255,255,255) could easily be switched to (2550,2550,2550)
//   3. Human readable, although the use is a bit limited
//   4. Clear code examples of run length encoding, and why background data is so valuable (plan on providing this code free)
//   5. Potential for verifying column and row information (a small corruption potentially more recoverable as location data
//       is maintained in more than a sequential read
//   6. Redundant data zips, compresses well
//   7. Human readable equals potential for more modification
//   8. Potentially useful for showing small video examples, 10 by 10 pixels times 10 frames not outside of reasonable
//       for improving comprehension
//   9. Implementation is free, and thus ability to modify and change to better suit needs is powerful, requirement to
//       deliver full implementation for other standards might be significant time losses
//   10. Has a pros and cons list, ability to see the pros and cons of a technology factor into what people consider as
//       useful, has potential to show and highlight what is missed here.  Knowledge about what something is lacking many
//       times factors into Feedback, and Feedback builds innovation.
//   11. Can be filled via Java AWT Raster, load JPG and convert a simple process
//   12. Convenience method for zipping, compressed message format useful for app that sends over network
//
//
// Cons to this image format
//   1. There is allot of redundant data, <element could easily be reduced to <e to save space
//   2. Human readable equals potential for more modification
//   3. Files are big, does not factor in imperceptibility to save space (lossy compression exists for a reason)
//   4. Does not currently allow drawing outside of pixel level, not a replacement for SVG
//
public class XmlImage {	
	int width;
	int height;
	RgbColor backgroundColor;
	XmlImagePixel pixels[][] ;
	
	public XmlImage(final int width, final int height, final RgbColor backgroundColor) {
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;		
		fillBackgroundData();
	}
	
	private void fillBackgroundData() {
		pixels = new XmlImagePixel[this.width][this.height];
		
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				XmlImagePixel pixel = new XmlImagePixel();
				pixel.setX(x);
				pixel.setY(y);
				pixel.setRgbColor(backgroundColor);
				pixels[x][y] = pixel; 
			}
		}
	}
	
	public XmlImage(Raster raster) {
		this.width = raster.getWidth();
		this.height = raster.getHeight();
		this.backgroundColor = new RgbColor(0,0,0);
		fillBackgroundData();
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				int pixel[] = new int[3];
				raster.getPixel(x, y, pixel);
				XmlImagePixel xmlImagePixel = new XmlImagePixel(x,y,new RgbColor(pixel[0], pixel[1], pixel[2]));
				setPixel(xmlImagePixel);
			}
		}
	}
	
	public void setPixel(XmlImagePixel xmlImagePixel) {
		pixels[xmlImagePixel.getX()][xmlImagePixel.getY()] = xmlImagePixel; 
	}
	
	public XmlImagePixel getPixel(int x, int y) {
		return pixels[x][y];
	}
	
	private ArrayList<ImageElement> getImageElementsXRunlengthEncoding() {
		ArrayList<ImageElement> imageElements = new ArrayList<>();
		
		RgbColor currentColor = null;
		for (int y=0; y<height; y++) {
			int quantity=1;
			for (int x=0; x<width; x++) {
				XmlImagePixel pixel = pixels[x][y];
				
				if (x==0) {
					currentColor=pixel.getRgbColor();
				} else {								
					boolean colorChanged = !pixel.getRgbColor().toString().equals(currentColor.toString());
					int xOffset;
					
					
					if (colorChanged) {			
						xOffset = pixel.getX()-quantity;							 
						imageElements.add(new ImageElement(xOffset, pixel.getY(), currentColor, quantity));
						
						// Set new color and quantity to 1
						quantity=1;
						currentColor = pixel.getRgbColor();
					} else {
						quantity++;					
					}				

					if (x==pixels.length-1) {	
						xOffset = pixel.getX()+1-quantity;							 
						imageElements.add(new ImageElement(xOffset, pixel.getY(), currentColor, quantity));							
					}					
				}
			}
		}
		
		return imageElements;
	}
	
	private String generateRepeatYSet(ImageElement previousElement, int repeatQuantity) {
		StringWriter writer = new StringWriter();
		ArrayList<Attribute> ySetAttributes = new ArrayList<>();
		ySetAttributes.add(new Attribute("quantity", ""+repeatQuantity));
		ySetAttributes.add(new Attribute("increment", ""+1));
		writer.append(XmlStringUtil.createElement("RepeatY", ySetAttributes, false));
		
		ArrayList<Attribute> elementAttributes = new ArrayList<>();
		elementAttributes.add(new Attribute("x",""+previousElement.getX()));
		elementAttributes.add(new Attribute("y",""+(previousElement.getY()-repeatQuantity+1)));
		elementAttributes.add(new Attribute("rgb",""+previousElement.rgbColor.toString()));
		elementAttributes.add(new Attribute("quantity",""+previousElement.quantity));
		writer.append("  "+XmlStringUtil.createElement("element", elementAttributes, true));
		
		writer.append(XmlStringUtil.createEndElement("RepeatY"));
		return writer.toString();
	}
	
	private String generateImageElementXmlString(ImageElement previousElement) {
		StringWriter writer = new StringWriter();
		ArrayList<Attribute> elementAttributes = new ArrayList<>();
		elementAttributes.add(new Attribute("x",""+previousElement.getX()));
		elementAttributes.add(new Attribute("y",""+previousElement.getY()));
		elementAttributes.add(new Attribute("rgb",""+previousElement.rgbColor.toString()));
		elementAttributes.add(new Attribute("quantity",""+previousElement.quantity));
		writer.append(XmlStringUtil.createElement("element", elementAttributes, true));
		return writer.toString();
	}
	
	public byte [] getZippedXmlImage() {
		return CompressionUtil.zipString(getXMLFormOfImage());
	}
	
	public String getXMLFormOfImage() {
		StringWriter writer = new StringWriter();
		ArrayList<Attribute> imageAttributes = new ArrayList<>();
		imageAttributes.add(new Attribute("width", ""+width));
		imageAttributes.add(new Attribute("height", ""+height));
		imageAttributes.add(new Attribute("backgroundColor", backgroundColor.toString()));
		writer.append(XmlStringUtil.createElement("image", imageAttributes, false));
		
		
		// Added Run-length Encoding (quantity) (compare each x on same y and if rgbColor is same group and set quantity field)
		// TODO: needs to be tested, verified
		
		// x, horizontal run lenth encoding, Start of Code, stores horizontal elements with their quantities, 
		// actual write out to xml format occurs in y, vertical run length encoding below
		ArrayList<ImageElement> imageElements = getImageElementsXRunlengthEncoding();
		// x, horizontal run length encoding, End of Code
				
		// y, vertical run length encoding, Start of Code, only writes elements if different than background color
		ImageElement previousElement = null;
		int repeatQuantity=1;
		for (int i=0; i<imageElements.size(); i++) {
			ImageElement currentElement = imageElements.get(i);
			if (null==previousElement) {
				previousElement=currentElement;
				repeatQuantity=1;
			} else if (currentElement.isEqualOutsideOfYValueIncrement(previousElement)) {
				repeatQuantity++;
				previousElement=currentElement;
				
				if (i==imageElements.size()-1) { // Last Element is special case (no future element to compare to)
					if (repeatQuantity>1) {
						// Only write if different than background color
						if (!previousElement.rgbColor.toString().equals(backgroundColor.toString())) {
							writer.append(generateRepeatYSet(previousElement, repeatQuantity));
						}
					} else {
						// Only write if different than background color
						if (!previousElement.rgbColor.toString().equals(backgroundColor.toString())) {
							writer.append(generateImageElementXmlString(previousElement));
						}
					}
				}				
			} else {
				// More than Y value increment is different, write as repeats if quantity greater than 1
				if (repeatQuantity>1) {
					// Only write if different than background color
					if (!previousElement.rgbColor.toString().equals(backgroundColor.toString())) {
						writer.append(generateRepeatYSet(previousElement, repeatQuantity));
					}
				} else {
					// Only write if different than background color
					if (!previousElement.rgbColor.toString().equals(backgroundColor.toString())) {
						writer.append(generateImageElementXmlString(previousElement));
					}
				}
				
				repeatQuantity=1;
				previousElement=currentElement;
			}
		}
		// y, vertical run length encoding, End of Code
		
		writer.append(XmlStringUtil.createEndElement("image"));
		return writer.toString();
	}
	
	class ImageElement {
		int x;
		int y;
		RgbColor rgbColor;
		int quantity;
		
		public ImageElement(final int x, final int y, final RgbColor rgbColor, final int quantity) {
			this.x = x;
			this.y = y;
			this.rgbColor = rgbColor;
			this.quantity = quantity;
		}
		
		public boolean isEqualOutsideOfYValueIncrement(ImageElement imageElement) {
			return imageElement.getX() == x 
					&& imageElement.getRgbColor().toString().equals(rgbColor.toString()) 
					&& imageElement.getQuantity() == quantity
					&& imageElement.getY()+1 == y;
		}
		
		
		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public RgbColor getRgbColor() {
			return rgbColor;
		}

		public int getQuantity() {
			return quantity;
		}
	}
}
