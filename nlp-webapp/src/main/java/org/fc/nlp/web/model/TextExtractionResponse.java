package org.fc.nlp.web.model;


public class TextExtractionResponse extends AbstractResponse {
	
	String content;
	

	public TextExtractionResponse() {		
	}
	
	public TextExtractionResponse(long executionTime, String content) {
		this.getResponseMetadata().setExecutionTime(executionTime);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
