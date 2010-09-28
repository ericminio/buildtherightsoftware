package org.ericmignot.controller;

import static org.ericmignot.util.HttpServletRequestMockBuilder.aMockRequest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import org.ericmignot.adapters.Renderer;
import org.ericmignot.adapters.SpecRepository;
import org.junit.Before;
import org.junit.Test;

public class NewControllerTest {

	private NewController controller;
	private Writer writerMock;
	
	@Before public void
	init() {
		controller = new NewController();
		writerMock = mock( Writer.class );
	}
	
	@Test public void
	activationSpecification() {
		assertTrue( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/new").build() ) );
		assertFalse( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/new/toto").build() ) );
		assertFalse( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/newtoto").build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( "/" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( "" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( null ).build() ) );
	}
	
	@Test public void
	rendersTheViewDuringWork() {
		Renderer rendererMock = mock( Renderer.class );
		controller.setRenderer( rendererMock );
		
		controller.handle( aMockRequest().withThisUri( "/specs/list").build(), mock(SpecRepository.class), writerMock );
		verify( rendererMock ).render( writerMock );
	}
}
