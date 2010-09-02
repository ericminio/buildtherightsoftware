package org.ericmignot.router;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.NewPage;
import org.junit.Before;
import org.junit.Test;

public class NewPageChooserTest {

	private NewPageChooser pageChooser;
	
	@Before public void
	init() {
		pageChooser = new NewPageChooser();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/new");
		
		assertTrue( "activation", pageChooser.isConcernedBy( request ) );
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
		assertFalse( "don't activate", pageChooser.isConcernedBy( request( "/specs/newspec" ) ) );
		assertFalse( "don't activate", pageChooser.isConcernedBy( request( "/specs/new/toto" ) ) );
	}
	
	@Test public void
	returnsNewPage() {
		assertTrue( pageChooser.getPage(null) instanceof NewPage );
	}
	
}
