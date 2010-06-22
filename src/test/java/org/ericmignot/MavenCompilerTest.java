package org.ericmignot;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class MavenCompilerTest {

	@Test public void
	canCompile() throws IOException, InterruptedException {
		MavenCompiler compiler = new MavenCompiler();
		
		compiler.setDirectory( "target/test-classes/test-compilation/mastermind" );
		compiler.mavenCleanAndCompile();
		
		File classes = new File( "target/test-classes/test-compilation/mastermind/target/classes");
		assertTrue( "classes exist", classes.isDirectory() );
	}
}
