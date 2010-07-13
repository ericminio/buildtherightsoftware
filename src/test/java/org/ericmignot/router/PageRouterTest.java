package org.ericmignot.router;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.ModifyPage;
import org.ericmignot.page.Page;
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
	returnsDefaultSampleByDefault() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( "/" );
		
		Page page = pageRouter.choosePage( request );
		assertTrue( "serves show page", page instanceof ShowPage );
		ShowPage showPage = (ShowPage) page;
		assertThat( "spec", showPage.getSpecX(), equalTo( "sample" ));
		
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
	
	@Test public void
	returnsModifyPageWhenModifyIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/modify/sample");
		
		assertTrue( "serves modify page", pageRouter.choosePage( request ) instanceof ModifyPage );
	}
	
	
}
