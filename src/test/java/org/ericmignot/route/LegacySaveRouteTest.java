package org.ericmignot.route;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.LegacyPage;
import org.ericmignot.page.LegacySavePage;
import org.ericmignot.route.LegacySaveRoute;
import org.junit.Before;
import org.junit.Test;

public class LegacySaveRouteTest {

	private LegacySaveRoute pageChooser;
	
	@Before public void
	init() {
		pageChooser = new LegacySaveRoute();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/save/sample");
		when(request.getParameter("specX")).thenReturn( "toto" );
		
		assertTrue( "activation", pageChooser.isActivatedBy( request ) );
		assertTrue( "activation", pageChooser.isActivatedBy( requestWithParameter( "/specs/save/sample-calculator") ) );
	}

	private HttpServletRequest requestWithParameter( String uri ) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		when(request.getParameter("specX")).thenReturn( "toto" );
		return request;
	}
	
	private HttpServletRequest requestWithoutParameter( String uri ) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		return request;
	}
	
	@Test public void
	noActivation() {
		assertFalse( "don't activate", pageChooser.isActivatedBy( requestWithParameter( "/" ) ) );
		assertFalse( "don't activate", pageChooser.isActivatedBy( requestWithParameter( "" ) ) );
		assertFalse( "don't activate", pageChooser.isActivatedBy( requestWithParameter( null ) ) );
		assertFalse( "don't activate", pageChooser.isActivatedBy( requestWithParameter( "/specs/save" ) ) );
		assertFalse( "don't activate", pageChooser.isActivatedBy( requestWithParameter( "/specs/save/" ) ) );
		
		assertFalse( "don't activate", pageChooser.isActivatedBy( requestWithoutParameter( "/specs/save/sample" ) ) );
	}
	
	@Test public void
	returnsModifyPageWithSpecXParameter() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/save/sample");
		when(request.getParameter("specX")).thenReturn( "toto" );
		when(request.getParameter("label")).thenReturn( "code" );
		
		LegacyPage instance = pageChooser.buildsPage( request );
		assertTrue( "save page instance", instance instanceof LegacySavePage );
		LegacySavePage savePage = (LegacySavePage) instance;
		assertThat( "name of the page to be saved", savePage.getSpecX(), equalTo( "sample" ) );
		assertThat( "content of the page to be saved", savePage.getSpecXContent(), equalTo( "toto" ) );
		assertThat( "label of the page to be saved", savePage.getSpecXLabel(), equalTo( "code" ) );
	}
}