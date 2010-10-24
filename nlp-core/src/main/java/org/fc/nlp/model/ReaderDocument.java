package org.fc.nlp.model;

import java.io.Reader;

public class ReaderDocument extends BaseDocument {

	Reader reader;
	
	public ReaderDocument(Reader reader) {
		this.reader = reader;
	}
	
	@Override
	public Reader getReader() {
		return reader;
	}
	
	@Override
	public String getIdentifier() {
		return "Anonymous";
	}

}
