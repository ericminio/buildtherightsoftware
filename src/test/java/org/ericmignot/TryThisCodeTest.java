package org.ericmignot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TryThisCodeTest {

	private TryThisCode tryThisCode;
	
	@Before public void
	init() {
		tryThisCode = new TryThisCode();
	}
	
	@Test public void
	extractProjectNameFromGitUrl() {
		assertEquals( "mastermind", tryThisCode.extractProjectName("git://github.com/testaddict/mastermind.git") );
	}
	
	@Test public void 
	withBothSeAndGithubSamples() throws IOException, InterruptedException {
		tryThisCode.setSe( "sample" );
		tryThisCode.setChrono( "test-deploy" );
		tryThisCode.setGitRepository("git://github.com/testaddict/mastermind.git");
		tryThisCode.go();

		File out = new File("specs/test-deploy/mastermind/se/out/sample.html");
		assertTrue( "se output", out.exists() );
	}
}
