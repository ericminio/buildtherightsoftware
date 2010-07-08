package org.ericmignot.router;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.HomePage;
import org.ericmignot.page.ResultPage;
import org.ericmignot.page.ShowPage;
import org.junit.Before;
import org.junit.Test;

public class PageRouterTest {

	private PageRouter pageRouter;
	
	@Before public void
	init() {
		pageRouter = new PageRouter();
	}
	
	@Test public void
	returnsHomePageWhenSiteHomeIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/");
		assertNotNull( "choosen page", pageRouter.choosePage( request ) );
		assertTrue( "serves home page", pageRouter.choosePage( request ) instanceof HomePage );
	}
	
	@Test public void
	returnsResultWhenSeExecutionIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/execute/sample");
		when(request.getParameter("repo")).thenReturn( "git://github.com/testaddict/mastermind.git" );
		
		assertTrue( "serves result page", pageRouter.choosePage( request ) instanceof ResultPage );
	}
	
	@Test public void
	returnsShowPageWhenShowIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/show/sample");
		
		assertTrue( "serves show page", pageRouter.choosePage( request ) instanceof ShowPage );
	}
	
	
}
