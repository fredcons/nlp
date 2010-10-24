package org.fc.nlp.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class StreamDocument extends BaseDocument {

	InputStream stream;
	
	public StreamDocument(InputStream stream) {
		this.stream = stream;
	}
	
	@Override
	public Reader getReader() {
		return new BufferedReader(new InputStreamReader(stream));
	}
	
	@Override
	public String getIdentifier() {
		return "Anonymous";
	}

}
