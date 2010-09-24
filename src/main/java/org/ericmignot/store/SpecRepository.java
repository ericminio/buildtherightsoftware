package org.ericmignot.store;

import org.ericmignot.core.Spec;

public interface SpecRepository {

	public void saveSpec(Spec spec);
	public Spec getSpecByTitle(String title);
}
