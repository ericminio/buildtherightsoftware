package org.ericmignot.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.ericmignot.util.GitUtils.git;

public class GitTest {

	@Test public void
	canExtractGitRepositoryFromAGitUrl() {
		assertEquals( "mastermind", git("git://github.com/testaddict/mastermind.git").extractRepositoryName() );
		assertEquals( "toto", git("git://github.com/testaddict/toto.git").extractRepositoryName() );
	}
	
	@Test public void
	returnsNullIfProjectNameCannotBeExtracted() {
		assertNull( git("git://github.com").extractRepositoryName() );
	}
	
}
