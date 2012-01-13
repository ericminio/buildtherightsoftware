package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.HttpServletRequestStubBuilder.aStubRequest;
import static org.ericminio.btrs.application.route.controllers.RepositoryMockBuilder.aRepo;
import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.route.controllers.ExecutionController;
import org.ericminio.btrs.application.view.pages.ResultPage;
import org.ericminio.btrs.domain.SourcePuller;
import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecRepository;
import org.ericminio.btrs.workers.Execution;
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
		
		requestMock = aStubRequest().withThisUri( "/specs/execute/sample" )
									.withThisPostParameter( "repo", "git://github.com/testaddict/mastermind.git" )
					  .build(); 
	}
	
	@Test public void
	activationSpecification() {
		assertTrue( "activation", controller.isActivatedBy( 
				aStubRequest().withThisUri( "/specs/execute/sample" )
				.withThisGetParameter( "repo", "git://github.com/testaddict/mastermind.git" ).build() ) );
		assertTrue( "activation", controller.isActivatedBy( 
				aStubRequest().withThisUri( "/specs/execute/sample" )
				.withThisGetParameter( "repo", "" ).build() ) );

		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( null ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/execute" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/execute/" ).build() ) );
		assertFalse( "activate", controller.isActivatedBy( aStubRequest().withThisUri( "/specs/execute/toto" ).build() ) );
	}

	@Test public void
	launchesExecutionAndRendersResult() throws Exception {
		Execution executeMock = mock( Execution.class );
		controller.setExecution( executeMock );
		SourcePuller pullerMock = mock( SourcePuller.class );
		when( pullerMock.getRepositoryName() ).thenReturn( "mastermind" );
		when( executeMock.getSourcePuller() ).thenReturn( pullerMock );
		
		ResultPage resultPageMock = mock( ResultPage.class );
		controller.setRenderer( resultPageMock );
		
		Writer writerMock = mock( Writer.class );
		
		controller.setWorkingDirectory( "target/test-classes/test-system" );
		controller.handle( requestMock, repoMock, writerMock );
		verify( executeMock ).setWorkingDirectory( "target/test-classes/test-system" );
		verify( executeMock ).setChrono( Mockito.anyString() );
		verify( executeMock ).setSpec( spec );
		verify( executeMock ).setSourceRepositoryUrl( "git://github.com/testaddict/mastermind.git" );
		verify( executeMock ).work();
		verify( resultPageMock ).setWorkingDirectory( "target/test-classes/test-system" );
		verify( resultPageMock ).setChrono( Mockito.anyString() );
		verify( resultPageMock ).setSpec( spec );
		verify( resultPageMock ).setGitRepositoryName( "mastermind" );
		verify( resultPageMock ).setSourceRepositoryUrl( "git://github.com/testaddict/mastermind.git" );
		verify( resultPageMock ).render( writerMock ); 
		
	}
	
}
