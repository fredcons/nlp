package org.fc.nlp.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fc.nlp.model.Term;
import org.fc.nlp.model.TermFrequency;

public class AnalysisHelper {
	
	public static Map<Term, TermFrequency> sortByValues(Map<Term, TermFrequency> map) {
		
		List<Term> terms = new ArrayList<Term>(map.keySet());
		Collections.sort(terms, new TermFrequencyComparator(map));
		
		Map<Term, TermFrequency> sortedByValues = new LinkedHashMap<Term, TermFrequency>();
		for (Term term : terms) {
			sortedByValues.put(term, map.get(term));
		}
		return sortedByValues;
	}
	
	public static String dumpReader(Reader reader) {
	    StringBuilder sb = new StringBuilder();
	    LineNumberReader nbp = new LineNumberReader(reader);
	    try {
    	    String line = null;
    	    while ((line = nbp.readLine()) != null) {
    	        sb.append(line.trim());
    	    }
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	    return sb.toString();
	}

}
