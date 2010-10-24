package org.fc.categorizer.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.fc.nlp.model.Category;
import org.fc.nlp.model.Corpus;
import org.fc.nlp.services.impl.NgramAnalyzerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:/categorizer-core-test-context.xml"})
public class CorpusNgramAnalysisDumperTest extends AbstractTestNGSpringContextTests {
	
	static final Logger LOGGER = Logger.getLogger(CorpusNgramAnalysisDumperTest.class); 

	Corpus reference;
	NgramAnalyzerImpl ngramAnalyzer;
	
	String dumpDirectory;
		
	@BeforeClass
	public void prepareCorpus() {
		LOGGER.info("Preparing corpus for analysis dump");
		ngramAnalyzer.analyze(reference);
	}

	@Test
	public void dumpReferenceCorpus() {		
	    File dumpDir = new File(dumpDirectory + File.separator + "ngrams");
		if (!dumpDir.exists()) {
		    dumpDir.mkdirs();
		}
		for (Category category : reference.getCategories()) {
			LOGGER.info("Dumping category " + category.getName() + " in " + dumpDir.getPath());
			try {
				for (int size : new Integer[] {0, 500, 1000}) {
    			    File categoryFile = new File(dumpDir, category.getName() + (size > 0 ? "_" + size : "") + ".txt");
    				FileOutputStream fos = new FileOutputStream(categoryFile);
    				fos.write(category.getTermAnalysis(ngramAnalyzer.providedType()).asTopTermScores(size).getBytes());
				}	
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}		
	}

	@Autowired
	public void setReference(Corpus reference) {
		this.reference = reference;
	}
	
	@Autowired
	public void setNgramAnalyzer(NgramAnalyzerImpl ngramAnalyzer) {
		this.ngramAnalyzer = ngramAnalyzer;
	}

    /**
     * @param dumpLocation the dumpLocation to set
     */
	@Autowired
    public void setDumpDirectory(String dumpDirectory) {
        this.dumpDirectory = dumpDirectory;
    }

	
}
