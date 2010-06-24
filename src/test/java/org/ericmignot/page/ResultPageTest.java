package org.ericmignot.page;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.ericmignot.TryThisCode;
import org.junit.Before;
import org.junit.Test;

public class ResultPageTest {

	private ResultPage resultPage;
	private TryThisCode launcher;
	
	@Before public void
	init() {
		resultPage = new ResultPage( "sample" , "git://github.com/testaddict/mastermind.git" );
		launcher = resultPage.getLauncher();
	}
	
	@Test public void
	launcherConfiguration() {
		assertNotNull( launcher );
		assertEquals( "se", "sample", launcher.getSe() );
		assertEquals( "repo", "git://github.com/testaddict/mastermind.git", launcher.getGitRepository() );
		assertEquals( "chrono", resultPage.getChrono(), launcher.getChrono() );
	}
	
	@Test public void
	contentTargetsOutput() {
		resultPage.setRunnerDirectory( "toto/" );
		String expectedContent = launcher.getRunnerDirectory() + launcher.getExecutionOutputDirectory() + "/sample.html";
		assertEquals( expectedContent, resultPage.getSecondColumn().getContent() );
	}
	
}
