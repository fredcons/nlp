package org.fc.nlp.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fc.nlp.util.AnalysisHelper;
import org.fc.nlp.util.Constants;


public class TermAnalysis {
	
    static final Logger LOGGER = Logger.getLogger(TermAnalysis.class);
    
	protected Map<Term, TermFrequency> termFrequencies;
	protected TermAnalysisType type;
	protected int topTerms;
	
	public TermAnalysis() {
	    this.termFrequencies = new HashMap<Term, TermFrequency>();
	}
	
	public TermAnalysis(File file, TermAnalysisType type, int topTerms) {
	    this.termFrequencies = new HashMap<Term, TermFrequency>();
	    this.type = type;
	    this.topTerms = topTerms;
	    try {
            LineNumberReader termAnalysisReader = new LineNumberReader(new FileReader(file));
            String line = "";
            int counter = 0;
            while ((line = termAnalysisReader.readLine()) != null && (topTerms == 0 || (topTerms > 0 && counter < topTerms))) {
                String[] keyValuePair = line.split("=");
                String term = keyValuePair[0];
                Long frequency = Long.valueOf(keyValuePair[1]);
                this.addTerm(new Term(term), new TermFrequency(frequency));
                LOGGER.debug("Adding term " + term + " with freq " + frequency);
                counter++;
            }
            LOGGER.info("Analysis loaded with size " + this.getFrequencies().keySet().size());              
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }        
	}
	
	public TermAnalysisType getType() {
		return type;
	}

	public void setType(TermAnalysisType type) {
		this.type = type;
	}

	public Map<Term, TermFrequency> getFrequencies() {
		return termFrequencies;
	}
	
	public void addTerm(Term term) {
		addTerm(term, new TermFrequency(1l));
	}
	
	public void addTerm(Term term, TermFrequency termFrequency) {
		TermFrequency tf = termFrequencies.get(term);
		if (tf == null) {
			termFrequencies.put(term, termFrequency);
		} else {
			tf.addFrequency(termFrequency.getFrequency());
		}
	}
	
	public void merge(TermAnalysis otherTermAnalysis) {
		for (Term term : otherTermAnalysis.getFrequencies().keySet()) {
			TermFrequency tf = otherTermAnalysis.getFrequencies().get(term);
			this.addTerm(term, tf);
		}
	}
	
	public double computeSimilarity(TermAnalysis otherTermAnalysis) {
		return computeSimilarity(otherTermAnalysis, SimilarityComputationMethod.COSINE_BASED_SIMILARITY);
	}
	
	public double computeSimilarity(TermAnalysis otherTermAnalysis, SimilarityComputationMethod similarityComputationMethod) {
		return similarityComputationMethod.computeSimilarity(this, otherTermAnalysis);
	}
	
	private void normalize() {
		// after an analysis is complete, we normalize the results,
		// so that the sum of all squared elements equals 1
		double sumSquared = 0;
		for (TermFrequency tf : termFrequencies.values()) {
			sumSquared += (double) tf.getSquaredFrequency();
		}
		double normalizationFactor = Math.sqrt(sumSquared);
		for (TermFrequency tf : termFrequencies.values()) {
			tf.setNormalizedFrequency(((double) tf.getFrequency()) / normalizationFactor);
		}
	}
	
	private void computeProbabilities() {
	    double sumOfAllTerms = 0;
	    for (TermFrequency tf : termFrequencies.values()) {
	        sumOfAllTerms += tf.getFrequency();
	    }
	    for (TermFrequency tf : termFrequencies.values()) {
	        tf.setProbability(((double) tf.getFrequency()) / sumOfAllTerms);
	    }
	}
	
	public void postProcess() {
	    this.normalize();
	    this.computeProbabilities();	    
	}
	
	public double sumSquaredNormalizedItems() {
		double sum = 0;
		for (TermFrequency tf : termFrequencies.values()) {
			sum += tf.getSquaredNormalizedFrequency();
		}
		return sum;
	}
	
	public String asTermScores() {
		StringBuilder result = new StringBuilder();
		for (Term term : termFrequencies.keySet()) {
			result.append(term.getTerm())
			      .append("=")
			      .append(termFrequencies.get(term).getFrequency())
			      .append(Constants.NEW_LINE);
		}
		return result.toString();
	}
	
	public String asTopTermScores() {
		Map<Term, TermFrequency> sortedMap = AnalysisHelper.sortByValues(termFrequencies);
		StringBuilder result = new StringBuilder();
		int i = 0;
		for (Term term : sortedMap.keySet()) {
			result.append(term != null && term.getTerm() != null ? term.getTerm() : "null")
			      .append("=")
			      .append(sortedMap.get(term).getFrequency())
			      .append(Constants.NEW_LINE);
			i++;
			if (topTerms > 0 && i >= topTerms) {
				break;
			}
		}
		return result.toString();
	}
	
	public String asTopTermScores(int specificTermScores) {
        Map<Term, TermFrequency> sortedMap = AnalysisHelper.sortByValues(termFrequencies);
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (Term term : sortedMap.keySet()) {
            result.append(term != null && term.getTerm() != null ? term.getTerm() : "null")
                  .append("=")
                  .append(sortedMap.get(term).getFrequency())
                  .append(Constants.NEW_LINE);
            i++;
            if (specificTermScores > 0 && i >= specificTermScores) {
                break;
            }
        }
        return result.toString();
    }
	
	public String asProbabilities() {
		StringBuilder result = new StringBuilder();
		for (Term term : termFrequencies.keySet()) {
			result.append(term.getTerm())
			      .append("=")
			      .append(termFrequencies.get(term).getProbability())
			      .append(Constants.NEW_LINE);
		}
		return result.toString();
	}
	
	public String asNormalizedTermScores() {
        StringBuilder result = new StringBuilder();
        for (Term term : termFrequencies.keySet()) {
            result.append(term.getTerm())
                  .append("=")
                  .append(termFrequencies.get(term).getNormalizedFrequency())
                  .append(Constants.NEW_LINE);
        }
        return result.toString();
    }
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(Constants.NEW_LINE);
		result.append("Raw counters").append(Constants.NEW_LINE);
		result.append(this.asTermScores());
		result.append("Normalized counters").append(Constants.NEW_LINE);
		result.append(this.asNormalizedTermScores());
		result.append("Probabilities").append(Constants.NEW_LINE);
        result.append(this.asProbabilities());
        return result.toString();
	}

    /**
     * @return the topTerms
     */
    public int getTopTerms() {
        return this.topTerms;
    }

    /**
     * @param topTerms the topTerms to set
     */
    public void setTopTerms(int topTerms) {
        this.topTerms = topTerms;
    }
}
