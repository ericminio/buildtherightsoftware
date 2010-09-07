package org.ericmignot.page.route;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.Page;
import org.ericmignot.page.ShowPage;
import org.ericmignot.page.route.ShowRoute;
import org.junit.Before;
import org.junit.Test;

public class ShowRouteTest {

	private ShowRoute pageChooser;
	
	@Before public void
	init() {
		pageChooser = new ShowRoute();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/show/sample");
		
		assertTrue( "activation", pageChooser.isActivatedBy( request ) );
		assertTrue( "activation", pageChooser.isActivatedBy( request( "/specs/show/sample-calculator") ) );
	}

	private HttpServletRequest request( String uri ) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		return request;
	}
	
	@Test public void
	noActivation() {
		assertFalse( "don't activate", pageChooser.isActivatedBy( request( "/" ) ) );
		assertFalse( "don't activate", pageChooser.isActivatedBy( request( "" ) ) );
		assertFalse( "don't activate", pageChooser.isActivatedBy( request( null ) ) );
		assertFalse( "don't activate", pageChooser.isActivatedBy( request( "/specs/show" ) ) );
		assertFalse( "don't activate", pageChooser.isActivatedBy( request( "/specs/show/" ) ) );
	}
	
	@Test public void
	returnsShowPageWithSpecXParameter() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/show/sample");
		Page instance = pageChooser.buildsPage( request );
		assertTrue( "show page instance", instance instanceof ShowPage );
		assertEquals( "spec-x param", "sample", ((ShowPage)instance).getSpecX() );
	}
}
