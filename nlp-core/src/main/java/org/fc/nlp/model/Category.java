package org.fc.nlp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Category {
	
	static final Logger LOGGER = Logger.getLogger(Category.class);

	String name;
	List<Document> documents;
	Map<TermAnalysisType, TermAnalysis> termAnalysises = new HashMap<TermAnalysisType, TermAnalysis>();
	
	public Category() {		
	}
	
	public Category(String name) {
		this.name =  name;
		this.documents = new ArrayList<Document>(); 
	}
		
	public Category(String name, List<TermAnalysis> termAnalysisesList) {
        this.name =  name;
        for (TermAnalysis analysis : termAnalysisesList) {
            this.addTermAnalysis(analysis);
        }
        for (TermAnalysis termAnalysis : termAnalysises.values()) {
            LOGGER.info("Post processing analysis with type " + termAnalysis.getType());
            termAnalysis.postProcess();
        }
    }

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Map<TermAnalysisType, TermAnalysis> getTermAnalysises() {
		return termAnalysises;
	}
	
	public TermAnalysis getTermAnalysis(TermAnalysisType type) {
        return termAnalysises.get(type);
    }
	
	public void addTermAnalysis(TermAnalysis termAnalysis) {
	    termAnalysises.put(termAnalysis.getType(), termAnalysis);
	}

}
