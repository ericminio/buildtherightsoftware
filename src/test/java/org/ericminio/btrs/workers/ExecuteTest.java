package org.ericminio.btrs.workers;

import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.ericminio.btrs.workers.Execution;
import org.ericminio.btrs.workers.GitPuller;
import org.ericminio.btrs.workers.GreenPepperRunner;
import org.ericminio.btrs.workers.MavenCompiler;
import org.junit.Before;
import org.junit.Test;

public class ExecuteTest {

	private Execution action;
	
	@Before public void
	init() {
		action = new Execution();
		action.setWorkingDirectory( "working-directory" );
		action.setChrono( "test-chrono" );
		action.setSpec( aSpec().withTitle( "sample" ).build() );
		action.setGitUrl( "git://github.com/testaddict/mastermind.git" );
	}
	
	@Test public void
	fetchFromGitThenCompileAndFinnalyRunTheSpecWhenExecuted() throws Exception {
		GitPuller gitDownloadMock = mock( GitPuller.class );
		action.setGitPuller( gitDownloadMock );
		
		MavenCompiler compilerMock = mock( MavenCompiler.class );
		action.setCompiler( compilerMock );
		
		GreenPepperRunner runnerMock = mock( GreenPepperRunner.class );
		action.setRunner( runnerMock );
		
		action.work();
		verify( gitDownloadMock ).setWorkingDirectory( "working-directory/runs/test-chrono" );
		verify( gitDownloadMock ).setUrl( "git://github.com/testaddict/mastermind.git" );
		verify( gitDownloadMock ).work();
		verify( compilerMock ).setWorkingDirectory( "working-directory/runs/test-chrono/mastermind" );
		verify( compilerMock ).work();
		verify( runnerMock ).setWorkingDirectory( "working-directory/" );
		verify( runnerMock ).setSpecFileRelativeFile( "sample.html" );
		verify( runnerMock ).setClassesRelativeDirectory( "runs/test-chrono/mastermind/target/classes" );
		verify( runnerMock ).setOutputRelativeDirectory( "runs/test-chrono/mastermind/se/out" );
		verify( runnerMock ).work();
	}
	
}
