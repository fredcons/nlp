package org.fc.nlp.model;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class BaseDocument implements Document {
	
	@Override
	public String getContentAsString() {
		StringBuilder content = new StringBuilder();
		if (getReader() != null) {
			String line;
			try {
			    BufferedReader reader = new BufferedReader(getReader());
			    while ((line = reader.readLine()) != null) {
			    	content.append(line).append("\n");
			    }
			} catch (IOException ioe) {
				LOGGER.error("Error while dumping content", ioe);
			}
		}
		return content.toString();
	}

}
