package org.fc.nlp.services.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;
import org.fc.nlp.model.Document;
import org.fc.nlp.model.Term;
import org.fc.nlp.model.TermAnalysis;
import org.fc.nlp.model.TermAnalysisType;
import org.springframework.util.StopWatch;

/**
 * NgramAnalyzerImpl
 * Created on 18 févr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 40 $
 * $Id: NgramAnalyzerImpl.java 40 2010-02-23 21:33:27Z fredcons $
 */
public class NgramAnalyzerImpl extends AbstractTermAnalyzerImpl {

    private static final Logger LOGGER = Logger.getLogger(NgramAnalyzerImpl.class);
    
    private int minNgramSize;
    private int maxNgramSize;
    
    public NgramAnalyzerImpl(int size) {
        this.minNgramSize = size;
        this.maxNgramSize = size;
    }
    
    public NgramAnalyzerImpl(int minSize, int maxSize) {
        this.minNgramSize = minSize;
        this.maxNgramSize = maxSize;
    }
        
    /* (non-Javadoc)
     * @see org.fc.categorizer.services.impl.AbstractTermAnalyzerImpl#providedType()
     */
    @Override
    public TermAnalysisType providedType() {
        return TermAnalysisType.NGRAMS;
    }
    
    /* (non-Javadoc)
     * @see org.fc.categorizer.services.impl.TermAnalyzerImpl#analyzeInternal(org.fc.nlp.model.Document)
     */
    @Override
    protected TermAnalysis analyzeInternal(Document document) {
        StopWatch sw = new StopWatch();
        sw.start();
        TermAnalysis documentTermAnalysis = createAnalysis();
        Tokenizer tokenizer = new StandardTokenizer(Version.LUCENE_CURRENT, document.getReader());
        TokenFilter filter = new NGramTokenFilter(new LowerCaseFilter(tokenizer), minNgramSize, maxNgramSize);
        TermAttribute termAttribute = filter.addAttribute(TermAttribute.class);
        try {
            while (filter.incrementToken()) {
                String term = termAttribute.term();
                LOGGER.debug("Found term : " + term);
                documentTermAnalysis.addTerm(new Term(term));
            }
        } catch (IOException ioe) {
            LOGGER.error("Error tokenizing ngrams", ioe);
        }
        sw.stop();
        LOGGER.info("Ngram analysis for document " + document.getIdentifier() + " took " + sw.getLastTaskTimeMillis() + " ms");
        return documentTermAnalysis;
    }

}
