package org.fc.categorizer.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.fc.nlp.model.Categorization;
import org.fc.nlp.model.Category;
import org.fc.nlp.model.Document;
import org.fc.nlp.model.TermAnalysis;
import org.fc.categorizer.services.Categorizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:/categorizer-core-test-context.xml"})
public class CategorizerTest extends AbstractTestNGSpringContextTests {
	
	static final Logger LOGGER = Logger.getLogger(CategorizerTest.class); 

	Categorizer ngramCategorizer;
	
	Category frenchTestCategory;
	Category danishTestCategory;
	Category swedishTestCategory;
	Category finnishTestCategory;
	Category norwegianTestCategory;
	
	Category analyzedFrenchTestCategory;
	
		
	@Test(enabled=false)
	public void categorizeFrenchDocuments() {	    
	    for (Document frenchTestDocument : frenchTestCategory.getDocuments()) {
	        Categorization testResult = ngramCategorizer.categorize(frenchTestDocument);
	        Assert.assertNotNull(testResult.getCategory(), "At least one category should be returned ");
	        Assert.assertEquals(testResult.getCategory().getName(), frenchTestCategory.getName(), "Oops, wrong category for " + frenchTestCategory.getName());
	    }	    
	}
	
	@Test
	public void dumpExistingAnalysis() {
	    for (TermAnalysis termAnalysis : analyzedFrenchTestCategory.getTermAnalysises().values()) {
	        LOGGER.info("termAnalysis " + termAnalysis.getType() + " has " + termAnalysis.getFrequencies().keySet().size() + " items");
	    }
	}
	
	@Test(enabled=false)
    public void categorizeScandinavianDocuments() {       
	    for (Document danishTestDocument : danishTestCategory.getDocuments()) {
	        categorize(danishTestDocument, danishTestCategory);
        } 
	    for (Document swedishTestDocument : swedishTestCategory.getDocuments()) {
	        categorize(swedishTestDocument, swedishTestCategory);
        }
	    for (Document finnishTestDocument : finnishTestCategory.getDocuments()) {
	        categorize(finnishTestDocument, finnishTestCategory);
        }
	    for (Document norwegianTestDocument : norwegianTestCategory.getDocuments()) {
            categorize(norwegianTestDocument, norwegianTestCategory);
        }
	}
	
	private void  categorize(Document document, Category expectedCategory) {
	    Categorization testResult = ngramCategorizer.categorize(document);
        Assert.assertNotNull(testResult.getCategory(), "At least one category should be returned ");
        LOGGER.info("Found category " + testResult.getCategory().getName() + " , expected " + expectedCategory.getName());
        //Assert.assertEquals(testResult.getCategory().getName(), expectedCategory.getName(), "Oops, wrong category for " + expectedCategory.getName());
	}
	
	@Test(enabled=false)
	public void explainFrenchDocuments() {
	    for (Document frenchTestDocument : frenchTestCategory.getDocuments()) {
	        explain(frenchTestDocument);
	    }
	}
	
	@Test(enabled=false)
    public void explainScandinavianDocuments() {       
        for (Document danishTestDocument : danishTestCategory.getDocuments()) {
            LOGGER.info("Explaining document " + danishTestDocument.getIdentifier() + " in category " + danishTestCategory.getName());
            explain(danishTestDocument);
        } 
        for (Document swedishTestDocument : swedishTestCategory.getDocuments()) {
            LOGGER.info("Explaining document " + swedishTestDocument.getIdentifier() + " in category " + swedishTestCategory.getName());
            explain(swedishTestDocument);
        }
        for (Document finnishTestDocument : finnishTestCategory.getDocuments()) {
            LOGGER.info("Explaining document " + finnishTestDocument.getIdentifier() + " in category " + finnishTestCategory.getName());
            explain(finnishTestDocument);
        }
        for (Document norwegianTestDocument : norwegianTestCategory.getDocuments()) {
            LOGGER.info("Explaining document " + norwegianTestDocument.getIdentifier() + " in category " + norwegianTestCategory.getName());
            explain(norwegianTestDocument);
        }
    }
	
	private void explain(Document document) {
	    List<Categorization> categorizations = ngramCategorizer.explain(document);
        for (Categorization categorization : categorizations) {
            LOGGER.info(categorization);
        }
	}
	
	@Autowired
	public void setNgramCategorizer(Categorizer ngramCategorizer) {
		this.ngramCategorizer = ngramCategorizer;
	}

    /**
     * @param frenchTestCategory the frenchTestCategory to set
     */
	@Autowired
    public void setFrenchTestCategory(Category frenchTestCategory) {
        this.frenchTestCategory = frenchTestCategory;
    }

    /**
     * @param danishTestCategory the danishTestCategory to set
     */
	@Autowired
    public void setDanishTestCategory(Category danishTestCategory) {
        this.danishTestCategory = danishTestCategory;
    }
	
    /**
     * @param swedishTestCategory the swedishTestCategory to set
     */
	@Autowired
    public void setSwedishTestCategory(Category swedishTestCategory) {
        this.swedishTestCategory = swedishTestCategory;
    }

    /**
     * @param finnishTestCategory the finnishTestCategory to set
     */
	@Autowired
    public void setFinnishTestCategory(Category finnishTestCategory) {
        this.finnishTestCategory = finnishTestCategory;
    }
	
	/**
     * @param norwegianTestCategory the norwegianTestCategory to set
     */
	@Autowired
    public void setNorwegianTestCategory(Category norwegianTestCategory) {
        this.norwegianTestCategory = norwegianTestCategory;
    }

    /**
     * @param analyzedFrenchTestCategory the analyzedFrenchTestCategory to set
     */
	@Autowired
	public void setAnalyzedFrenchTestCategory(Category analyzedFrenchTestCategory) {
        this.analyzedFrenchTestCategory = analyzedFrenchTestCategory;
    }
	
}