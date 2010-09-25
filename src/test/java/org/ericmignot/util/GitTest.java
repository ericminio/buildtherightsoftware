package org.ericmignot.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GitTest {

	@Test public void
	canExtractGitRepositoryFromAGitUrl() {
		assertEquals( "mastermind", GitUtils.extractGitRepositoryName( "git://github.com/testaddict/mastermind.git" ) );
		assertEquals( "toto", GitUtils.extractGitRepositoryName( "git://github.com/testaddict/toto.git" ) );
	}
}