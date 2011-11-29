package org.ericmignot.domain;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.store.MatchAll;
import org.ericmignot.store.MatchLabel;

public abstract class SpecMatcher {

	public abstract boolean matches(Spec spec);

	public static SpecMatcher all() {
		return new MatchAll();
	}

	public static SpecMatcher withLabel(String label) {
		return new MatchLabel( label );
	}

}