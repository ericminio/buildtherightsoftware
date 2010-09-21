package org.ericmignot.store;

import org.ericmignot.core.Spec;

public interface Repository {

	public void saveSpec(Spec spec);
	public Spec getSpecByTitle(String title);
}
