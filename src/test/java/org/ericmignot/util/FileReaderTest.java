package org.ericmignot.util;

import static org.ericmignot.util.FileUtils.readFile;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

public class FileReaderTest {

	@Test public void
	canReadInvitationFileSection() throws IOException {
		String content = readFile( "target/html/invitation.html" );
		assertThat( content, containsString( "Can you make it ?" ));
	}
}
