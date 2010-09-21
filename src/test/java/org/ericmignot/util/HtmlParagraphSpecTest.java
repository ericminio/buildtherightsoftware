package org.ericmignot.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.ericmignot.store.Repository;
import org.junit.Test;


public class HtmlParagraphSpecTest {

	private HtmlParagraphSpec spec;
	
	@Test public void
	delegatesSavingToTheGivenRepository() {
		spec = new HtmlParagraphSpec();
		Repository repoMock = mock( Repository.class );
		spec.saveIn( repoMock );
		verify( repoMock ).saveSpec( spec );
	}
	
	@Test public void
	keepsTitleAndContent() throws IOException {
		spec = new HtmlParagraphSpec( "title", "<br>content</br>" );
		assertEquals( "title", spec.getTitle() );
		assertEquals( "<br>content</br>", spec.getContent() );
	}
}
