package org.ericmignot.router;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.ModifyPage;
import org.ericmignot.page.Page;
import org.junit.Before;
import org.junit.Test;

public class ModifyPageChooserTest {

	private ModifyPageChooser pageChooser;
	
	@Before public void
	init() {
		pageChooser = new ModifyPageChooser();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/modify/sample");
		
		assertTrue( "activation", pageChooser.isConcernedBy( request ) );
		assertTrue( "activation", pageChooser.isConcernedBy( request( "/specs/modify/sample-calculator") ) );
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
		assertFalse( "don't activate", pageChooser.isConcernedBy( request( "/specs/modify" ) ) );
		assertFalse( "don't activate", pageChooser.isConcernedBy( request( "/specs/modify/" ) ) );
	}
	
	@Test public void
	canExtractSpecXFromUri() {
		assertEquals( "extract spec-x", "sample", pageChooser.extractSpecX( "/specs/modify/sample" ) );
		assertEquals( "extract spec-x", "toto", pageChooser.extractSpecX( "/specs/modify/toto" ) );
	}
	
	@Test public void
	returnsModifyPageWithSpecXParameter() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/modify/sample");
		Page instance = pageChooser.getPage( request );
		assertTrue( "show page instance", instance instanceof ModifyPage );
		assertEquals( "spec-x param", "sample", ((ModifyPage)instance).getSpecX() );
	}
}
