package org.ericmignot.controller;

import static org.ericmignot.util.HttpServletRequestMockBuilder.aMockRequest;
import static org.ericmignot.util.RepositoryMockBuilder.aRepo;
import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.domain.Execution;
import org.ericmignot.page.ResultPage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ExecutionControllerTest {

	private ExecutionController controller;
	private Spec spec;
	private SpecRepository repoMock;
	private HttpServletRequest requestMock;
	
	@Before public void
	init() {
		controller = new ExecutionController();
		
		spec = aSpec().withTitle( "sample" ).build();
		repoMock = aRepo().withSpec( spec ).build();
		
		requestMock = aMockRequest().withThisUri( "/specs/execute/sample" )
									.withThisGitRepoParam( "git://github.com/testaddict/mastermind.git" )
					  .build(); 
	}
	
	@Test public void
	activationSpecification() {
		assertTrue( "activation", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/execute/sample" ).withThisGitRepoParam( "git://github.com/testaddict/mastermind.git" ).build() ) );

		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( null ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/execute" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/execute/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aMockRequest().withThisUri( "/specs/execute/toto" ).build() ) );
	}

	@Test public void
	launchesExecutionAndRendersResult() {
		Execution executeMock = mock( Execution.class );
		controller.setExecution( executeMock );
		
		ResultPage resultPageMock = mock( ResultPage.class );
		controller.setRenderer( resultPageMock );
		
		Writer writerMock = mock( Writer.class );
		
		controller.setWorkingDirectory( "target/test-classes/test-system" );
		controller.handle( requestMock, repoMock, writerMock );
		verify( executeMock ).setWorkingDirectory( "target/test-classes/test-system" );
		verify( executeMock ).setChrono( Mockito.anyString() );
		verify( executeMock ).setSpec( spec );
		verify( executeMock ).setGitUrl( "git://github.com/testaddict/mastermind.git" );
		verify( executeMock ).work();
		verify( resultPageMock ).setWorkingDirectory( "target/test-classes/test-system" );
		verify( resultPageMock ).setChrono( Mockito.anyString() );
		verify( resultPageMock ).setSpec( spec );
		verify( resultPageMock ).setGitRepositoryName( "mastermind" );
		verify( resultPageMock ).render( writerMock ); 
		
	}
	
}
