package org.ericmignot.page;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.ericmignot.core.TryThisCode;
import org.junit.Before;
import org.junit.Test;

public class ExecutePageTest {

	private ExecutePage page;
	private TryThisCode launcher;
	
	@Before public void
	init() {
		page = new ExecutePage( "sample" , "git://github.com/testaddict/mastermind.git" );
		launcher = page.getLauncher();
	}
	
	@Test public void
	launcherConfiguration() {
		assertNotNull( launcher );
		assertEquals( "se", "sample", launcher.getSpecX() );
		assertEquals( "repo", "git://github.com/testaddict/mastermind.git", launcher.getGitRepository() );
		assertEquals( "chrono", page.getChrono(), launcher.getChrono() );
	}
	
	@Test public void
	resultFilePath() {
		assertEquals( "result file", "specs/runs/" + page.getChrono() + "/mastermind/se/out/sample.html", page.getFilePathToBeIncluded() );
	}
	
	@Test public void
	launchSpecExecutionWhenRenderIsCalled() throws IOException, InterruptedException {
		TryThisCode launcherMock = mock(TryThisCode.class);
		when(launcherMock.getDirectory()).thenReturn("target/test-classes/test-page-result");
		when(launcherMock.getCompilerDirectory()).thenReturn("target/test-classes/test-page-result");
		when(launcherMock.getExecutionOutputDirectory()).thenReturn("");
		when(launcherMock.getSpecX()).thenReturn("sample");
		page.setLauncher(launcherMock);
		
		page.content();
		verify(launcherMock).go();
	}
	
	@Test public void
	coberturaReportFile() {
		assertEquals( "cobertura report file", "specs/runs/" + page.getChrono() + "/mastermind/target/site/cobertura/frame-summary.html", page.getCoberturaReportPath() );
	}
	
}
