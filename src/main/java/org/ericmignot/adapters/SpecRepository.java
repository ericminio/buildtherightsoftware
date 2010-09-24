package org.ericmignot.adapters;


public interface SpecRepository {

	public void saveSpec(Spec spec);
	
	public Spec getSpecByTitle(String title);
	
}
