package org.ericmignot.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.ericmignot.util.PageFileReader;
import org.junit.Before;
import org.junit.Test;

public class SpecSaverTest {

	private SpecSaver saver;
	
	@Before public void
	init() {
		saver = new SpecSaver();
	}
	
	@Test public void
	modifiesTheSpecFileContent() throws IOException {
		saver.setDirectory( "target/test-classes/test-save-spec/" );
		saver.save( "sample", "toto" );
		
		String fileContent = new PageFileReader().readFile( "target/test-classes/test-save-spec/sample.html" );
		assertThat( "spec saved", fileContent, equalTo( "toto" ));
	}
}
