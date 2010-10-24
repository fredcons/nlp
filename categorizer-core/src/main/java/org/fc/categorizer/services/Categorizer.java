package org.fc.categorizer.services;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.fc.nlp.model.Categorization;
import org.fc.nlp.model.Document;


public interface Categorizer {
	
    Categorization categorize(Document document);
	
	List<Categorization> explain(Document document);
	
	List<Categorization> explain(InputStream stream);
	
	List<Categorization> explain(URL url);
	
	List<Categorization> explain(String text);
	
	Categorization categorize(String text);
	
	Categorization categorize(InputStream stream);

	Categorization categorize(File file);
	
	Categorization categorize(URL url);
}
