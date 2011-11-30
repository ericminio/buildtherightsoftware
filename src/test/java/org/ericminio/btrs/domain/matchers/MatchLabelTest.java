package org.ericminio.btrs.domain.matchers;

import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.ericminio.btrs.domain.matchers.MatchLabel;
import org.junit.Test;


public class MatchLabelTest {

	@Test public void
	dontMatchAllSpecs() {
		MatchLabel filter = new MatchLabel( "this-label" );
		assertFalse( filter.matches( aSpec().build() ) );
	}
	
	@Test public void
	matchesSpecWithGivenLabel() {
		MatchLabel filter = new MatchLabel( "this-label" );
		assertTrue( filter.matches( aSpec().withLabel( "this-label" ).build() ) );
	}
	
	@Test public void
	matchesAllSpecsWhenCriteriaIsNull() {
		MatchLabel filter = new MatchLabel( null );
		assertTrue( filter.matches( aSpec().withLabel( "a-label" ).build() ) );
	}
	
	@Test public void
	matchesAllSpecsWhenCriteriaIsEmpty() {
		MatchLabel filter = new MatchLabel( "" );
		assertTrue( filter.matches( aSpec().withLabel( "a-label" ).build() ) );
	}
	
	@Test public void
	caseIncensitive() {
		MatchLabel filter = new MatchLabel( "this-label" );
		assertTrue( filter.matches( aSpec().withLabel( "THIS-label" ).build() ) );
	}
	
	@Test public void
	twoMatchersOnSameLabelArEqual() {
		assertTrue( new MatchLabel( "a-label" ).equals( new MatchLabel( "a-label" )) );
	}
	
}
