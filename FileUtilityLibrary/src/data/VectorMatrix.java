package data;

import java.util.Vector;

public class VectorMatrix<T> {
	private Vector<Vector<T>> matrix;
	
	public int getWidth() {
		return matrix.size();
	}
	
	public int getHeight() {
		return matrix.get(0).size();
	}	
	
	public VectorMatrix(int width, int height, T defaultValue) {
		matrix = new Vector<>(width);
		for (int i=0; i<width; i++) {
			matrix.add(i,new Vector<T>(height));
			for (int j=0; j<height; j++) {
				matrix.get(i).add(defaultValue);
			}
		}
	}
	
	public T getElement(int i, int j) {
		return matrix.get(i).get(j);
	}
	
	public void setElement(int i, int j, T element) {
		matrix.get(i).set(j, element);
	}
	
	public boolean areAllRowsEqual() {
		boolean allRowsEqual=true;
		for (int i=0; i<getWidth(); i++) {
			for (int j=0; j<getWidth(); j++) {
				if (!matrix.get(i).equals(matrix.get(j))) {
					allRowsEqual=false;
				}
			}
		}
		return allRowsEqual;
	}
	
	public boolean areSomeRowsDifferent() {
		return !areAllRowsEqual();
	}
}
