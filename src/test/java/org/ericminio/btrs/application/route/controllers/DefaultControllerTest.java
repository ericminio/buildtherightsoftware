package org.ericminio.btrs.application.route.controllers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.application.view.pages.WelcomePage;
import org.ericminio.btrs.domain.SpecRepository;
import org.junit.Before;
import org.junit.Test;

public class DefaultControllerTest {

	private DefaultController controller;
	private SpecRepository repository;
	
	@Before public void
	init() {
		controller = new DefaultController();
		repository = mock(SpecRepository.class);
	}
	
	@Test public void
	isAlwaysActivated() {
		assertThat( controller.isActivatedBy( null), is(true) );
	}
	
	@Test public void
	rendererIsTheWelcomePage() {
		assertThat( controller.getRenderer(), instanceOf(WelcomePage.class) );
	}
	
	@Test public void
	triggersRenderingWithoutInteractingWithSpecRepository() throws Exception {
		Renderer viewMock = mock( Renderer.class );
		controller.setRenderer( viewMock );
		
		Writer writerMock = mock( Writer.class );
		controller.handle(null, repository, writerMock );
		
		verify( repository, never() ).getSpecByTitle( anyString() );
		verify( viewMock ).render( writerMock );
	}
}
