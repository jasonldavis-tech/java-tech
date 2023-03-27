package data;

public class BooleanMatrix {
    boolean matrix[][];
	
	public int getWidth() {
		return matrix.length;
	}
	
	public int getHeight() {
		return matrix[0].length;
	}	
	
	public BooleanMatrix(int width, int height, boolean defaultValue) {
		matrix = new boolean[width][height];
	}
	
	public boolean getElement(int i, int j) {
		return matrix[i][j];
	}
	
	public void setElement(int i, int j, boolean element) {
		matrix[i][j]=element;
	}
}
