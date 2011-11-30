package org.ericminio.btrs.domain.matchers;

import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.junit.Assert.assertTrue;

import org.ericminio.btrs.domain.SpecMatcher;
import org.ericminio.btrs.domain.matchers.MatchAll;
import org.junit.Test;


public class MatchAllTest {

	@Test public void
	allSpecsMatch() {
		SpecMatcher filter = new MatchAll();
		assertTrue( filter.matches( aSpec().build() ) );
	}
	
	@Test public void
	twoMatchersOnSameLabelArEqual() {
		assertTrue( new MatchAll().equals( new MatchAll()) );
	}

	
}
