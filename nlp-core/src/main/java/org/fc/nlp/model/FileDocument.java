package org.fc.nlp.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileDocument extends EncodedDocument {

	String fileLocation;
	
	public FileDocument(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	public FileDocument(String fileLocation, String encoding) {
		this.fileLocation = fileLocation;
		this.encoding = encoding;
	}
	
	@Override
	public String getIdentifier() {
		return fileLocation;
	}
	
	@Override
	public Reader getReader() {
		Reader reader = null;
		try {
			File document = new File(fileLocation);
			if (document.exists()) {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(document), charset)); 
			} else {
				System.out.println("Document " + document + " does not exist");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		return reader;
	}
	
}
