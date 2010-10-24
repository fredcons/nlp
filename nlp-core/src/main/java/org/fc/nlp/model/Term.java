package org.fc.nlp.model;

public class Term {

	private String term;
	
	public Term(String term) {
		if (term == null) {
			throw new IllegalStateException("A valid term should be provided");
		}
		this.term = term;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Term) {
			return this.term.equals(((Term) obj).getTerm());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return term.hashCode();
	}
	
	@Override
	public String toString() {
		return "Term : " + term;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
			
}
