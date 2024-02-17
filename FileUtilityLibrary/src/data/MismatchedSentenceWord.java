package data;

import java.io.StringWriter;

public class MismatchedSentenceWord {
	private String word;
	private String wordBefore;
	private String wordAfter;
	private int location;

	@Override
	public String toString() {
		return "MismatchedSentenceWord [word=" + word + ", wordBefore=" + wordBefore + ", wordAfter=" + wordAfter
				+ ", location=" + location + "]";
	}

	public MismatchedSentenceWord(final String word, final String wordBefore, final String wordAfter, int location) {
		this.word = word;
		this.wordBefore = wordBefore;
		this.wordAfter = wordAfter;
		this.location = location;
	}
	
	public String getAsHTMLString() {
		final StringWriter stringWriter = new StringWriter();
		
		stringWriter.append(this.wordBefore);
		stringWriter.append(" <b>");
		stringWriter.append(this.word);
		stringWriter.append("</b> ");
		stringWriter.append(this.wordAfter);
		
		return stringWriter.toString();		
	}
	
	public int getLocation() {
		return location;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWordBefore() {
		return wordBefore;
	}

	public void setWordBefore(String wordBefore) {
		this.wordBefore = wordBefore;
	}

	public String getWordAfter() {
		return wordAfter;
	}

	public void setWordAfter(String wordAfter) {
		this.wordAfter = wordAfter;
	}
}
