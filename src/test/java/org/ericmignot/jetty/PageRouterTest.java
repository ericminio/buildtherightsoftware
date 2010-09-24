package org.ericmignot.jetty;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.NewPage;
import org.ericmignot.page.SavePage;
import org.junit.Before;
import org.junit.Test;

public class PageRouterTest {

	private PageRouter pageRouter;
	
	@Before public void
	init() {
		pageRouter = new PageRouter();
	}
	
	@Test public void
	returnsSaveWhenSaveSpecIsCalled() {
		String specXContent = "toto";
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/save/sample");
		when(request.getParameter("specX")).thenReturn( specXContent );
		
		Page page = pageRouter.choosePage( request );
		assertTrue( "serves save page", page instanceof SavePage );
	}
	
	@Test public void
	returnsNewWhenNewIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/new");
		
		assertTrue( "serves new page", pageRouter.choosePage( request ) instanceof NewPage );
	}
	
	@Test public void
	returnsCreateWhenCreationIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/create");
		when(request.getQueryString()).thenReturn("specXName=toto");
		
		assertTrue( "serves save page", pageRouter.choosePage( request ) instanceof SavePage );
	}
	
	
}
