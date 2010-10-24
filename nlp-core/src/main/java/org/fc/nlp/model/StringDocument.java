package org.fc.nlp.model;

import java.io.Reader;
import java.io.StringReader;

public class StringDocument extends BaseDocument {

	String content;
	
	public StringDocument(String content) {
		this.content = content;
	}
	
	@Override
	public Reader getReader() {
		return new StringReader(content);
	}
	
	@Override
	public String getIdentifier() {
		return "Anonymous";
	}
}
