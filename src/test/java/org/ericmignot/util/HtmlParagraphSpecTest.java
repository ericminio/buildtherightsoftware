package org.ericmignot.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;


public class HtmlParagraphSpecTest {

	private HtmlParagraphSpec spec = new HtmlParagraphSpec( "title", "<br>content</br>" );;
	
	@Test public void
	keepsTitleAndContent() throws IOException {
		assertEquals( "title", spec.getTitle() );
		assertEquals( "<br>content</br>", spec.getContent() );
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
