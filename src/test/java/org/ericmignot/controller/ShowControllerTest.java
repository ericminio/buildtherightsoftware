package org.ericmignot.controller;

import static org.ericmignot.util.HttpServletRequestMockBuilder.aMockRequest;
import static org.ericmignot.util.RepositoryMockBuilder.aMockRepo;
import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import org.ericmignot.adapters.Spec;
import org.ericmignot.adapters.SpecRepository;
import org.ericmignot.adapters.View;
import org.ericmignot.page.ShowPage;
import org.junit.Before;
import org.junit.Test;

public class ShowControllerTest {

	private ShowController controller;
	
	@Before public void
	init() {
		controller = new ShowController();
	}
	
	@Test public void
	activationSpecification() {
		assertTrue( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/show/sample").build() ) );
		assertTrue( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/show/sample-calculator").build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( "/" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( "" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( null ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/show" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/show/" ).build() ) );
	}
	
	@Test public void
	theRenderingViewIsAShowView() {
		assertTrue( controller.getView() instanceof ShowPage );
	}
	
	@Test public void
	rendersTheViewDuringWork() {
		View viewMock = mock( View.class );
		controller.setView( viewMock );
		Writer writerMock = mock( Writer.class );
		
		Spec spec = aSpec().withTitle( "sample" ).build();
		SpecRepository repoMock = aMockRepo().withOneSpec( spec ).build();
		
		controller.handle( aMockRequest().withThisUri( "/specs/show/sample").build(), repoMock, writerMock );
		verify( repoMock ).getSpecByTitle( "sample" );
		verify( viewMock ).setSpec( spec );
		verify( viewMock ).render( writerMock );
	}
}
