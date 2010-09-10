package org.ericmignot.route;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.SavePage;
import org.ericmignot.route.CreateRoute;
import org.junit.Before;
import org.junit.Test;

public class CreateRouteTest {

	private CreateRoute pageChooser;
	
	@Before public void
	init() {
		pageChooser = new CreateRoute();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( "/specs/create" );
		when(request.getQueryString()).thenReturn( "specXName=tutu" );
		
		assertTrue( "activation", pageChooser.isActivatedBy( request ) );
	}

	private HttpServletRequest request( String uri, String queryString ) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		when(request.getQueryString()).thenReturn( queryString );
		return request;
	}
	
	@Test public void
	noActivation() {
		assertFalse( "should not activate", pageChooser.isActivatedBy( request( "/specs/create", null ) ) );
		assertFalse( "should not activate", pageChooser.isActivatedBy( request( "/specs/create", "specXName=" ) ) );
		assertFalse( "should not activate", pageChooser.isActivatedBy( request( "/specs/create", "specXNameFoo=tutu" ) ) );
		assertFalse( "should not activate", pageChooser.isActivatedBy( request( "/", null ) ) );
		assertFalse( "should not activate", pageChooser.isActivatedBy( request( "", null ) ) );
		assertFalse( "should not activate", pageChooser.isActivatedBy( request( null, null ) ) );
		assertFalse( "should not activate", pageChooser.isActivatedBy( request( null, "" ) ) );
	}
	
	@Test public void
	returnsCorrectPage() {
		SavePage page = (SavePage) pageChooser.buildsPage( request("/specs/create", "specXName=mytestcreation") );
		assertEquals( "mytestcreation", page.getSpecX() );
		assertEquals( "", page.getSpecXLabel() );
	}
	
}
