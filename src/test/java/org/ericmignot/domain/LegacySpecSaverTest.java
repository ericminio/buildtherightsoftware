package org.ericmignot.domain;

import static org.ericmignot.util.FileUtils.readFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class LegacySpecSaverTest {

	private LegacySpecSaver saver;
	
	@Before public void
	init() {
		saver = new LegacySpecSaver();
	}
	
	@Test public void
	modifiesTheSpecFileContent() throws IOException {
		saver.setDirectory( "target/test-classes/test-save-spec/" );
		saver.saveContent( "sample", "toto" );
		
		String fileContent = readFile( "target/test-classes/test-save-spec/sample.html" );
		assertThat( "spec saved", fileContent, equalTo( "toto" ));
	}
	
	@Test public void
	modifiesTheSpecLabel() throws IOException {
		saver.setDirectory( "target/test-classes/test-save-spec/" );
		saver.saveLabel( "sample", "code" );
		
		String fileContent = readFile( "target/test-classes/test-save-spec/sample.label" );
		assertThat( "spec label saved", fileContent, equalTo( "code" ));
	}
	
	@Test public void
	supportsEmptyLabel() throws IOException {
		saver.setDirectory( "target/test-classes/test-save-spec/" );
		saver.saveLabel( "empty-label", "" );
		readFile( "target/test-classes/test-save-spec/empty-label.label" );
	}

}
