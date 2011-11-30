package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.HttpServletRequestStubBuilder.aStubRequest;
import static org.ericminio.btrs.application.route.controllers.RepositoryMockBuilder.aRepo;
import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import org.ericminio.btrs.application.route.controllers.ModifyController;
import org.ericminio.btrs.application.view.SpecRenderer;
import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecRepository;
import org.junit.Before;
import org.junit.Test;

public class ModifyControllerTest {

	private ModifyController controller;
	
	@Before public void
	init() {
		controller = new ModifyController();
	}
	
	@Test public void
	activationSpecification() {
		assertTrue( "activation", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/modify/sample" ).build() ) );
		assertTrue( "activation", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/modify/sample-calculator").build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aStubRequest().withThisUri( "/" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aStubRequest().withThisUri( "" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aStubRequest().withThisUri( null ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/modify" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/modify/" ).build() ) );
	}
	
	@Test public void
	rendersTheViewDuringWork() throws Exception {
		SpecRenderer viewMock = mock( SpecRenderer.class );
		controller.setRenderer( viewMock );
		
		Writer writerMock = mock( Writer.class );
		
		Spec spec = aSpec().withTitle( "sample" ).build();
		SpecRepository repoMock = aRepo().withSpec( spec ).build();
		
		controller.handle( aStubRequest().withThisUri( "/specs/modify/sample").build(), repoMock, writerMock );
		verify( viewMock ).setSpec( spec );
		verify( viewMock ).render( writerMock );
	}
}
