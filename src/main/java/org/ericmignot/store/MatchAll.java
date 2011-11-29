package org.ericmignot.store;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.domain.SpecMatcher;

public class MatchAll extends SpecMatcher {

	public boolean matches(Spec spec) {
		return true;
	}
	
	public boolean equals(Object o) {
		return o instanceof MatchAll;
	}

}
