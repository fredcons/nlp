package org.fc.nlp.util;

import java.util.Comparator;
import java.util.Map;

import org.fc.nlp.model.Term;
import org.fc.nlp.model.TermFrequency;

public class TermFrequencyComparator implements Comparator<Term> {
	
	Map<Term, TermFrequency> sortableMap;
	
	public TermFrequencyComparator(Map<Term, TermFrequency> initialMap) {
		sortableMap = initialMap;
	}
	
	@Override
	public int compare(Term t1, Term t2) {
        int compare = sortableMap.get(t2).compareTo(sortableMap.get(t1));
        return - compare;
	}
	
}
