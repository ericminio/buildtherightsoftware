package org.ericmignot;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;


public class SeRunnerTest {

	@Test public void
	canExecuteASe() throws IOException, InterruptedException {
		SeRunner seRunner = new SeRunner();
		
		seRunner.setRunnerDirectory( "target/test-classes/test-greenpepper");
		seRunner.setCloneRepository( "mastermind" );
		seRunner.setSe( "mastermind/se/seSample.html" );
		seRunner.setOut( "mastermind/se/out" );
		seRunner.executeSpecification();
		
		File out = new File ( "target/test-classes/test-greenpepper/mastermind/se/out/seSample.html" );
		assertTrue( "seSample output", out.exists() );
	}
}
