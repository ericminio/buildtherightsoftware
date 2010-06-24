package org.ericmignot;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TryThisCodeTest {

	private TryThisCode tryThisCode;
	
	@Before public void
	init() {
		tryThisCode = new TryThisCode();
	}
	
	@Test public void
	defaultDirectoriesToBeUSed() {
		assertEquals( "runner", "specs/", tryThisCode.getRunnerDirectory() );
		
		tryThisCode.setChrono( "chrono-directory" );
		assertEquals( "fetcher", "specs/runs/chrono-directory", tryThisCode.getFetcherDirectory() );
		
		tryThisCode.setGitRepository( "git://github.com/testaddict/mastermind.git" );
		assertEquals( "compiler", "specs/runs/chrono-directory/mastermind", tryThisCode.getCompilerDirectory() );
		
		assertEquals( "system under test", "runs/chrono-directory/mastermind/target/classes", tryThisCode.getClassesRelativeDirectory() );
		assertEquals( "execution output", "runs/chrono-directory/mastermind/se/out", tryThisCode.getExecutionOutputDirectory() );
	}
	
	@Test public void
	extractProjectNameFromGitUrl() {
		assertEquals( "mastermind", tryThisCode.extractProjectName("git://github.com/testaddict/mastermind.git") );
	}
	
}
