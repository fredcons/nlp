package org.fc.nlp.spring;

import org.fc.nlp.model.ResourceDocument;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * ResourceDocumentDefinitionParser
 * Created on 8 févr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 34 $
 * $Id: ResourceDocumentDefinitionParser.java 34 2010-02-18 12:19:37Z fredcons $
 */
public class ResourceDocumentDefinitionParser extends AbstractSingleBeanDefinitionParser {

   
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser#getBeanClass(org.w3c.dom.Element)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Class getBeanClass(Element element) {
        return ResourceDocument.class;
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
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        builder.setScope("prototype");
        String path = element.getAttribute("path");
        builder.addConstructorArgValue(path);
        
        String encoding = element.getAttribute("encoding");
        if (StringUtils.hasText(encoding)) {
            builder.addConstructorArgValue(encoding);
        }
    }
    
    
    
}
