package org.fc.nlp.web.controllers;

import org.apache.log4j.Logger;
import org.fc.nlp.web.model.CategorizationRequest;
import org.fc.nlp.web.model.CategorizationResponse;
import org.fc.nlp.web.model.ExplainResponse;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * CategorizerController
 * Created on 19 mars 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 59 $
 * $Id: RemoteCategorizerController.java 59 2010-04-26 16:41:58Z fredcons $
 */
@Controller
public class RemoteCategorizerController extends AbstractNlpController {
	
	private static final Logger LOGGER = Logger.getLogger(RemoteCategorizerController.class); 
	
    @RequestMapping(value="/remote/categorize")
    public ModelAndView categorize(CategorizationRequest request) {
        CategorizationResponse categorizationResponse = categorizeInternal(request);
        ModelAndView mav = new ModelAndView();
        mav.addObject("categorizationResponse", categorizationResponse);
        //View view = request.isJson() ? categorizeJsonView : categorizeXmlView;
        //mav.setView(view);
        return mav;
    }
    
    @RequestMapping(value="/remote/explain")
    public ModelAndView explain(CategorizationRequest request, BindingResult bindingResult) {
        ExplainResponse explainResponse =  explainInternal(request);
        ModelAndView mav = new ModelAndView();
        mav.addObject("explainResponse", explainResponse);
        //View view = request.isJson() ? explainJsonView : explainXmlView;
        //mav.setView(view);
        return mav;
    }
      

}
