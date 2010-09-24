package org.ericmignot.store;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.ericmignot.adapters.Spec;
import org.ericmignot.util.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FileRepositoryTest {

	private SpecFileStore repository = new SpecFileStore( "test-directory" );
	private final File dir = new File( "test-directory" );
	
	@Before public void
	createTestDirectory() {
		dir.mkdir();
	}
	
	@After public void
	cleanAndDeleteDirectory() {
		File[] files = dir.listFiles();
		for (File file : files) {
			file.delete();
		}
		dir.delete();
	}

	@Test public void
	createsAFileWithSpecTitleAsFilenameAndHtmlExtensionWhenSavingASpec() {
		Spec spec = aSpec().withTitle( "spec-title" ).build();
		repository.saveSpec( spec );
		assertTrue( new File( "test-directory/spec-title.html" ).exists() );
	}
	
	@Test public void
	savesTheSpecContentInTheFileWithHtmlExtension() {
		Spec spec = aSpec().withTitle( "title" ).withContent( "my test content" ).build();
		repository.saveSpec( spec );
		assertEquals( "my test content", new FileUtils().readFile( "test-directory/title.html" ) );
	}
	
	@Test public void
	createsAFileWithSpecTitleAsFilenameAndLabelExtensionWhenSavingASpec() {
		Spec spec = aSpec().withTitle( "spec-title" ).build();
		repository.saveSpec( spec );
		assertTrue( new File( "test-directory/spec-title.label" ).exists() );
	}
	
	@Test public void
	savesTheSpecLabelInTheFileWithLabelExtension() {
		Spec spec = aSpec().withTitle( "title" ).withLabel( "my test label" ).build();
		repository.saveSpec( spec );
		assertEquals( "my test label", new FileUtils().readFile( "test-directory/title.label" ) );
	}
	
	@Test public void
	canRetrieveASpec() {
		Spec spec = aSpec().withTitle( "title" ).withContent( "my test content" ).withLabel( "my test label" ).build();
		repository.saveSpec( spec );
		assertEquals( "title", repository.getSpecByTitle( "title" ).getTitle() );
		assertEquals( "my test content", repository.getSpecByTitle( "title" ).getContent() );
		assertEquals( "my test label", repository.getSpecByTitle( "title" ).getLabel() );
	}
	
}
