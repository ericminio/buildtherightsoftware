package org.ericmignot.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.store.SpecRepository;

public class InMemoryRepository implements SpecRepository {

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

	public List<Spec> getSpecs() {
		return new ArrayList<Spec>( specs.values() );
	}

	public List<Spec> getSpecs(String label) {
		List<Spec> filteredByLabel = new ArrayList<Spec>();
		for (Spec spec : specs.values()) {
			if ( spec.getLabel().equalsIgnoreCase( label ) ) {
				filteredByLabel.add( spec );
			}
		}
		return filteredByLabel;
	}

	

}
