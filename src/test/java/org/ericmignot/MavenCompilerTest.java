package org.ericmignot;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class MavenCompilerTest {

	@Test public void
	canCompile()  {
		MavenCompiler compiler = new MavenCompiler();
		
		compiler.setDirectory( "target/test-classes/test-compilation/mastermind" );
		try {
			compiler.mavenCleanAndCompile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File classes = new File( "target/test-classes/test-compilation/mastermind/target/classes");
		assertTrue( "classes exist", classes.isDirectory() );
	}
}
