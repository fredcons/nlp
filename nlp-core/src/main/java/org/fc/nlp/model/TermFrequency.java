package org.fc.nlp.model;

public class TermFrequency implements Comparable<TermFrequency> {
	
	private long frequency;
	private double normalizedFrequency;
	private double probability;
	
	public TermFrequency(long frequency) {
		this.frequency = frequency;
	}
	
	public void addFrequency(long frequency) {
		this.frequency += frequency;
	}
	
	@Override
	public int compareTo(TermFrequency otherTermFrequency) {
		return this.frequency > otherTermFrequency.frequency ? -1 : 1;
	}
	
	public long getFrequency() {
		return frequency;
	}
	
	public long getSquaredFrequency() {
		return frequency * frequency;
	}
	
	public double getSquaredNormalizedFrequency() {
		return normalizedFrequency * normalizedFrequency;
	}
	
	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}
	
	public double getNormalizedFrequency() {
		return normalizedFrequency;
	}
	
	public void setNormalizedFrequency(double normalizedFrequency) {
		this.normalizedFrequency = normalizedFrequency;
	}

    /**
     * @return the probability
     */
    public double getProbability() {
        return this.probability;
    }

    /**
     * @param probability the probability to set
     */
    public void setProbability(double probability) {
        this.probability = probability;
    }
	
	

}
