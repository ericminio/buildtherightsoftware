package org.ericmignot.store;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.domain.SpecMatcher;

public class MatchLabel extends SpecMatcher {

	private String label;
	
	public MatchLabel(String label) {
		this.label = label;
	}

	public boolean matches(Spec spec) {
		if (label == null || label == "") return true;
		return label.equalsIgnoreCase( spec.getLabel() );
	}

	public String getLabel() {
		return label;
	}
	
	public boolean equals(Object o) {
		MatchLabel other = (MatchLabel) o;
		return label.equalsIgnoreCase( other.getLabel() );
	}

}
