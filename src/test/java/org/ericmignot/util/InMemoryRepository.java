package org.ericmignot.util;

import java.util.HashMap;
import java.util.Map;

import org.ericmignot.core.Spec;
import org.ericmignot.store.Repository;

public class InMemoryRepository implements Repository {

	protected Map<String, Spec> specs;
	
	public InMemoryRepository() {
		setStoringMap( new HashMap<String, Spec>() );
	}
	
	public void setStoringMap(Map<String, Spec> map) {
		this.specs = map;
	}
	
	public void saveSpec(Spec spec) {
		this.specs.put( spec.getTitle() , spec);
	}

	public Spec getSpecByTitle(String title) {
		return specs.get( title );
	}

	

}
