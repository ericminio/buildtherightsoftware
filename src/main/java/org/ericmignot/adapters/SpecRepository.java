package org.ericmignot.adapters;

import java.util.List;


public interface SpecRepository {

	public void saveSpec(Spec spec);
	
	public Spec getSpecByTitle(String title);
	
	public List<Spec> getSpecs();

}
