package org.ericmignot.application;

import static org.ericmignot.util.FileUtils.removeDir;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.ericmignot.domain.GitPuller;
import org.junit.Before;
import org.junit.Test;


public class GitHubPresentTest {

	@Before public void
	init() {
		removeDir( "target/test-classes/test-git-donwload/mastermind" );
	}
	
	@Test public void
	canCloneARemoteGitRepository() {
		GitPuller fetcher = new GitPuller();
		fetcher.setWorkingDirectory( "target/test-classes/test-git-donwload" );
		fetcher.setUrl( "git://github.com/testaddict/mastermind.git" );
		fetcher.work();
		
		File mastermind = new File( "target/test-classes/test-git-donwload/mastermind" );
		assertTrue( "git clone", mastermind.isDirectory() ); 
	}
}
