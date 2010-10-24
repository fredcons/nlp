package org.fc.nlp.model;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

public enum SimilarityComputationMethod {

	COSINE_BASED_SIMILARITY() {
		@Override
		@SuppressWarnings("unchecked")
		public double computeSimilarity(TermAnalysis termAnalysis, TermAnalysis otherTermAnalysis) {
			Collection<Term> commonTerms = CollectionUtils.intersection(termAnalysis.getFrequencies().keySet(), otherTermAnalysis.getFrequencies().keySet());
			double dotProduct = 0;
			for (Term term : commonTerms) {
			    double thisValue = termAnalysis.getFrequencies().get(term) != null ? termAnalysis.getFrequencies().get(term).getNormalizedFrequency() : 0.0;
			    double otherValue = otherTermAnalysis.getFrequencies().get(term) != null ? otherTermAnalysis.getFrequencies().get(term).getNormalizedFrequency() : 0.0;		    
			    dotProduct += thisValue * otherValue;
			}
			return dotProduct;
		}
	}, 
	
	CORRELATION_BASED_SIMILARITY() {
	    @Override
	    public double computeSimilarity(TermAnalysis termAnalysis, TermAnalysis otherTermAnalysis) {
	        return 0;
	    }
	},
	
	ADJUSTED_COSINE_BASED_SIMILARITY() {
        @Override
        public double computeSimilarity(TermAnalysis termAnalysis, TermAnalysis otherTermAnalysis) {
            return 0;
        }
    }
	;
	
	public abstract double computeSimilarity(TermAnalysis termAnalysis, TermAnalysis otherTermAnalysis);
}
