package org.ericmignot.store;

import static org.ericmignot.util.FileUtils.readFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LabelMigrationTest {

	private LabelMigration migration;
	private String workingDirectory = "target/test-classes/test-label-migration";
	
	@Before public void
	initDirectory() {
		migration = new LabelMigration();
		rmDir( workingDirectory );
		new File( workingDirectory ).mkdir();
	}
	
	@After public void
	cleanDirectory() {
		rmDir( workingDirectory );
	}
	
	protected void rmDir(String dir) {
		String[] files = new File( dir ).list();
		if (files != null) {
			for (String fileName : files) {
				if ( new File( dir + "/" + fileName ).isDirectory() ) {
					rmDir( dir + "/" + fileName );
				}
				new File( dir + "/" + fileName ).delete();
			}
		}
		new File( dir ).delete();
	}
	
	@Test public void
	createTheMissingLabelForASingleFile() throws IOException {
		new File( workingDirectory + "/toto.html").createNewFile();
		migration.setWorkingDirectory( workingDirectory );
		
		migration.work();
		List<String> files = Arrays.asList( new File( workingDirectory ).list() );
		assertEquals( 2, files.size() );
		assertTrue( files.contains( "toto.label" ) );
		assertTrue( files.contains( "toto.label" ) );
		
	}
	
	@Test public void
	doesNothingIfTheLabelFileExists() throws IOException {
		new File( workingDirectory + "/toto.html").createNewFile();
		new File( workingDirectory + "/toto.label").createNewFile();
		migration.setWorkingDirectory( workingDirectory );
		migration.work();
		List<String> files = Arrays.asList( new File( workingDirectory ).list() );
		assertEquals( 2, files.size() );
		assertTrue( files.contains( "toto.html" ) );
		assertTrue( files.contains( "toto.label" ) );
	}
	
	
	@Test public void
	dontTouchAnExistingLabelFile() throws IOException {
		new File( workingDirectory + "/toto.html").createNewFile();
		
		File label = new File( workingDirectory + "/toto.label" );
		label.createNewFile();
		PrintWriter out
		   = new PrintWriter(new BufferedWriter(new FileWriter( label )));
		out.print( "a label that I don't want to lose" );
		out.flush();
		
		migration.setWorkingDirectory( workingDirectory );
		migration.work();
		assertEquals( "a label that I don't want to lose", readFile( workingDirectory + "/toto.label" ) );
	}
	
	@Test public void
	dontCreateLabelFileForOtherFileThanHtml() throws IOException {
		new File( workingDirectory + "/toto.html").createNewFile();
		new File( workingDirectory + "/titi.html").createNewFile();
		new File( workingDirectory + "/jar.jar").createNewFile();
		
		migration.setWorkingDirectory( workingDirectory );
		migration.work();
		
		List<String> files = Arrays.asList( new File( workingDirectory ).list() );
		assertEquals( 5, files.size() );
		assertTrue( files.contains( "toto.label" ) );
		assertTrue( files.contains( "titi.label" ) );
	}
}
