package org.fc.nlp.web.controllers;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.fc.categorizer.services.Categorizer;
import org.fc.extractor.services.TextExtractor;
import org.fc.nlp.model.Categorization;
import org.fc.nlp.model.Document;
import org.fc.nlp.web.model.CategorizationRequest;
import org.fc.nlp.web.model.CategorizationResponse;
import org.fc.nlp.web.model.ExplainResponse;
import org.fc.nlp.web.model.TextExtractionRequest;
import org.fc.nlp.web.model.TextExtractionResponse;
import org.fc.nlp.web.support.spring.StringToUrlPropertyEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class AbstractNlpController {
    
    private static final Logger LOGGER = Logger.getLogger(AbstractNlpController.class); 
    
    Categorizer ngramCategorizer;
    TextExtractor textExtractor;
    
    /**
     * performs the actual categorization
     * @param text
     * @return
     */
    protected CategorizationResponse categorizeInternal(CategorizationRequest request) {
        long start = System.currentTimeMillis();
        Categorization categorization = null;
        if (StringUtils.hasText(request.getText())) {
            categorization = ngramCategorizer.categorize(request.getText());
        } else if (request.getUrl() != null) {
            categorization = ngramCategorizer.categorize(request.getUrl());
        } else if (request.getFile() != null) {
            categorization = ngramCategorizer.categorize(new ByteArrayInputStream(request.getFileAsBytes()));
        } else {
            LOGGER.warn("Request object is empty");
        }
        long executionTime = System.currentTimeMillis() - start;
        return new CategorizationResponse(executionTime, categorization);
    }
    
    /**
     * performs the actual explanation
     * @param text
     * @return
     */
    protected ExplainResponse explainInternal(CategorizationRequest request) {
        long start = System.currentTimeMillis();
        List<Categorization> categorizations = null;
        if (StringUtils.hasText(request.getText())) {
            categorizations = ngramCategorizer.explain(request.getText());
        } else if (request.getUrl() != null) {
            categorizations = ngramCategorizer.explain(request.getUrl());
        } else if (request.getFile() != null) {
            categorizations = ngramCategorizer.explain(new ByteArrayInputStream(request.getFileAsBytes()));
        } else {
            LOGGER.warn("Request object is empty");
        }
        long executionTime = System.currentTimeMillis() - start;
        return new ExplainResponse(executionTime, categorizations);
    }
    
    /**
     * performs the actual extraction
     * @param text
     * @return
     */
    protected TextExtractionResponse extractInternal(TextExtractionRequest request) {
        long start = System.currentTimeMillis();
        Document doc = null;
        if (StringUtils.hasText(request.getText())) {
            doc = textExtractor.extractDocument(request.getText(), request.getMimeType());
        } else if (request.getUrl() != null) {
            doc = textExtractor.extractDocument(request.getUrl());
        } else if (request.getFile() != null) {
            doc = textExtractor.extractDocument(new ByteArrayInputStream(request.getFileAsBytes()));
        } else {
            LOGGER.warn("Request object is empty");
        }
        long executionTime = System.currentTimeMillis() - start;
        return new TextExtractionResponse(executionTime, doc != null ? doc.getContentAsString() : "");
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(URL.class, new StringToUrlPropertyEditor());
    }

    /**
     * @param categorizer the categorizer to set
     */
    @Autowired
    public void setNgramCategorizer(Categorizer ngramCategorizer) {
        this.ngramCategorizer = ngramCategorizer;
    }
    
    /**
     * @param textExtractor the textExtractor to set
     */
    @Autowired
    public void setTextExtractor(TextExtractor textExtractor) {
        this.textExtractor = textExtractor;
    }
	
}
