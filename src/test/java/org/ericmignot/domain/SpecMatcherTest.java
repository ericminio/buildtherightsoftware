package org.ericmignot.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.ericmignot.store.MatchAll;
import org.ericmignot.store.MatchLabel;
import org.junit.Test;


public class SpecMatcherTest {

	@Test public void
	providesConvenientMethodForMatchAllMatcher() {
		assertTrue( SpecMatcher.all() instanceof MatchAll );
	}
	
	@Test public void
	providesConvenientMethodForMatchLabelMatcher() {
		assertTrue( SpecMatcher.withLabel( "" ) instanceof MatchLabel );
	}
	
	@Test public void
	matchLabelMatcherIsInitializedWithGivenCriteria() {
		MatchLabel matcher = (MatchLabel) SpecMatcher.withLabel( "criteria" );
		assertThat( matcher.getLabel(), equalTo( "criteria" ) );
	}
}
