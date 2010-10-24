package org.fc.nlp.services.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.shingle.ShingleFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;
import org.fc.nlp.model.Document;
import org.fc.nlp.model.Term;
import org.fc.nlp.model.TermAnalysis;
import org.fc.nlp.model.TermAnalysisType;
import org.springframework.util.StopWatch;

/**
 * WordAnalyzerImpl
 * Created on 18 févr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 40 $
 * $Id: ShingleAnalyzerImpl.java 40 2010-02-23 21:33:27Z fredcons $
 */
public class ShingleAnalyzerImpl extends AbstractTermAnalyzerImpl {
    
    private static final Logger LOGGER = Logger.getLogger(ShingleAnalyzerImpl.class);
    
    private int maxShingleSize;
    
    public ShingleAnalyzerImpl(int maxShingleSize) {
        this.maxShingleSize = maxShingleSize;
    }
    
    /* (non-Javadoc)
     * @see org.fc.categorizer.services.impl.AbstractTermAnalyzerImpl#providedType()
     */
    @Override
    public TermAnalysisType providedType() {
        return TermAnalysisType.SHINGLES;
    }

    /* (non-Javadoc)
     * @see org.fc.categorizer.services.impl.AbstractTermAnalyzerImpl#analyzeInternal(org.fc.nlp.model.Document)
     */
    @Override
    protected TermAnalysis analyzeInternal(Document document) {
        StopWatch sw = new StopWatch();
        sw.start();
        TermAnalysis documentTermAnalysis = createAnalysis();
        Tokenizer tokenizer = new StandardTokenizer(Version.LUCENE_CURRENT, document.getReader());
        ShingleFilter filter = new ShingleFilter(new LowerCaseFilter(tokenizer), maxShingleSize);
        filter.setOutputUnigrams(false);
        TermAttribute termAttribute = filter.addAttribute(TermAttribute.class);
        try {
            while (filter.incrementToken()) {
                String term = termAttribute.term();
                LOGGER.debug("Found term : " + term);
                documentTermAnalysis.addTerm(new Term(term));
            }
        } catch (IOException ioe) {
            LOGGER.error("Error tokenizing shingles", ioe);
        }
        sw.stop();
        LOGGER.info("Shingles analysis for document " + document.getIdentifier() + " took " + sw.getLastTaskTimeMillis() + " ms");
        return documentTermAnalysis;
    }

}
