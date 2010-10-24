package org.fc.nlp.web.support.xstream;

import org.fc.nlp.web.model.CategorizationResponse;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CategorizationResponseConverter extends WriteOnlyConverter {

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,	MarshallingContext context) {
		CategorizationResponse response = (CategorizationResponse) source;
		context.convertAnother(response.getResponseMetadata());
		context.convertAnother(response.getCategorization());

	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean canConvert(Class type) {
		if (type.isAssignableFrom(CategorizationResponse.class)) {
			return true;
		}
		return false;
	}

}
