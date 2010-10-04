package org.ericmignot.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.ericmignot.domain.PlainTextSpec;
import org.junit.Test;


public class PlainTextSpecTest {

	private PlainTextSpec spec = new PlainTextSpec( "title" );;
	
	@Test public void
	canBeCreatedOnlyWithATitle() {
		assertEquals( "title", spec.getTitle() );
		assertNull( spec.getContent() );
		assertNull( spec.getLabel() );
	}
	
	@Test public void
	acceptTitleModification() {
		spec.setTitle( "new title" );
		assertEquals( "new title", spec.getTitle() );
	}
	
	@Test public void
	acceptContentModification() {
		spec.setContent( "new content" );
		assertEquals( "new content", spec.getContent() );
	}
	
	@Test public void
	acceptLabelModification() {
		spec.setLabel( "new label" );
		assertEquals( "new label", spec.getLabel() );
	}
}
