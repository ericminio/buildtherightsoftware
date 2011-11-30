package org.ericminio.btrs.domain;

import java.io.IOException;
import java.util.List;

public interface SpecRepository {

	public void saveSpec(Spec spec) throws IOException;
	
	public Spec getSpecByTitle(String title);
	
	public List<Spec> getSpecs(SpecMatcher matcher);

}
