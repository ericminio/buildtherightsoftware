package org.ericmignot.jetty;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.Page;
import org.ericmignot.jetty.PageRouter;
import org.ericmignot.page.CreatePage;
import org.ericmignot.page.ModifyPage;
import org.ericmignot.page.NewPage;
import org.ericmignot.page.ExecutePage;
import org.ericmignot.page.SavePage;
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
		
		assertTrue( "serves result page", pageRouter.choosePage( request ) instanceof ExecutePage );
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
		
		assertTrue( "serves create page", pageRouter.choosePage( request ) instanceof CreatePage );
	}
	
	
}
