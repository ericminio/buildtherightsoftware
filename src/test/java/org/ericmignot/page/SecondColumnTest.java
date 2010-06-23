package org.ericmignot.page;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.ericmignot.util.FileReader;
import org.junit.Before;
import org.junit.Test;


public class SecondColumnTest {

	private SecondColumn secondColumn;
	
	@Before public void
	init() {
		secondColumn = new SecondColumn();
	}
	
	@Test public void
	dontDisplayContentWhenNoContentIsProvided() throws IOException {
		FileReader fileReaderMock = mock(FileReader.class);
		secondColumn.setFileReader(fileReaderMock);
	
		secondColumn.html();
		verify(fileReaderMock, never()).readFile(null);
	}
	
	@Test public void
	containsCodeSubmisionInvitation() throws IOException {
		String content = secondColumn.html();
		
		assertThat( "rule for section", content, containsString("</textarea>"));
		assertThat( "rule for section", content, containsString("Try this code"));
	}
}
