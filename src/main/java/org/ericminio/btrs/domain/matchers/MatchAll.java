package org.ericminio.btrs.domain.matchers;

import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecMatcher;

public class MatchAll implements SpecMatcher {

	public boolean matches(Spec spec) {
		return true;
	}
	
	public boolean equals(Object o) {
		return o instanceof MatchAll;
	}

}
