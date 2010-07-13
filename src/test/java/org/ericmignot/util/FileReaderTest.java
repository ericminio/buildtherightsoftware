package org.ericmignot.util;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class FileReaderTest {

	private PageFileReader fileReader;
	
	@Before public void
	init() {
		fileReader = new PageFileReader();
	}
	
	@Test public void
	canReadInvitationFileSection() throws IOException {
		String content = fileReader.readFile( "target/html/invitation.html" );
		assertThat( content, containsString( "Can you make it ?" ));
	}
}
