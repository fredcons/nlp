package org.fc.nlp.web.support.spring;

import java.beans.PropertyEditorSupport;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class StringToUrlPropertyEditor extends PropertyEditorSupport {
	
	private static final Logger LOGGER = Logger.getLogger(StringToUrlPropertyEditor.class); 
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
			    if (!text.startsWith("http")) {
			        text = "http://" + text;
			    }
				setValue(new URL(text));
			} catch (Exception e) {
				LOGGER.error("Error parsing " + text + " as url", e);
			}       
		}	
	}
	public String getAsText() {
		URL url = (URL) getValue();
		return url != null ? url.toString() : null;
	}

}
