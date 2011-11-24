package org.ericmignot.util;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FileUtilsTest {

	@Test public void
	returnsNullWhenReadingAFileThatDoesNotExist() {
		assertNull( FileUtils.readFile( "target/test-read-file/a-file" ) );
	}
	
	@Test public void
	canSaveAFile() throws IOException {
		FileUtils.saveContentInFile( "toto", "target/test-save" );
		assertEquals( "toto", FileUtils.readFile( "target/test-save" ) );
		new File( "target/test-save" ).delete();
	}
	
	@Test public void
	canRemoveAnEmptyDirectory() throws IOException {
		new File( "target/test-remove-directory" ).mkdir();
		assertTrue( new File( "target/test-remove-directory" ).exists() );
		FileUtils.removeDir( "target/test-remove-directory" );
		assertFalse( new File( "target/test-remove-directory" ).exists() );
	}
	
	@Test public void
	canRemoveADirectoryContainingAFile() throws IOException {
		new File( "target/test-remove-directory" ).mkdir();
		FileUtils.saveContentInFile( "content", "target/test-remove-directory/a-file" );
		FileUtils.removeDir( "target/test-remove-directory" );
		assertFalse( new File( "target/test-remove-directory" ).exists() );
	}
	
	@Test public void
	canRemoveADirectoryContainingAFileAndASubDirectory() throws IOException {
		new File( "target/test-remove-directory" ).mkdir();
		FileUtils.saveContentInFile( "content", "target/test-remove-directory/a-file" );
		new File( "target/test-remove-directory/a-directory" ).mkdir();
		
		FileUtils.removeDir( "target/test-remove-directory" );
		assertFalse( new File( "target/test-remove-directory" ).exists() );
	}
}
