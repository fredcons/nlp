package org.fc.nlp.services.impl;

import org.apache.log4j.Logger;
import org.fc.nlp.services.TermAnalyzer;
import org.fc.nlp.model.Category;
import org.fc.nlp.model.Corpus;
import org.fc.nlp.model.Document;
import org.fc.nlp.model.TermAnalysis;
import org.fc.nlp.model.TermAnalysisType;
import org.springframework.util.StopWatch;

public abstract class AbstractTermAnalyzerImpl implements TermAnalyzer {
	
	private static final Logger LOGGER = Logger.getLogger(AbstractTermAnalyzerImpl.class);
		
	public TermAnalysis analyze(Category category) {
		TermAnalysis categoryTermAnalysis = createAnalysis();				
		for (Document document : category.getDocuments()) {
			categoryTermAnalysis.merge(analyze(document));
		}		
		return categoryTermAnalysis;
	}
	
	public TermAnalysis analyze(Document document) {
		return analyzeInternal(document);
	}
	
	/**
	 * this should be defined by subclasses to fit particular analysis requirements : ngrams, words, shingles, etc...
	 * @param document
	 * @return
	 */
	protected abstract TermAnalysis analyzeInternal(Document document);
	
	/**
	 * this should be defined by subclasses to fill analysis-specific fields  
	 * @return
	 */
	protected TermAnalysis createAnalysis() {
        TermAnalysis termAnalysis = new TermAnalysis();
        termAnalysis.setType(providedType());
        return termAnalysis;
    }
	
	/**
	 * retursn the provided analysis type
	 * @return
	 */
	public abstract TermAnalysisType providedType();
	
	@Override
	public void analyze(Corpus corpus) {
		StopWatch sw = new StopWatch();
		sw.start();
		if (corpus != null && corpus.getCategories() != null) {
			for (Category category : corpus.getCategories()) {
				if (category.getTermAnalysises().isEmpty()) {
					// if no analysis is specified, we have to compute one from the documents
					TermAnalysis categoryTermAnalysis = this.analyze(category);
					categoryTermAnalysis.postProcess();
					LOGGER.debug(categoryTermAnalysis.toString());
					category.addTermAnalysis(categoryTermAnalysis);
				} else if (!category.getTermAnalysises().isEmpty()) {
					LOGGER.debug(category.getTermAnalysises().toString());
				}
			}
		}
		sw.stop();
		LOGGER.info("Corpus analysis took " + sw.getLastTaskTimeMillis() + " ms");
		
	}

}
