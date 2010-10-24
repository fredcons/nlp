package org.fc.nlp.spring;

import java.io.File;

import org.apache.log4j.Logger;
import org.fc.nlp.model.TermAnalysis;
import org.fc.nlp.model.TermAnalysisType;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * FileDocumentDefinitionParser
 * Created on 8 févr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 54 $
 * $Id: TermAnalysisDefinitionParser.java 54 2010-04-24 22:00:50Z fredcons $
 */
public class TermAnalysisDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private static final Logger LOGGER = Logger.getLogger(TermAnalysisDefinitionParser.class);
   
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser#getBeanClass(org.w3c.dom.Element)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Class getBeanClass(Element element) {
        return TermAnalysis.class;
    }
        
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser#doParse(org.w3c.dom.Element, org.springframework.beans.factory.support.BeanDefinitionBuilder)
     */
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        
        String path = element.getAttribute("path");
        String type = element.getAttribute("type");
        String topTermsAsString = element.getAttribute("topTerms");
        File file = null;
        try {
            file = ResourceUtils.getFile(path);
        } catch (Exception e) {
            LOGGER.error("Error loading " + path, e);
        }
        if (file == null) {
            LOGGER.error("No file found for path " + path);
        }
        
        builder.addConstructorArgValue(file);
        builder.addConstructorArgValue(TermAnalysisType.valueOf(type));
        int topTerms = 0;
        if (StringUtils.hasLength(topTermsAsString)) {
            topTerms = Integer.valueOf(topTermsAsString);
        }
        builder.addConstructorArgValue(topTerms);
        
    }    
    
}
