package org.ericminio.btrs.domain;

import java.io.IOException;
import java.util.List;

public interface SpecRepository {

	void saveSpec(Spec spec) throws IOException;
	
	Spec getSpecByTitle(String title);
	
	List<Spec> getSpecs(SpecMatcher matcher);

}
