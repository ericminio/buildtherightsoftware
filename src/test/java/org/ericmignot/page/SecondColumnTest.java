package org.ericmignot.page;

import static org.mockito.Mockito.mock;
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
	readsContentFromFile() throws IOException {
		FileReader fileReaderMock = mock(FileReader.class);
		secondColumn.setFileReader(fileReaderMock);
		
		secondColumn.html();
		verify(fileReaderMock).readFile( SecondColumn.FILE_NAME );
	}
}
