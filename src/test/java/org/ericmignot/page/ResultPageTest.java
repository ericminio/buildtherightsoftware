package org.ericmignot.page;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.ericmignot.core.TryThisCode;
import org.ericmignot.util.FileReader;
import org.junit.Before;
import org.junit.Test;

public class ResultPageTest {

	private ResultPage page;
	private TryThisCode launcher;
	private FileReader fileReaderMock;
	
	@Before public void
	init() {
		page = new ResultPage( "sample" , "git://github.com/testaddict/mastermind.git" );
		launcher = page.getLauncher();
		fileReaderMock = mock(FileReader.class);
		page.setFileReader(fileReaderMock);
	}
	
	@Test public void
	launcherConfiguration() {
		assertNotNull( launcher );
		assertEquals( "se", "sample", launcher.getSe() );
		assertEquals( "repo", "git://github.com/testaddict/mastermind.git", launcher.getGitRepository() );
		assertEquals( "chrono", page.getChrono(), launcher.getChrono() );
	}
	
	@Test public void
	containsModifyLink() throws IOException {
		String content = page.pageContent();
		assertThat( "modify link", content, containsString( "<a name=modifyLink href=/specs/modify/sample" ));
	}
	
	@Test public void
	containsSpecExecutionResult() throws IOException {
		page.setRunnerDirectory( "toto/" );
		String expectedContent = launcher.getRunnerDirectory() + launcher.getExecutionOutputDirectory() + "/sample.html";
		
		page.pageContent();
		verify( fileReaderMock ).readFile( expectedContent );
	}
	
	@Test public void
	containsCodeSubmissionInvitation() throws IOException {
		page.pageContent();
		verify(fileReaderMock).readFile( "target/html/invitation.html" );
	}
	
}
