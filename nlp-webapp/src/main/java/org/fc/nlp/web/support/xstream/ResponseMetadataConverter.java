package org.fc.nlp.web.support.xstream;

import org.fc.nlp.web.model.ResponseMetadata;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ResponseMetadataConverter extends WriteOnlyConverter {

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,	MarshallingContext context) {
		ResponseMetadata metadata = (ResponseMetadata) source;
		writer.startNode("executionTime");
		writer.setValue(String.valueOf(metadata.getExecutionTime()));
		writer.endNode();
		writer.startNode("errorMessage");
		writer.setValue(metadata.getErrorMessage() != null ? metadata.getErrorMessage() : "");
		writer.endNode();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean canConvert(Class type) {
		if (type.isAssignableFrom(ResponseMetadata.class)) {
			return true;
		}
		return false;
	}

}
