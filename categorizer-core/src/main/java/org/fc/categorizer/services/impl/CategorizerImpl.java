package org.fc.categorizer.services.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.fc.categorizer.services.Categorizer;
import org.fc.extractor.services.TextExtractor;
import org.fc.nlp.services.TermAnalyzer;
import org.fc.nlp.model.Categorization;
import org.fc.nlp.model.Category;
import org.fc.nlp.model.Corpus;
import org.fc.nlp.model.Document;
import org.fc.nlp.model.StringDocument;
import org.fc.nlp.model.TermAnalysis;
import org.springframework.util.StopWatch;

public class CategorizerImpl implements Categorizer {
	
	static final Logger LOGGER = Logger.getLogger(CategorizerImpl.class);
	
	private Corpus reference;
	private TermAnalyzer analyzer;
	private TextExtractor textExtractor;
	
	@PostConstruct
	public void initialize() {
		analyzer.analyze(reference);
	}
	
	@Override
	public Categorization categorize(Document document) {
	    Categorization categorization = new Categorization();
		TermAnalysis termAnalysis = analyzer.analyze(document);
		termAnalysis.postProcess();
		LOGGER.debug(termAnalysis.toString());
		StopWatch sw = new StopWatch();
		sw.start();
		double highScore = 0;
		for (Category category : reference.getCategories()) {
			double currentScore  = termAnalysis.computeSimilarity(category.getTermAnalysis(termAnalysis.getType()));
			LOGGER.debug("Category " + category.getName() + "  has a score of " + currentScore);
			if (currentScore > highScore) {
			    categorization.setSimilarity(currentScore);
			    categorization.setCategory(category);
			    highScore = currentScore;
			}
		}
		sw.stop();
		LOGGER.info("categorization took " + sw.getLastTaskTimeMillis() + " ms");
		return categorization;
	}
	
	@Override
	public List<Categorization> explain(Document document) {
		TermAnalysis termAnalysis = analyzer.analyze(document);
		termAnalysis.postProcess();
		List<Categorization> categorizations = new ArrayList<Categorization>();
		for (Category category : reference.getCategories()) {
			double score = termAnalysis.computeSimilarity(category.getTermAnalysis(termAnalysis.getType()));
			categorizations.add(new Categorization(category, score));
		}	
		Collections.sort(categorizations);
		return categorizations;
	}
	
	@Override
	public List<Categorization> explain(InputStream stream) {
		Document document = textExtractor.extractDocument(stream);
		return explain(document);
	}
	
	@Override
	public List<Categorization> explain(URL url) {
		Document document = textExtractor.extractDocument(url);
		return explain(document);
	}
	
	@Override
	public List<Categorization> explain(String text) {
		return explain(new StringDocument(text));
	}
	
	@Override
	public Categorization categorize(String text) {
		return categorize(new StringDocument(text));
	}
	
	@Override
	public Categorization categorize(InputStream stream) {
		Document document = textExtractor.extractDocument(stream);
		return categorize(document);
	}
	
	@Override
	public Categorization categorize(File file) {
		Document document = textExtractor.extractDocument(file);
		return categorize(document);
	}
	
	@Override
	public Categorization categorize(URL url) {
		Document document = textExtractor.extractDocument(url);
		return categorize(document);
	}

	public void setReference(Corpus reference) {
		this.reference = reference;
	}
	
	public void setAnalyzer(TermAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

	public void setTextExtractor(TextExtractor textExtractor) {
		this.textExtractor = textExtractor;
	}
}
