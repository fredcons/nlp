package org.fc.nlp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class UrlDocument extends BaseDocument {

	URL url;
	
	public UrlDocument(URL url) {
		this.url = url;
	}
	
	@Override
	public Reader getReader() {
		Reader reader = null;
	    try {
	        reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	    return reader;
	}
	
	@Override
	public String getIdentifier() {
		return url.toExternalForm();
	}

}
