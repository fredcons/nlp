package org.fc.extractor.services;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.fc.nlp.model.Document;
import org.springframework.core.io.Resource;

/**
 * TextExtractor
 * Created on 17 févr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 57 $
 * $Id: TextExtractor.java 57 2010-04-25 14:45:02Z fredcons $
 */
public interface TextExtractor {
    
    Document extractDocument(InputStream input);
    
    Document extractDocument(File file);
    
    Document extractDocument(Resource resource);
    
    Document extractDocument(URL url);
    
    Document extractDocument(String content, String mimeType);

}
