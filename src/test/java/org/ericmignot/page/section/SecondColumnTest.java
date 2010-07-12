package org.ericmignot.page.section;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.ericmignot.util.FileReader;
import org.junit.Before;
import org.junit.Test;


public class SecondColumnTest {

	private SecondColumn section;
	
	@Before public void
	init() {
		section = new SecondColumn();
	}
	
	@Test public void
	containsModifyButton() {
		FileReader fileReaderMock = mock(FileReader.class);
		section.setFileReader(fileReaderMock);
		section.setContent( "specs/tutu.html" );
		String content = section.html();
		
		assertThat( "modify link", content, containsString( "<a name=modifyLink href=/specs/modify/tutu" ));
	}
	
	@Test public void
	displayGivenContent() throws IOException {
		FileReader fileReaderMock = mock(FileReader.class);
		section.setFileReader(fileReaderMock);
		section.setContent( "toto" );
		
		section.html();
		verify(fileReaderMock).readFile( "toto" );
	}
	
	@Test public void
	dontDisplayContentWhenNoContentIsProvided() throws IOException {
		FileReader fileReaderMock = mock(FileReader.class);
		section.setFileReader(fileReaderMock);
	
		section.html();
		verify(fileReaderMock, never()).readFile(null);
	}
	
	@Test public void
	containsCodeSubmisionInvitation() throws IOException {
		String content = section.html();
		
		assertThat( "git repo field", content, containsString("</textarea>"));
		assertThat( "submit code button", content, containsString("Try this code"));
	}
	
	@Test public void
	canExtractSpecXFromContent() {
		section.setContent( "toto" );
		assertThat( "specX extraction", section.getSpecX(), equalTo( "toto" ));
		
		section.setContent( "sepcs/toto.html" );
		assertThat( "specX extraction", section.getSpecX(), equalTo( "toto" ));
	}
}
