package org.ericmignot.router;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.Page;
import org.ericmignot.page.ShowPage;
import org.junit.Before;
import org.junit.Test;

public class ShowPageChooserTest {

	private ShowPageChooser pageChooser;
	
	@Before public void
	init() {
		pageChooser = new ShowPageChooser();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/show/sample");
		
		assertTrue( "activation", pageChooser.isConcernedBy( request ) );
		assertTrue( "activation", pageChooser.isConcernedBy( request( "/specs/show/sample-calculator") ) );
	}

	private HttpServletRequest request( String uri ) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		return request;
	}
	
	@Test public void
	noActivation() {
		assertFalse( "don't activate", pageChooser.isConcernedBy( request( "/" ) ) );
		assertFalse( "don't activate", pageChooser.isConcernedBy( request( "" ) ) );
		assertFalse( "don't activate", pageChooser.isConcernedBy( request( null ) ) );
		assertFalse( "don't activate", pageChooser.isConcernedBy( request( "/specs/show" ) ) );
		assertFalse( "don't activate", pageChooser.isConcernedBy( request( "/specs/show/" ) ) );
	}
	
	@Test public void
	canExtractSpecXFromUri() {
		assertEquals( "extract spec-x", "sample", pageChooser.extractSpecX( "/specs/show/sample" ) );
		assertEquals( "extract spec-x", "toto", pageChooser.extractSpecX( "/specs/show/toto" ) );
	}
	
	@Test public void
	returnsShowPageWithSpecXParameter() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/show/sample");
		Page instance = pageChooser.getPage( request );
		assertTrue( "show page instance", instance instanceof ShowPage );
		assertEquals( "spec-x param", "sample", ((ShowPage)instance).getSpecX() );
	}
}
