package org.ericmignot;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

public class GitDownloadTest {

	@Test public void
	canCloneGitRepository() throws IOException, InterruptedException {
		GitDownload fetcher = new GitDownload();
		fetcher.setDirectory( "target/test-classes/test-git-donwload" );
		fetcher.fetch( "git://github.com/testaddict/mastermind.git" );
		
		File mastermind = new File( "target/test-classes/test-git-donwload/mastermind" );
		assertTrue( mastermind.isDirectory() );
	}
}
