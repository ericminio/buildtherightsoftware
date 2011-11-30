package org.ericminio.btrs.domain.matchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.ericminio.btrs.domain.matchers.CoreMatchers;
import org.ericminio.btrs.domain.matchers.MatchAll;
import org.ericminio.btrs.domain.matchers.MatchLabel;
import org.junit.Test;


public class CoreMatchersTest {

	@Test public void
	providesConvenientMethodForMatchAllMatcher() {
		assertTrue( CoreMatchers.all() instanceof MatchAll );
	}
	
	@Test public void
	providesConvenientMethodForMatchLabelMatcher() {
		assertTrue( CoreMatchers.withLabel( "" ) instanceof MatchLabel );
	}
	
	@Test public void
	matchLabelMatcherIsInitializedWithGivenCriteria() {
		MatchLabel matcher = (MatchLabel) CoreMatchers.withLabel( "criteria" );
		assertThat( matcher.getLabel(), equalTo( "criteria" ) );
	}
}
