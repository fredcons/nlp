package org.fc.nlp.web.model;

public abstract class AbstractResponse {
	
	ResponseMetadata responseMetadata;

	public ResponseMetadata getResponseMetadata() {
		if (responseMetadata == null) {
			responseMetadata = new ResponseMetadata();
		}
		return responseMetadata;
	}

	public void setResponseMetadata(ResponseMetadata responseMetadata) {
		this.responseMetadata = responseMetadata;
	}

}
