package org.fc.nlp.services;

import org.fc.nlp.model.Category;
import org.fc.nlp.model.Corpus;
import org.fc.nlp.model.Document;
import org.fc.nlp.model.TermAnalysis;

public interface TermAnalyzer {
	
	TermAnalysis analyze(Category category);
	
	TermAnalysis analyze(Document document);
	
	void analyze(Corpus corpus);

}
