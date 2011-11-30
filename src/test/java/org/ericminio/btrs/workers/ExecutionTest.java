package org.ericminio.btrs.workers;

import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.ericminio.btrs.domain.SourcePuller;
import org.ericminio.btrs.workers.Execution;
import org.ericminio.btrs.domain.SpecRunner;
import org.ericminio.btrs.domain.Compiler;
import org.junit.Before;
import org.junit.Test;

public class ExecutionTest {

	private Execution execution;
	
	@Before public void
	init() {
		execution = new Execution();
		execution.setWorkingDirectory( "working-directory" );
		execution.setChrono( "test-chrono" );
		execution.setSpec( aSpec().withTitle( "sample" ).build() );
		execution.setGitUrl( "git://github.com/testaddict/mastermind.git" );
	}
	
	@Test public void
	fetchFromGitThenCompileAndFinnalyRunTheSpecWhenExecuted() throws Exception {
		SourcePuller pullerMock = mock( SourcePuller.class );
		execution.setSourcePuller( pullerMock );
		
		Compiler compilerMock = mock( Compiler.class );
		execution.setCompiler( compilerMock );
		
		SpecRunner specRunnerMock = mock( SpecRunner.class );
		execution.setSpecRunner( specRunnerMock );
		
		execution.work();
		verify( pullerMock ).setWorkingDirectory( "working-directory/runs/test-chrono" );
		verify( pullerMock ).setUrl( "git://github.com/testaddict/mastermind.git" );
		verify( pullerMock ).work();
		verify( compilerMock ).setWorkingDirectory( "working-directory/runs/test-chrono/mastermind" );
		verify( compilerMock ).work();
		verify( specRunnerMock ).setWorkingDirectory( "working-directory/" );
		verify( specRunnerMock ).setSpecFileRelativeFile( "sample.html" );
		verify( specRunnerMock ).setClassesRelativeDirectory( "runs/test-chrono/mastermind/target/classes" );
		verify( specRunnerMock ).setOutputRelativeDirectory( "runs/test-chrono/mastermind/se/out" );
		verify( specRunnerMock ).work();
	}
	
}
