package org.ericmignot.store;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertTrue;

import org.ericmignot.domain.SpecMatcher;
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
