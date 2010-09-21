package org.ericmignot.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;


public class HtmlParagraphSpecTest {

	private HtmlParagraphSpec spec = new HtmlParagraphSpec( "title" );;
	
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
