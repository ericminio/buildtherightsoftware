package org.ericmignot.controller;

import static org.ericmignot.util.HttpServletRequestMockBuilder.aMockRequest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.SpecRenderer;
import org.junit.Before;
import org.junit.Test;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.ericmignot.util.matchers.SpecMatcher.isASpec;
import static org.ericmignot.util.RepositoryMockBuilder.aMockRepo;

public class SaveControllerTest {

	private SaveController controller;
	private HttpServletRequest requestMock;
	private SpecRepository repo;
	private Writer writerMock;
	
	@Before public void
	init() {
		controller = new SaveController();
		requestMock = aMockRequest().withThisUri( "/specs/save/toto" )
									.withThisLabel( "game" )
									.withThisContent( "tetris" )
					  .build(); 
		Spec spec = aSpec().withTitle( "toto" ).build();
		repo = aMockRepo().withSpec(spec).build();
		
		writerMock = mock( Writer.class );
	}
	
	@Test public void
	activationSpecification() {
		assertTrue( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/save/toto" )
																		  .withThisLabel( "game" )
																		  .withThisContent( "tetris" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( null ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/save" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/save/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/save/toto" ).build() ) );
	}

	@Test public void
	createASpecWithDefaultContent() {
		SpecRenderer renderer = mock(SpecRenderer.class);
		controller.setRenderer( renderer );
		
		controller.handle( requestMock, repo, writerMock );
		verify( repo ).saveSpec( argThat( isASpec().withTitle( "toto" )
												 .withLabel( "game" )
												 .withContent( "tetris" ) ) );
		verify( renderer ).setSpec( argThat( isASpec().withTitle( "toto" ) ) );
		verify( renderer ).render( writerMock );
	}
	
}
