package org.ericmignot.domain;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.ericmignot.domain.MavenCompiler;
import org.junit.Before;
import org.junit.Test;
import static org.ericmignot.util.FileUtils.removeDir;

public class MavenCompilerTest {

	@Before public void
	copyResources() throws IOException, InterruptedException {
		removeDir( "target/test-classes/test-compilation/mastermind/target" );
		Process process = Runtime.getRuntime().exec("mvn resources:testResources", null, new File(".") );
		process.waitFor();
		
		String pomDirectory = "target/test-classes/test-compilation/mastermind/";
		MavenCompiler compiler = new MavenCompiler();
		compiler.setWorkingDirectory( pomDirectory );
		compiler.work();
	}
	
	@Test public void
	compilesAndCreateCoberturaReport()  {
		assertTrue( "classes exist", new File( "target/test-classes/test-compilation/mastermind/target/classes" ).exists() );   
		assertTrue( "cobertura report exist", new File( "target/test-classes/test-compilation/mastermind/target/site/cobertura/frame-summary.html" ).exists() );   
	}  
	
}
