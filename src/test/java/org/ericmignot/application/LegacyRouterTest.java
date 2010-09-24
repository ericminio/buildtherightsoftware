package org.ericmignot.application;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.LegacyPage;
import org.ericmignot.application.LegacyRouter;
import org.ericmignot.page.LegacyNewPage;
import org.ericmignot.page.LegacySavePage;
import org.junit.Before;
import org.junit.Test;


public class LegacyRouterTest {

	private LegacyRouter pageRouter;
	
	@Before public void
	init() {
		pageRouter = new LegacyRouter();
	}
	
	@Test public void
	returnsSaveWhenSaveSpecIsCalled() {
		String specXContent = "toto";
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/save/sample");
		when(request.getParameter("specX")).thenReturn( specXContent );
		
		LegacyPage page = pageRouter.choosePage( request );
		assertTrue( "serves save page", page instanceof LegacySavePage );
	}
	
	@Test public void
	returnsNewWhenNewIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/new");
		
		assertTrue( "serves new page", pageRouter.choosePage( request ) instanceof LegacyNewPage );
	}
	
	@Test public void
	returnsCreateWhenCreationIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/create");
		when(request.getQueryString()).thenReturn("specXName=toto");
		
		assertTrue( "serves save page", pageRouter.choosePage( request ) instanceof LegacySavePage );
	}
	
	
}
