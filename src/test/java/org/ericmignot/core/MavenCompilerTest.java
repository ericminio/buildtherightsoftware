package org.ericmignot.core;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class MavenCompilerTest {

	@Before public void
	copyResources() throws IOException, InterruptedException {
		rmDir( "target/test-classes/test-compilation/mastermind/target" );
		Process process = Runtime.getRuntime().exec("mvn resources:testResources", null, new File(".") );
		process.waitFor();
		
		String pomDirectory = "target/test-classes/test-compilation/mastermind/";
		MavenCompiler compiler = new MavenCompiler();
		compiler.setWorkingDirectory( pomDirectory );
		compiler.work();
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
	compilesAndCreateCoberturaReport()  {
		assertTrue( "classes exist", new File( "target/test-classes/test-compilation/mastermind/target/classes" ).exists() );   
		assertTrue( "cobertura report exist", new File( "target/test-classes/test-compilation/mastermind/target/site/cobertura/frame-summary.html" ).exists() );   
	}  
	
}
