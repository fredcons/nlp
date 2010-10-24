package org.fc.nlp.web.model;

import org.fc.nlp.model.Categorization;

public class CategorizationResponse extends AbstractResponse {
	
	Categorization categorization;
	
	public CategorizationResponse() {		
	}
	
	public CategorizationResponse(long executionTime, Categorization categorization) {
		this.getResponseMetadata().setExecutionTime(executionTime);
		this.categorization = categorization;
	}

	public Categorization getCategorization() {
		return categorization;
	}

	public void setCategorization(Categorization categorization) {
		this.categorization = categorization;
	}
}
