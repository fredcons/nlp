package org.fc.extractor.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:/extractor-core-test-context.xml"})
public class ContextTest extends AbstractTestNGSpringContextTests {

	
	@Test
	public void loadContext() {
		
	}
	
}