package org.ericmignot.action;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.ericmignot.util.RepositoryMockBuilder.aMockRepo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.core.Spec;
import org.ericmignot.jetty.View;
import org.ericmignot.page.ShowPage;
import org.ericmignot.store.Repository;
import org.junit.Before;
import org.junit.Test;

public class ShowTest {

	private Show action;
	
	@Before public void
	init() {
		action = new Show();
	}
	
	protected HttpServletRequest 
	aRequestWithThisUri( String uri ) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		return request;
	}
	
	@Test public void
	isActivatedByACompleteRequest() {
		assertTrue( "activation", action.isActivatedBy( aRequestWithThisUri( "/specs/show/sample") ) );
		assertTrue( "activation", action.isActivatedBy( aRequestWithThisUri( "/specs/show/sample-calculator") ) );
		assertFalse( "don't activate", action.isActivatedBy( aRequestWithThisUri( "/" ) ) );
		assertFalse( "don't activate", action.isActivatedBy( aRequestWithThisUri( "" ) ) );
		assertFalse( "don't activate", action.isActivatedBy( aRequestWithThisUri( null ) ) );
		assertFalse( "don't activate", action.isActivatedBy( aRequestWithThisUri( "/specs/show" ) ) );
		assertFalse( "don't activate", action.isActivatedBy( aRequestWithThisUri( "/specs/show/" ) ) );
	}
	
	@Test public void
	canExtractSpecTitleFromUri() {
		assertEquals( "sample", action.extractSpecTitle( aRequestWithThisUri( "/specs/show/sample") ) );
	}
	
	@Test public void
	getSpecFromRepository() {
		Repository repoMock = aMockRepo().withOneSpec( aSpec().withTitle( "sample" ).build() ).build();
		action.work( aRequestWithThisUri( "/specs/show/sample"), repoMock, mock( Writer.class ) );
		verify( repoMock ).getSpecByTitle( "sample" );
	}
	
	@Test public void
	theRenderingViewIsAShowView() {
		assertTrue( action.getView() instanceof ShowPage );
	}
	
	@Test public void
	rendersTheViewDuringWork() {
		View viewMock = mock( View.class );
		action.setView( viewMock );
		Writer writerMock = mock( Writer.class );
		
		Spec spec = aSpec().withTitle( "sample" ).build();
		Repository repoMock = aMockRepo().withOneSpec( spec ).build();
		
		action.work( aRequestWithThisUri( "/specs/show/sample"), repoMock, writerMock );
		verify( viewMock ).render( spec, writerMock );
	}
}
