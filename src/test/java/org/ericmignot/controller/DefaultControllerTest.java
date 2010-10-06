package org.ericmignot.controller;

import static org.ericmignot.util.RepositoryMockBuilder.aRepo;
import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.ericmignot.util.matchers.SpecMatcher.isASpec;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.SpecRenderer;
import org.junit.Before;
import org.junit.Test;


public class DefaultControllerTest {

	private DefaultController controller;
	private SpecRepository repository;
	
	@Before public void
	init() {
		controller = new DefaultController();
		repository = aRepo().withSpec( aSpec().withTitle( "sample" ).build() ).build();
	}
	
	@Test public void
	triggersSamplePageRendering() {
		SpecRenderer viewMock = mock( SpecRenderer.class );
		controller.setRenderer( viewMock );
		
		controller.handle(null, repository, mock( Writer.class ) );
		verify( viewMock ).setSpec( argThat( isASpec().withTitle( "sample" ) ));
	}
}
