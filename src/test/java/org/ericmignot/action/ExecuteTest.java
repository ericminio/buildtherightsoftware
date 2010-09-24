package org.ericmignot.action;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.ericmignot.core.GitPuller;
import org.ericmignot.core.MavenCompiler;
import org.ericmignot.core.SpecRunner;
import org.junit.Before;
import org.junit.Test;

public class ExecuteTest {

	private Execute action;
	
	@Before public void
	init() {
		action = new Execute();
		action.setWorkingDirectory( "working-directory" );
		action.setChrono( "test-chrono" );
		action.setSpec( aSpec().withTitle( "sample" ).build() );
		action.setGitUrl( "git://github.com/testaddict/mastermind.git" );
	}
	
	@Test public void
	fetchFromGitThenCompileAndFinnalyRunTheSpecWhenExecuted() throws IOException, InterruptedException {
		GitPuller gitDownloadMock = mock( GitPuller.class );
		action.setGitPuller( gitDownloadMock );
		
		MavenCompiler compilerMock = mock( MavenCompiler.class );
		action.setCompiler( compilerMock );
		
		SpecRunner runnerMock = mock( SpecRunner.class );
		action.setRunner( runnerMock );
		
		action.work();
		verify( gitDownloadMock ).setWorkingDirectory( "working-directory/runs/test-chrono" );
		verify( gitDownloadMock ).setGitUrl( "git://github.com/testaddict/mastermind.git" );
		verify( gitDownloadMock ).work();
		verify( compilerMock ).setWorkingDirectory( "working-directory/runs/test-chrono/mastermind" );
		verify( compilerMock ).work();
		verify( runnerMock ).setWorkingDirectory( "working-directory/" );
		verify( runnerMock ).setSpecFileRelativeFile( "sample.html" );
		verify( runnerMock ).setClassesRelativeDirectory( "runs/test-chrono/mastermind/target/classes" );
		verify( runnerMock ).setOutputRelativeDirectory( "runs/test-chrono/mastermind/se/out" );
		verify( runnerMock ).work();
	}
	
	@Test public void
	canExtractGitRepositoryFromAGitUrl() {
		assertEquals( "mastermind", action.extractGitRepositoryName( "git://github.com/testaddict/mastermind.git" ) );
		assertEquals( "toto", action.extractGitRepositoryName( "git://github.com/testaddict/toto.git" ) );
	}
	
}
