package org.ericmignot.store;

import java.util.HashMap;
import java.util.Map;

import org.ericmignot.core.Spec;

public class InMemoryRepository implements Repository {

	protected Map<String, Spec> specs;
	
	public InMemoryRepository() {
		setSpecStoringMap( new HashMap<String, Spec>() );
	}
	
	protected void setSpecStoringMap(Map<String, Spec> map) {
		this.specs = map;
	}
	
	public void saveSpec(Spec spec) {
		this.specs.put( spec.getTitle() , spec);
	}

	public Spec getSpecByTitle(String title) {
		return specs.get( title );
	}

	

}
