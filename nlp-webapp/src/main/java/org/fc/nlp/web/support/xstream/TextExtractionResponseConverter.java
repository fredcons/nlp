package org.fc.nlp.web.support.xstream;

import org.fc.nlp.web.model.TextExtractionResponse;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class TextExtractionResponseConverter extends WriteOnlyConverter {

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,	MarshallingContext context) {
		TextExtractionResponse response = (TextExtractionResponse) source;
		context.convertAnother(response.getResponseMetadata());
		writer.startNode("content");
		writer.setValue(response.getContent());
		writer.endNode();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean canConvert(Class type) {
		if (type.isAssignableFrom(TextExtractionResponse.class)) {
			return true;
		}
		return false;
	}

}
