package org.ericmignot.controller;

import static org.ericmignot.util.HttpServletRequestStubBuilder.aStubRequest;
import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.ListRenderer;
import org.ericmignot.domain.SpecMatcher;
import org.junit.Before;
import org.junit.Test;


public class LabelListControllerTest {

	private LabelListController controller;
	
	@Before public void
	init() {
		controller = new LabelListController();
	}
	
	@Test public void
	activationSpecification() {
		assertTrue( "activation", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/labels" ).build() ) );

		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( null ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/label" ).build() ) );
	}
	
	@Test public void
	injectAllTheSpecsInTheView() throws Exception {
		ListRenderer viewMock = mock( ListRenderer.class );
		controller.setRenderer( viewMock );
		Writer writerMock = mock( Writer.class );
		
		List<Spec> specs = new ArrayList<Spec>();
		specs.add( aSpec().withTitle( "sample" ).build() );
		SpecRepository repoMock = mock( SpecRepository.class );
		when( repoMock.getSpecs( SpecMatcher.all()) ).thenReturn( specs );
		
		controller.handle( null, repoMock, writerMock );
		verify( repoMock ).getSpecs( SpecMatcher.all() );
		verify( viewMock ).setSpecs( specs );
		verify( viewMock ).render( writerMock );
	}
}
