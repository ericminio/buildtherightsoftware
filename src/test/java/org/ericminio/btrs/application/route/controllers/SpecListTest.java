package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.HttpServletRequestStubBuilder.aStubRequest;
import static org.ericminio.btrs.application.route.controllers.RepositoryMockBuilder.aRepo;
import static org.ericminio.btrs.domain.matchers.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.ericminio.btrs.application.route.controllers.SpecList;
import org.ericminio.btrs.application.view.ListRenderer;
import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecMatcher;
import org.ericminio.btrs.domain.SpecRepository;
import org.junit.Before;
import org.junit.Test;

import static org.ericminio.btrs.domain.SpecMatcher.*;
import static org.ericminio.btrs.store.SpecBuilder.aSpec;

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
		assertTrue( "activation", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/list").build() ) );
		assertTrue( "activation", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/list").withThisPostParameter( "label", "toto" ).build() ) );
		assertFalse( "activation", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/list/toto").build() ) );
		assertFalse( "activation", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/listtoto").build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aStubRequest().withThisUri( "/" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aStubRequest().withThisUri( "" ).build() ) );
		assertFalse( "don't activate", controller.isActivatedBy( aStubRequest().withThisUri( null ).build() ) );
	}
	
	@Test public void
	initializesTheViewWithTheSpecsBeforeRendering() throws Exception {
		SpecRepository repo = aRepo().withSpec( aSpec().build() ).build();
		List<Spec> specs = repo.getSpecs( all() );
		
		controller.handle( null, repo, writerMock );
		verify( rendererMock ).setSpecs( specs );
		verify( rendererMock ).render( writerMock );
	}
	
	@Test public void
	extractOnlyTheLabeledSpecsWhenALabelFilterIsSpecified() throws Exception {
		SpecRepository repoMock = mock( SpecRepository.class );
		controller.handle( aStubRequest().withThisUri( "/specs/list")
							.withThisGetParameter( "label", "toto" ).build(), repoMock, writerMock );
		verify( repoMock ).getSpecs( withLabel( "toto" ) );
	}

	@Test public void
	initializeTheViewWithTheFilteredSpecsWhenALabelIsSpecifiedInTheRequest() throws Exception {
		SpecRepository repoStub = mock( SpecRepository.class );
		List<Spec> expectedSpecs = new ArrayList<Spec>();
		expectedSpecs.add( aSpec().build() );
		when(repoStub.getSpecs( withLabel( "toto" ) )).thenReturn( expectedSpecs );
		
		controller.handle( aStubRequest().withThisUri( "/specs/list")
							.withThisGetParameter( "label", "toto" ).build(), repoStub, writerMock );
		verify( rendererMock ).setSpecs( expectedSpecs );
		verify( rendererMock ).render( writerMock );
	}
	
}
