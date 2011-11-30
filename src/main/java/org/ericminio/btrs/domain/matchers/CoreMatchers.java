package org.ericminio.btrs.domain.matchers;

import org.ericminio.btrs.domain.SpecMatcher;

public class CoreMatchers {

	public static SpecMatcher all() {
		return new MatchAll();
	}

	public static SpecMatcher withLabel(String label) {
		return new MatchLabel( label );
	}

}