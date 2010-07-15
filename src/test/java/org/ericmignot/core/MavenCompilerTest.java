package org.ericmignot.core;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class MavenCompilerTest {

	@Test public void
	canCompile()  {
		String pomDirectory = "target/test-classes/test-compilation/mastermind/";
		MavenCompiler compiler = new MavenCompiler();
		compiler.setDirectory( pomDirectory );
		try {
			compiler.mavenCleanAndCompile();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		File classes = new File( pomDirectory + "target/classes" );
		assertTrue( "classes exist", classes.exists() );   
	}       
	
	@Test public void
	nopWhenDirectoryDoesNotExist() throws IOException, InterruptedException {
		MavenCompiler compiler = new MavenCompiler();
		compiler.setDirectory( "toto" );
		compiler.mavenCleanAndCompile();
	}
}
