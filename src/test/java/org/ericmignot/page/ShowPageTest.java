package org.ericmignot.page;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.ericmignot.util.FileReader;
import org.junit.Before;
import org.junit.Test;

public class ShowPageTest {

	private ShowPage page;
	private FileReader fileReaderMock;
	
	@Before public void
	init() throws IOException {
		page = new ShowPage( "a-spec-x" );
		fileReaderMock = mock(FileReader.class);
		page.setFileReader(fileReaderMock);
		
	}
	
	@Test public void
	specXIsStored() {
		assertThat( "spec-x stored", page.getSpecX(), equalTo( "a-spec-x" ) );
	}
	
	@Test public void
	defaultWorkingDirectory() {
		assertThat( "default working dir", page.getSpecXDirectory(), equalTo( ShowPage.DEFAULT_WORKING_DIRECTORY ) );
	}
	
	@Test public void
	workingDirectoryCanBeChanged() {
		page.setSpecXDirectory( "target/test-classes/test-system/" );
		assertThat( "working dir", page.getSpecXDirectory(), equalTo( "target/test-classes/test-system/" ) );
	}
	
	@Test public void
	containsModifyLink() throws IOException {
		String content = page.pageContent();
		assertThat( "modify link", content, containsString( "<a name=modifyLink href=/specs/modify/a-spec-x" ));
	}
	
	@Test public void
	containsSpecItself() throws IOException {
		page.pageContent();
		verify(fileReaderMock).readFile( "specs/a-spec-x.html" );
	}
	
	@Test public void
	containsCodeSubmissionInvitation() throws IOException {
		page.pageContent();
		verify(fileReaderMock).readFile( "target/html/invitation.html" );
	}
	
}
