package org.fc.nlp.web.support.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;

public abstract class WriteOnlyConverter implements Converter {

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		throw new UnsupportedOperationException("No unmarshalling provided");
	}

}
