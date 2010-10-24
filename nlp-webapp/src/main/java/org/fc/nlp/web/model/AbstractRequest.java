package org.fc.nlp.web.model;

import org.apache.log4j.Logger;

public class AbstractRequest {
    
    protected static final Logger LOGGER = Logger.getLogger(AbstractRequest.class); 
	
	protected String format;
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
