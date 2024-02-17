package data.xmlImageFormat;

public class XmlImagePixel {
	public RgbColor getRgbColor() {
		return rgbColor;
	}

	public void setRgbColor(RgbColor rgbColor) {
		this.rgbColor = rgbColor;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private RgbColor rgbColor;
	private int x;
	private int y;
	
	public XmlImagePixel(final int x, final int y, final RgbColor rgbColor) {
		this.x = x;
		this.y = y;
		this.rgbColor = rgbColor;
	}
		
	public XmlImagePixel() {
	}

}
