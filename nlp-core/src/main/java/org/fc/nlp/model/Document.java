package org.fc.nlp.model;

import java.io.Reader;

import org.apache.log4j.Logger;

public interface Document {
	
	final static Logger LOGGER = Logger.getLogger(Document.class); 

	Reader getReader();

	String getIdentifier();
	
	String getContentAsString();
	
}
