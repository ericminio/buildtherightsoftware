package org.ericminio.btrs.domain.matchers;

import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecMatcher;

public class MatchLabel implements SpecMatcher {

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
