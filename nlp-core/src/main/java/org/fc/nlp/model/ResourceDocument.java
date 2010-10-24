package org.fc.nlp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

import org.springframework.core.io.Resource;

/**
 * ResourceDocument
 * Created on 5 févr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 29 $
 * $Id: ResourceDocument.java 29 2010-02-17 15:23:09Z fredcons $
 */
public class ResourceDocument extends EncodedDocument {
    
    Resource resource;
    
    public ResourceDocument(Resource resource) {
        this.resource = resource;
    }
    
    public ResourceDocument(Resource resource, String encoding) {
        this.resource = resource;
        this.encoding = encoding;
    }
    
    @Override
    public String getIdentifier() {
    	return resource.getFilename();
    }

    /* (non-Javadoc)
     * @see org.fc.categorizer.model.Document#getReader()
     */
    @Override
    public Reader getReader() {
        Reader reader = null;
        try {
        	InputStream targetStream = null;
        	if (resource.getFilename().endsWith(".gz")) {
        		LOGGER.debug("Using gzip stream for filename " + resource.getFilename());
        		targetStream = new GZIPInputStream(resource.getInputStream());
        	} else {
        		targetStream = resource.getInputStream();
        	}
            reader = new BufferedReader(new InputStreamReader(targetStream, charset));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return reader;
    }

}
