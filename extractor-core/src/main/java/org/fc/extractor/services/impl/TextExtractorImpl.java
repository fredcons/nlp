package org.fc.extractor.services.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.fc.extractor.services.TextExtractor;
import org.fc.nlp.model.Document;
import org.fc.nlp.model.ReaderDocument;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

/**
 * TextExtractorImpl
 * Created on 17 févr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 57 $
 * $Id: TextExtractorImpl.java 57 2010-04-25 14:45:02Z fredcons $
 */
public class TextExtractorImpl implements TextExtractor {
    
    static final Logger LOGGER = Logger.getLogger(TextExtractorImpl.class);

    Tika tika = new Tika();
    
    /* (non-Javadoc)
     * @see org.fc.extractor.services.ExtractorService#extractDocument(java.io.InputStream)
     */
    @Override
    public Document extractDocument(InputStream input) {        
        Reader reader = null;
        try {
            reader = tika.parse(input);
        } catch (IOException ioe) {
            LOGGER.error("Error while parsing inputstream", ioe);
        } 
        return new ReaderDocument(reader);
    }
    
    /* (non-Javadoc)
     * @see org.fc.extractor.services.ExtractorService#extractDocument(java.io.InputStream)
     */
    @Override
    public Document extractDocument(String content, String mimeType) {        
        Reader reader = null;
        try {
        	if (StringUtils.hasText(mimeType)) {
        		Metadata metadata = new Metadata();
        		// TODO : check if CONTENT_TYPE is more appropriated
            	metadata.add(Metadata.MIME_TYPE_MAGIC, mimeType);
                reader = tika.parse(new ByteArrayInputStream(content.getBytes()), metadata);
        	} else {
        		reader = tika.parse(new ByteArrayInputStream(content.getBytes()));
        	}        	
        } catch (IOException ioe) {
            LOGGER.error("Error while parsing inputstream", ioe);
        } 
        return new ReaderDocument(reader);
    }
    
    /* (non-Javadoc)
     * @see org.fc.extractor.services.ExtractorService#extractDocument(java.io.File)
     */
    @Override
    public Document extractDocument(File file) {
        Reader reader = null;
        try {
            reader = tika.parse(file);
        } catch (IOException ioe) {
            LOGGER.error("Error while parsing file " + file.getName(), ioe);
        } 
        return new ReaderDocument(reader);
    }
    
    /* (non-Javadoc)
     * @see org.fc.extractor.services.ExtractorService#extractDocument(org.springframework.core.io.Resource)
     */
    @Override
    public Document extractDocument(Resource resource) {
        Document document = null;
        try {
            document = extractDocument(resource.getInputStream());
        } catch (IOException ioe) {
            LOGGER.error("Error while parsing resource " + resource.getFilename(), ioe);
        } 
        return document;
    }
    
    /* (non-Javadoc)
     * @see org.fc.extractor.services.ExtractorService#extractDocument(java.net.URL)
     */
    @Override
    public Document extractDocument(URL url) {
        Reader reader = null;
        try {
            reader = tika.parse(url);
        } catch (IOException ioe) {
            LOGGER.error("Error while parsing url " + url.toExternalForm(), ioe);
        } 
        return new ReaderDocument(reader);
    }

}
