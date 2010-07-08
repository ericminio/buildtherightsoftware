package org.ericmignot.router;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.Page;
import org.ericmignot.page.ResultPage;
import org.junit.Before;
import org.junit.Test;

public class ResultPageChooserTest {

	private ResultPageChooser pageChooser;
	
	@Before public void
	init() {
		pageChooser = new ResultPageChooser();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/execute/sample");
		when(request.getParameter("repo")).thenReturn( "git://github.com/testaddict/mastermind.git" );
		
		assertTrue( "activation", pageChooser.isConcernedBy( request ) );
	}
	
	private HttpServletRequest request( String uri, String repoParam ) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		when(request.getParameter("repo")).thenReturn( repoParam );
		return request;
	}
	
	@Test public void
	noActivation() {
		assertFalse( "activate", pageChooser.isConcernedBy( request( "/", null ) ) );
		assertFalse( "activate", pageChooser.isConcernedBy( request( "", null ) ) );
		assertFalse( "activate", pageChooser.isConcernedBy( request( null, null ) ) );
		assertFalse( "activate", pageChooser.isConcernedBy( request( "/specs", null ) ) );
		assertFalse( "activate", pageChooser.isConcernedBy( request( "/specs/", null ) ) );
		assertFalse( "activate", pageChooser.isConcernedBy( request( "/specs/execute", null ) ) );
		assertFalse( "activate", pageChooser.isConcernedBy( request( "/specs/execute/", null ) ) );
		assertFalse( "activate", pageChooser.isConcernedBy( request( "/specs/execute/toto", null ) ) );
	}
	
	@Test public void
	canExtractSpecXFromUri() {
		assertEquals( "extract spec-x", "sample", pageChooser.extractSpecX( "/specs/execute/sample" ) );
		assertEquals( "extract spec-x", "toto", pageChooser.extractSpecX( "/specs/execute/toto" ) );
	}
	
	@Test public void
	returnsResultPageInstanceWithCorrectParameters() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/execute/sample");
		when(request.getParameter("repo")).thenReturn( "git://github.com/testaddict/mastermind.git" );
		
		Page instance = pageChooser.getPage( request );
		assertTrue( "result page", instance instanceof ResultPage );
		ResultPage resultPage = (ResultPage) instance;
		assertEquals( "spec-x", "sample", resultPage.getLauncher().getSe() );
		assertEquals( "repo", "git://github.com/testaddict/mastermind.git", resultPage.getLauncher().getGitRepository() );
	}
}
