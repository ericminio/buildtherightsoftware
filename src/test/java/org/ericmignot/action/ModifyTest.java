package org.ericmignot.action;

import static org.ericmignot.util.HttpServletRequestMockBuilder.aMockRequest;
import static org.ericmignot.util.RepositoryMockBuilder.aMockRepo;
import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import org.ericmignot.core.Spec;
import org.ericmignot.jetty.View;
import org.ericmignot.page.ModifyPage;
import org.ericmignot.store.Repository;
import org.junit.Before;
import org.junit.Test;

public class ModifyTest {

	private Modify action;
	
	@Before public void
	init() {
		action = new Modify();
	}
	
	@Test public void
	activationSpecification() {
		assertTrue( "activation", action.isActivatedBy( aMockRequest().withThisUri( "/specs/modify/sample" ).build() ) );
		assertTrue( "activation", action.isActivatedBy( aMockRequest().withThisUri( "/specs/modify/sample-calculator").build() ) );
		assertFalse( "don't activate", action.isActivatedBy( aMockRequest().withThisUri( "/" ).build() ) );
		assertFalse( "don't activate", action.isActivatedBy( aMockRequest().withThisUri( "" ).build() ) );
		assertFalse( "don't activate", action.isActivatedBy( aMockRequest().withThisUri( null ).build() ) );
		assertFalse( "don't activate", action.isActivatedBy( aMockRequest().withThisUri( "/specs/modify" ).build() ) );
		assertFalse( "don't activate", action.isActivatedBy( aMockRequest().withThisUri( "/specs/modify/" ).build() ) );
	}
	
	@Test public void
	theRenderingViewIsAModifyPage() {
		assertTrue( action.getView() instanceof ModifyPage );
	}
	
	@Test public void
	rendersTheViewDuringWork() {
		View viewMock = mock( View.class );
		action.setView( viewMock );
		Writer writerMock = mock( Writer.class );
		
		Spec spec = aSpec().withTitle( "sample" ).build();
		Repository repoMock = aMockRepo().withOneSpec( spec ).build();
		
		action.work( aMockRequest().withThisUri( "/specs/modify/sample").build(), repoMock, writerMock );
		verify( repoMock ).getSpecByTitle( "sample" );
		verify( viewMock ).render( spec, writerMock );
	}
}
