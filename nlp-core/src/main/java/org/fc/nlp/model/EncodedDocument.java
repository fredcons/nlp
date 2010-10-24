package org.fc.nlp.model;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

public abstract class EncodedDocument extends BaseDocument {
	
	protected static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
	
	protected String encoding;
	protected Charset charset;
	
	@PostConstruct
	protected void init() {
		if (encoding != null) {
			try {
				LOGGER.debug("Using charset " + encoding);
				charset = Charset.forName(encoding);
			} catch (Exception e) {
				LOGGER.error("Could not build charset for encoding " + encoding, e);
			}
		} 
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
	}


	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	

}
