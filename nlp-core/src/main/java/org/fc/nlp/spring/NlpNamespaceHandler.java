package org.fc.nlp.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * NlpNamespaceHandler
 * Created on 8 févr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 39 $
 * $Id: NlpNamespaceHandler.java 39 2010-02-23 09:43:47Z fredcons $
 */
public class NlpNamespaceHandler extends NamespaceHandlerSupport {

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
     */
    @Override
    public void init() {
        registerBeanDefinitionParser("fileDocument", new FileDocumentDefinitionParser());
        registerBeanDefinitionParser("resourceDocument", new ResourceDocumentDefinitionParser());
        registerBeanDefinitionParser("stringDocument", new StringDocumentDefinitionParser());
        registerBeanDefinitionParser("analyzedCategory", new AnalyzedCategoryDefinitionParser());
        registerBeanDefinitionParser("analyzableCategory", new CategoryDefinitionParser());
        registerBeanDefinitionParser("corpus", new CorpusDefinitionParser());
        registerBeanDefinitionParser("termAnalysis", new TermAnalysisDefinitionParser());
        
    }

}
