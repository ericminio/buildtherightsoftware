package org.ericminio.btrs.workers;

import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
		execution.setSourceRepositoryUrl( "git://github.com/testaddict/mastermind.git" );
	}
	
	@Test public void
	setsGitPullerWhenRepositoryUrlPointsAtGithub() {
		execution.setSourceRepositoryUrl( "git://github.com/testaddict/mastermind.git" );
		assertTrue( execution.getSourcePuller() instanceof GitPuller );
	}
	
	@Test public void
	setsMercurialPullerWhenRepositoryUrlPointsAtBitbuket() {
		execution.setSourceRepositoryUrl( "https://bitbucket.org/ericminio/bowling-kata" );
		assertTrue( execution.getSourcePuller() instanceof MercurialPuller );
	}
	
	@Test public void
	reSetsGitPullerWhenRepositoryUrlPointsAtGithub() {
		execution.setSourceRepositoryUrl( "https://bitbucket.org/ericminio/bowling-kata" );
		execution.setSourceRepositoryUrl( "git://github.com/testaddict/mastermind.git" );
		assertTrue( execution.getSourcePuller() instanceof GitPuller );
	}
	
	@Test public void
	sourcePullerCanBeConfigured() {
		SourcePuller pullerMock = mock( SourcePuller.class );
		execution.setSourcePuller( pullerMock );
		assertThat( execution.getSourcePuller(), equalTo( pullerMock ) );
	}
	
	@Test public void
	compilerCanBeConfigured() {
		Compiler compilerMock = mock( Compiler.class );
		execution.setCompiler( compilerMock );
		assertThat( execution.getCompiler(), equalTo( compilerMock ) );
	}
	
	@Test public void
	specRunnerCanBeConfigured() {
		SpecRunner specRunnerMock = mock( SpecRunner.class );
		execution.setSpecRunner( specRunnerMock );
		assertThat( execution.getSpecRunner(), equalTo( specRunnerMock ) );
	}
	
	
	@Test public void
	fetchSourcesThenCompileAndFinnalyRunTheSpecWhenExecuted() throws Exception {
		SourcePuller pullerMock = mock( SourcePuller.class );
		execution.setSourcePuller( pullerMock );
		when(pullerMock.getRepositoryName()).thenReturn( "mastermind" );
		
		Compiler compilerMock = mock( Compiler.class );
		execution.setCompiler( compilerMock );
		
		SpecRunner specRunnerMock = mock( SpecRunner.class );
		execution.setSpecRunner( specRunnerMock );
		
		execution.work();
		verify( pullerMock ).setWorkingDirectory( "working-directory/runs/test-chrono" );
		verify( pullerMock ).setUrl( "git://github.com/testaddict/mastermind.git" );
		verify( pullerMock ).work();
		verify( pullerMock ).getRepositoryName();
		verify( compilerMock ).setWorkingDirectory( "working-directory/runs/test-chrono/mastermind" );
		verify( compilerMock ).work();
		verify( specRunnerMock ).setWorkingDirectory( "working-directory/" );
		verify( specRunnerMock ).setSpecFileRelativeFile( "sample.html" );
		verify( specRunnerMock ).setClassPathRelativeDirectory( "runs/test-chrono/mastermind/target/classes:runs/test-chrono/mastermind/target/test-classes" );
		verify( specRunnerMock ).setOutputRelativeDirectory( "runs/test-chrono/mastermind/se/out" );
		verify( specRunnerMock ).work();
	}
	
}
