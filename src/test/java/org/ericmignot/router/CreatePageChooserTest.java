package org.ericmignot.router;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.CreatePage;
import org.junit.Before;
import org.junit.Test;

public class CreatePageChooserTest {

	private CreatePageChooser pageChooser;
	
	@Before public void
	init() {
		pageChooser = new CreatePageChooser();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( "/specs/create" );
		when(request.getQueryString()).thenReturn( "specXName=tutu" );
		
		assertTrue( "activation", pageChooser.isConcernedBy( request ) );
	}

	private HttpServletRequest request( String uri, String queryString ) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		when(request.getQueryString()).thenReturn( queryString );
		return request;
	}
	
	@Test public void
	noActivation() {
		assertFalse( "should not activate", pageChooser.isConcernedBy( request( "/specs/create", null ) ) );
		assertFalse( "should not activate", pageChooser.isConcernedBy( request( "/specs/create", "specXName=" ) ) );
		assertFalse( "should not activate", pageChooser.isConcernedBy( request( "/specs/create", "specXNameFoo=tutu" ) ) );
		assertFalse( "should not activate", pageChooser.isConcernedBy( request( "/", null ) ) );
		assertFalse( "should not activate", pageChooser.isConcernedBy( request( "", null ) ) );
		assertFalse( "should not activate", pageChooser.isConcernedBy( request( null, null ) ) );
	}
	
	@Test public void
	canExtractSpecXNameFromQuery() {
		assertEquals( "toto",  pageChooser.extractSpecX( "specXName=toto" ));
		assertEquals( "hello",  pageChooser.extractSpecX( "specXName=hello" ));
	}
	
	@Test public void
	returnsCorrectPage() {
		assertEquals( "mytestcreation", ((CreatePage) pageChooser
				.getPage( request("/specs/create", "specXName=mytestcreation") )).getSpecX() );
	}
	
}
