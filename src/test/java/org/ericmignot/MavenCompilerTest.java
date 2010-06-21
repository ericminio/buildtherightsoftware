package org.ericmignot;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class MavenCompilerTest {

	@Before public void
	downloadGitRepository() throws IOException, InterruptedException {
		GitDownload fetcher = new GitDownload();
		fetcher.setCloneNumber( "2" ); 
		fetcher.fetch( "git://github.com/testaddict/mastermind.git" );
	}
	
	@Test public void
	canCompile() throws IOException, InterruptedException {
		MavenCompiler compiler = new MavenCompiler();
		
		compiler.setCloneNumber("2");
		compiler.setRepositoryName("mastermind");
		
		compiler.mavenCleanAndCompile();
		
		File classes = new File( "target/clones/2/mastermind/target/classes");
		assertTrue( "classes exist", classes.isDirectory() );
	}
}
