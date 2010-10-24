package org.fc.nlp.services.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.Tokenizer;
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
 * @version $Revision: 43 $
 * $Id: WordAnalyzerImpl.java 43 2010-02-26 10:39:09Z fredcons $
 */
public class WordAnalyzerImpl extends AbstractTermAnalyzerImpl {

    private static final Logger LOGGER = Logger.getLogger(WordAnalyzerImpl.class);
    
    /* (non-Javadoc)
     * @see org.fc.categorizer.services.impl.AbstractTermAnalyzerImpl#providedType()
     */
    @Override
    public TermAnalysisType providedType() {
        return TermAnalysisType.WORDS;
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
        TokenFilter filter = new LowerCaseFilter(tokenizer);
        TermAttribute termAttribute = filter.addAttribute(TermAttribute.class);
        try {
            while (filter.incrementToken()) {
                String term = termAttribute.term();
                LOGGER.debug("Found term : " + term);
                documentTermAnalysis.addTerm(new Term(term));
            }
        } catch (IOException ioe) {
            LOGGER.error("Error tokenizing words", ioe);
        }
        sw.stop();
        LOGGER.info("Word analysis for document " + document.getIdentifier() + " took " + sw.getLastTaskTimeMillis() + " ms");
        return documentTermAnalysis;
    }

}
