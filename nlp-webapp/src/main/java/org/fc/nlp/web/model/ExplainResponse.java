package org.fc.nlp.web.model;

import java.util.List;

import org.fc.nlp.model.Categorization;

public class ExplainResponse extends AbstractResponse {
	
	List<Categorization> categorizations;
	
	public ExplainResponse() {		
	}
	
	public ExplainResponse(long executionTime, List<Categorization> categorizations) {
		this.getResponseMetadata().setExecutionTime(executionTime);
		this.categorizations = categorizations;
	}

	public List<Categorization> getCategorizations() {
		return categorizations;
	}

	public void setCategorizations(List<Categorization> categorizations) {
		this.categorizations = categorizations;
	}

}
