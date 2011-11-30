package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.HttpServletRequestStubBuilder.aStubRequest;
import static org.ericminio.btrs.domain.matchers.CoreMatchers.all;
import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.ericminio.btrs.application.route.controllers.LabelListController;
import org.ericminio.btrs.application.view.ListRenderer;
import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecRepository;
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
		when( repoMock.getSpecs( all()) ).thenReturn( specs );
		
		controller.handle( null, repoMock, writerMock );
		verify( repoMock ).getSpecs( all() );
		verify( viewMock ).setSpecs( specs );
		verify( viewMock ).render( writerMock );
	}
}
