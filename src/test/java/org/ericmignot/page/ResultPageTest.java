package org.ericmignot.page;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.ericmignot.TryThisCode;
import org.junit.Before;
import org.junit.Test;

public class ResultPageTest {

	private ResultPage resultPage;
	
	@Before public void
	init() {
		resultPage = new ResultPage( "sample" , "git://github.com/testaddict/mastermind.git" );
		assertNotNull( resultPage.getLauncher() );
	}
	
	@Test public void
	executeSeBeforeDisplay() throws IOException, InterruptedException {
		TryThisCode launcherMock = mock( TryThisCode.class );
		resultPage.setLauncher( launcherMock );
		
		resultPage.launch();
		verify( launcherMock ).setSe( "sample" );
		verify( launcherMock ).setGitRepository( "git://github.com/testaddict/mastermind.git" );
		verify( launcherMock ).setChrono( anyString() );
		verify( launcherMock ).go();
	}
	
	@Test public void
	contentIsExecutionOutput() {
		resultPage.launch();
		String chrono = resultPage.getChrono();
		String expectedContent = "specs/" + chrono + "/mastermind/se/out/sample.html";
		assertEquals( expectedContent, resultPage.getSecondColumn().getContent() );
	}
	
	@Test public void
	displayPageContent() {
		SecondColumn secondColumnMock = mock(SecondColumn.class);
		resultPage.setSecondColumn(secondColumnMock);
		
		resultPage.html();
		verify(secondColumnMock).html();
	}
}
