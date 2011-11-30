package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.HttpServletRequestStubBuilder.aStubRequest;
import static org.ericminio.btrs.domain.matchers.HamcrestSpecMatcher.isASpec;
import static org.ericminio.btrs.store.FileUtils.readFile;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.route.controllers.CreationController;
import org.ericminio.btrs.application.view.SpecRenderer;
import org.ericminio.btrs.domain.SpecRepository;
import org.junit.Before;
import org.junit.Test;

public class CreationControllerTest {

	private CreationController controller;
	private HttpServletRequest requestMock;
	private SpecRepository repo;
	private Writer writerMock;
	
	@Before public void
	init() {
		controller = new CreationController();
		requestMock = aStubRequest().withThisUri( "/specs/create" )
									.withThisGetParameter( "spec", "toto" )
					  .build(); 
		repo = mock( SpecRepository.class );
		writerMock = mock( Writer.class );
	}
	
	@Test public void
	activationSpecification() {
		assertTrue( "activation", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/create" ).withThisGetParameter( "spec", "spec-name" ).build() ) );

		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( null ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/create" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/create/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/create/toto" ).build() ) );
	}

	@Test public void
	createASpecWithDefaultContent() throws Exception {
		SpecRenderer renderer = mock(SpecRenderer.class);
		controller.setRenderer( renderer );
		
		controller.handle( requestMock, repo, writerMock );
		verify( repo ).saveSpec( argThat( isASpec().withTitle( "toto" )
												 .withContent( readFile( "target/html/newSpecContent.html" ) ) ) );
		verify( renderer ).setSpec( argThat( isASpec().withTitle( "toto" ) ) );
		verify( renderer ).render( writerMock );
	}
	
}
