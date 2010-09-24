package org.ericmignot.domain;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.ericmignot.domain.GitPuller;
import org.junit.Before;
import org.junit.Test;

public class GitDownloadTest {

	@Before public void
	init() {
		rmDir( "target/test-classes/test-git-donwload/mastermind" );
	}
	
	@Test public void
	canCloneARemoteGitRepository() {
		GitPuller fetcher = new GitPuller();
		fetcher.setWorkingDirectory( "target/test-classes/test-git-donwload" );
		fetcher.setGitUrl( "git://github.com/testaddict/mastermind.git" );
		fetcher.work();
		
		File mastermind = new File( "target/test-classes/test-git-donwload/mastermind" );
		assertTrue( "git clone", mastermind.isDirectory() ); 
	}
	
	protected void rmDir(String dir) {
		String[] files = new File( dir ).list();
		if (files != null) {
			for (String path : files) {
				new File( path ).delete();
			}
		}
		new File( dir ).delete();
	}
	
}
