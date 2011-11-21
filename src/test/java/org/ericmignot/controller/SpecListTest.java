package org.ericmignot.controller;

import static org.ericmignot.util.HttpServletRequestMockBuilder.aMockRequest;
import static org.ericmignot.util.RepositoryMockBuilder.aRepo;
import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.ListRenderer;
import org.junit.Before;
import org.junit.Test;

public class SpecListTest {

	private SpecList controller;
	private Writer writerMock;
	private ListRenderer rendererMock;
	
	@Before public void
	init() {
		controller = new SpecList();
		writerMock = mock( Writer.class );
		rendererMock = mock( ListRenderer.class );
		controller.setRenderer( rendererMock );
	}
	
	@Test public void
	activationSpecification() {
		assertTrue( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/list").build() ) );
		assertTrue( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/list").withThisLabel( "toto" ).build() ) );
		assertFalse( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/list/toto").build() ) );
		assertFalse( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/listtoto").build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( "/" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( "" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aMockRequest().withThisUri( null ).build() ) );
	}
	
	@Test public void
	initializesTheViewWithTheSpecsBeforeRendering() {
		Spec spec = aSpec().withTitle( "sample" ).build();
		SpecRepository repo = aRepo().withSpec( spec ).build();
		List<Spec> specs = repo.getSpecs();
		
		controller.handle( null, repo, writerMock );
		verify( rendererMock ).setSpecs( specs );
		verify( rendererMock ).render( writerMock );
	}
	
	@Test public void
	extractOnlyTheLabeledSpecsWhenALabelFilterIsSpecified() {
		SpecRepository repoMock = mock( SpecRepository.class );
		controller.handle( aMockRequest().withThisUri( "/specs/list")
							.withThisLabelParam( "toto" ).build(), repoMock, writerMock );
		verify( repoMock ).getSpecs( "toto" );
	}

	@Test public void
	initializeTheViewWithTheFilteredSpecsWhenALabelIsSpecifiedInTheRequest() {
		List<Spec> expectedSpecs = new ArrayList<Spec>();
		expectedSpecs.add( aSpec().build() );
		SpecRepository repo = mock( SpecRepository.class );
		when(repo.getSpecs( "toto" )).thenReturn( expectedSpecs );
		
		controller.handle( aMockRequest().withThisUri( "/specs/list")
							.withThisLabelParam( "toto" ).build(), repo, writerMock );
		verify( rendererMock ).setSpecs( expectedSpecs );
		verify( rendererMock ).render( writerMock );
	}
	
}
