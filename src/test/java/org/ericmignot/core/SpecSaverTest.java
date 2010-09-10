package org.ericmignot.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
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
		saver.saveContent( "sample", "toto" );
		
		String fileContent = new PageFileReader().readFile( "target/test-classes/test-save-spec/sample.html" );
		assertThat( "spec saved", fileContent, equalTo( "toto" ));
	}
	
	@Test public void
	modifiesTheSpecLabel() throws IOException {
		saver.setDirectory( "target/test-classes/test-save-spec/" );
		saver.saveLabel( "sample", "code" );
		
		String fileContent = new PageFileReader().readFile( "target/test-classes/test-save-spec/sample.label" );
		assertThat( "spec label saved", fileContent, equalTo( "code" ));
	}
	
	@Test public void
	supportsEmptyLabel() throws IOException {
		saver.setDirectory( "target/test-classes/test-save-spec/" );
		saver.saveLabel( "empty-label", "" );
		new PageFileReader().readFile( "target/test-classes/test-save-spec/empty-label.label" );
	}

}
