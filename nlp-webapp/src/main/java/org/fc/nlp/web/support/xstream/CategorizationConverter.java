package org.fc.nlp.web.support.xstream;

import org.fc.nlp.model.Categorization;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CategorizationConverter extends WriteOnlyConverter {

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,	MarshallingContext context) {
		Categorization categorization = (Categorization) source;
		writer.startNode("categorization");
		writer.addAttribute("category", categorization.getCategory().getName());
		writer.addAttribute("similarity", String.valueOf(categorization.getSimilarity()));
		writer.endNode();
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean canConvert(Class type) {
		if (type.isAssignableFrom(Categorization.class)) {
			return true;
		}
		return false;
	}

}
