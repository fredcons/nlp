package org.fc.nlp.spring;

import java.util.List;

import org.fc.nlp.model.Category;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * FileDocumentDefinitionParser
 * Created on 8 févr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 39 $
 * $Id: AnalyzedCategoryDefinitionParser.java 39 2010-02-23 09:43:47Z fredcons $
 */
public class AnalyzedCategoryDefinitionParser extends AbstractSingleBeanDefinitionParser {

   
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser#getBeanClass(org.w3c.dom.Element)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Class getBeanClass(Element element) {
        return Category.class;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.xml.AbstractBeanDefinitionParser#resolveId(org.w3c.dom.Element, org.springframework.beans.factory.support.AbstractBeanDefinition, org.springframework.beans.factory.xml.ParserContext)
     */
    @Override
    protected String resolveId(Element element, AbstractBeanDefinition definition, ParserContext parserContext) throws BeanDefinitionStoreException {
        return element.getAttribute("id");
    }
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser#doParse(org.w3c.dom.Element, org.springframework.beans.factory.support.BeanDefinitionBuilder)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        builder.setScope("prototype");
        String name = element.getAttribute("name");
        builder.addConstructorArgValue(name);
               
        // a list of termanalyses
        List parsedList = parserContext.getDelegate().parseListElement(element, builder.getRawBeanDefinition());
        builder.addConstructorArgValue(parsedList);
        
    }    
    
}
