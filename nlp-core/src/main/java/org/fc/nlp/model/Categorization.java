package org.fc.nlp.model;

/**
 * Categorization
 * Created on 9 févr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 50 $
 * $Id: Categorization.java 50 2010-03-20 22:22:47Z fredcons $
 */
public class Categorization implements Comparable<Categorization> {
    
    protected Category category;
    protected double similarity;
    
    /**
     * 
     */
    public Categorization() {}
    
    /**
     * 
     */
    public Categorization(Category category, double similarity) {
        this.category = category;
        this.similarity = similarity;
    }
    
    /**
     * @return the category
     */
    public Category getCategory() {
        return this.category;
    }
    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }
    /**
     * @return the similarity
     */
    public double getSimilarity() {
        return this.similarity;
    }
    /**
     * @param similarity the similarity to set
     */
    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (category != null) {
        	sb.append(category.getName()).append(" = ").append(similarity);
        } else {
        	sb.append("N/A");
        }
        return sb.toString();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Categorization otherCategorization) {
        return this.similarity < otherCategorization.similarity ? 1 : -1;
    }
    

}
