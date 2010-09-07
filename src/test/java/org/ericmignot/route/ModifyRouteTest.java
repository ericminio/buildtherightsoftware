package org.ericmignot.route;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.Page;
import org.ericmignot.page.ModifyPage;
import org.ericmignot.route.ModifyRoute;
import org.junit.Before;
import org.junit.Test;

public class ModifyRouteTest {

	private ModifyRoute pageChooser;
	
	@Before public void
	init() {
		pageChooser = new ModifyRoute();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/modify/sample");
		
		assertTrue( "activation", pageChooser.isActivatedBy( request ) );
		assertTrue( "activation", pageChooser.isActivatedBy( request( "/specs/modify/sample-calculator") ) );
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
		assertFalse( "don't activate", pageChooser.isActivatedBy( request( "/specs/modify" ) ) );
		assertFalse( "don't activate", pageChooser.isActivatedBy( request( "/specs/modify/" ) ) );
	}
	
	@Test public void
	returnsModifyPageWithSpecXParameter() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/modify/sample");
		Page instance = pageChooser.buildsPage( request );
		assertTrue( "modify page instance", instance instanceof ModifyPage );
		assertEquals( "spec-x param", "sample", ((ModifyPage)instance).getSpecX() );
	}
}
