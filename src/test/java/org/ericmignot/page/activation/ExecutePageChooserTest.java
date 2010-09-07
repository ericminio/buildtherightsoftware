package org.ericmignot.page.activation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.Page;
import org.ericmignot.page.ExecutePage;
import org.ericmignot.page.activation.ExecutePageActivator;
import org.junit.Before;
import org.junit.Test;

public class ExecutePageChooserTest {

	private ExecutePageActivator pageChooser;
	
	@Before public void
	init() {
		pageChooser = new ExecutePageActivator();
	}
	
	@Test public void
	activation() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/execute/sample");
		when(request.getParameter("repo")).thenReturn( "git://github.com/testaddict/mastermind.git" );
		
		assertTrue( "activation", pageChooser.isActivatedBy( request ) );
	}
	
	private HttpServletRequest request( String uri, String repoParam ) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		when(request.getParameter("repo")).thenReturn( repoParam );
		return request;
	}
	
	@Test public void
	noActivation() {
		assertFalse( "activate", pageChooser.isActivatedBy( request( "/", null ) ) );
		assertFalse( "activate", pageChooser.isActivatedBy( request( "", null ) ) );
		assertFalse( "activate", pageChooser.isActivatedBy( request( null, null ) ) );
		assertFalse( "activate", pageChooser.isActivatedBy( request( "/specs", null ) ) );
		assertFalse( "activate", pageChooser.isActivatedBy( request( "/specs/", null ) ) );
		assertFalse( "activate", pageChooser.isActivatedBy( request( "/specs/execute", null ) ) );
		assertFalse( "activate", pageChooser.isActivatedBy( request( "/specs/execute/", null ) ) );
		assertFalse( "activate", pageChooser.isActivatedBy( request( "/specs/execute/toto", null ) ) );
	}
	
	@Test public void
	returnsResultPageInstanceWithCorrectParameters() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/execute/sample");
		when(request.getParameter("repo")).thenReturn( "git://github.com/testaddict/mastermind.git" );
		
		Page instance = pageChooser.buildsPage( request );
		assertTrue( "result page", instance instanceof ExecutePage );
		ExecutePage resultPage = (ExecutePage) instance;
		assertEquals( "spec-x", "sample", resultPage.getLauncher().getSpecX() );
		assertEquals( "repo", "git://github.com/testaddict/mastermind.git", resultPage.getLauncher().getGitRepository() );
	}
}
