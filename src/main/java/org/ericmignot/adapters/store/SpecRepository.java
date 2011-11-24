package org.ericmignot.adapters.store;

import java.io.IOException;
import java.util.List;

import org.ericmignot.adapters.domain.Spec;


public interface SpecRepository {

	public void saveSpec(Spec spec) throws IOException;
	
	public Spec getSpecByTitle(String title);
	
	public List<Spec> getSpecs();
	
	public List<Spec> getSpecs(String label);

}
