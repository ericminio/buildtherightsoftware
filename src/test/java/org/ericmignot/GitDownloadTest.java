package org.ericmignot;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class GitDownloadTest {

	@Test public void
	canCloneGitRepository() throws IOException, InterruptedException {
		GitDownload fetcher = new GitDownload();
		
		fetcher.setCloneNumber( "1" ); 
		fetcher.fetch( "git://github.com/testaddict/mastermind.git" );
		
		File mastermind = new File( "target/clones/1/mastermind" );
		assertTrue(mastermind.isDirectory());
	}
}
