package org.ericmignot.adapters.store;

import java.util.List;

import org.ericmignot.adapters.domain.Spec;


public interface SpecRepository {

	public void saveSpec(Spec spec);
	
	public Spec getSpecByTitle(String title);
	
	public List<Spec> getSpecs();
	
	public List<Spec> getSpecs(String label);

}
