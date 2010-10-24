package org.fc.nlp.web.support.xstream;

import org.fc.nlp.model.Categorization;
import org.fc.nlp.web.model.ExplainResponse;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ExplainResponseConverter extends WriteOnlyConverter {

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,	MarshallingContext context) {
		ExplainResponse response = (ExplainResponse) source;
		context.convertAnother(response.getResponseMetadata());
		writer.startNode("categorizations");
		for (Categorization categorization : response.getCategorizations()) {
			context.convertAnother(categorization);
		}	
		writer.endNode();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean canConvert(Class type) {
		if (type.isAssignableFrom(ExplainResponse.class)) {
			return true;
		}
		return false;
	}

}
