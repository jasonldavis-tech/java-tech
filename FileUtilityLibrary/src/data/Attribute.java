package data;

public class Attribute {
	public Attribute(final String qualifiedName, final String value) {
		this.qualifiedName = qualifiedName;
		this.value = value;
	}

	public String getQualifiedName() {
		return qualifiedName;
	}
	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	private String qualifiedName;
	private String value;
}
