package org.fc.categorizer.test;

import org.apache.log4j.Logger;
import org.fc.nlp.model.Document;
import org.fc.nlp.model.StringDocument;
import org.fc.nlp.model.TermAnalysis;
import org.fc.nlp.services.TermAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:/categorizer-core-test-context.xml"})
public class AnalyzerTest extends AbstractTestNGSpringContextTests {
	
	private static final Logger LOGGER = Logger.getLogger(AnalyzerTest.class); 

	TermAnalyzer ngramAnalyzer;
		
	@Test(enabled=false)
	public void analyze() {
		Document document = new StringDocument("foo");
		TermAnalysis result = ngramAnalyzer.analyze(document);
		result.postProcess();
		LOGGER.info(result.toString());
		Assert.assertEquals(1.0, result.sumSquaredNormalizedItems(), 0.000000001);
		
		Document similarDocument = new StringDocument("foo");
		TermAnalysis similarResult = ngramAnalyzer.analyze(similarDocument);
		similarResult.postProcess();
		
		Assert.assertEquals(result.computeSimilarity(similarResult), 1.0, 0.000000001);
	}
	
	
	
	@Autowired
	public void setNgramAnalyzer(TermAnalyzer ngramAnalyzer) {
		this.ngramAnalyzer = ngramAnalyzer;
	}
	
}