package org.fc.nlp.web.controllers;

import org.apache.log4j.Logger;
import org.fc.nlp.web.model.TextExtractionRequest;
import org.fc.nlp.web.model.TextExtractionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RemoteTextExtractorController extends AbstractNlpController {
	
	private static final Logger LOGGER = Logger.getLogger(RemoteTextExtractorController.class);
	
	@RequestMapping(value="/remote/extract")
    public ModelAndView extract(TextExtractionRequest request) {
		ModelAndView mav = new ModelAndView();
		TextExtractionResponse textExtraction = extractInternal(request);
		mav.addObject("textExtraction", textExtraction);
		return mav;
	}	

}
