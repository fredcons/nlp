package org.fc.nlp.web.model;

import java.io.IOException;
import java.net.URL;

import org.springframework.web.multipart.MultipartFile;

public class TextExtractionRequest extends AbstractRequest {
	
	private String text;
	private MultipartFile file;
	private URL url;
	private String mimeType;
	
	public void setFormat(String format) {
		this.format = format;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	
	public byte[] getFileAsBytes() {
	    byte[] result = null;
	    if (file != null) {
    	    try {
    	        result = file.getBytes();
            } catch (IOException ioe) {
                LOGGER.error("Error while extracting bytes", ioe);
            }
	    }    
        return result;
    }
	
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public URL getUrl() {
		return url;
	}
	
	public void setUrl(URL url) {
		this.url = url;
	}
	
	public boolean isJson() {
		return "json".equals(format);
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
