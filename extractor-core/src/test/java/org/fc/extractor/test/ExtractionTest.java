package org.fc.extractor.test;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.fc.extractor.services.TextExtractor;
import org.fc.nlp.model.Document;
import org.fc.nlp.util.AnalysisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.ResourceUtils;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:/extractor-core-test-context.xml"})
public class ExtractionTest extends AbstractTestNGSpringContextTests {
    
    static final Logger LOGGER = Logger.getLogger(ExtractionTest.class);

    TextExtractor textExtractor;
    
	String[] documents = new String[] {"classpath:data/text.doc", "classpath:data/text.pdf", "classpath:data/text.html"};
    
	@Test
	public void loadContext() {
	    for (String resource : documents) {
	        try {
    	        File file = ResourceUtils.getFile(resource);
    		    Document document = textExtractor.extractDocument(file);
    		    LOGGER.info(document.getIdentifier() + " : " + AnalysisHelper.dumpReader(document.getReader()));
	        } catch (FileNotFoundException fnfe) {
	            LOGGER.error("Could not find " + resource, fnfe);
	        }
		}
	}

    /**
     * @param extractorService the extractorService to set
     */
	@Autowired
	public void setTextExtractor(TextExtractor textExtractor) {
		this.textExtractor = textExtractor;
	}

	
}