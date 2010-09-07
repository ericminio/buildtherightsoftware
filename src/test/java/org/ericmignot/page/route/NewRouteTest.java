package org.ericmignot.page.route;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.NewPage;
import org.ericmignot.page.route.NewRoute;
import org.junit.Before;
import org.junit.Test;

public class NewRouteTest {

	private NewRoute pageChooser;
	
	@Before public void
	init() {
		pageChooser = new NewRoute();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/new");
		
		assertTrue( "activation", pageChooser.isActivatedBy( request ) );
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
		assertFalse( "don't activate", pageChooser.isActivatedBy( request( "/specs/newspec" ) ) );
		assertFalse( "don't activate", pageChooser.isActivatedBy( request( "/specs/new/toto" ) ) );
	}
	
	@Test public void
	returnsNewPage() {
		assertTrue( pageChooser.buildsPage(null) instanceof NewPage );
	}
	
}
