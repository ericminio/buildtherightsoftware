package org.ericmignot.core;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class MavenCompilerTest {

	@Before public void
	copyResources() throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("mvn resources:testResources", null, new File(".") );
		process.waitFor();
	}
	
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
	
	@Test public void
	generatesACoberturaReport() {
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
		
		File classes = new File( pomDirectory + "target/site/cobertura/frame-summary.html" );
		assertTrue( "classes exist", classes.exists() );   
	}
}
