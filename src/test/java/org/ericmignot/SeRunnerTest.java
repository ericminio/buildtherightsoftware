package org.ericmignot;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;


public class SeRunnerTest {

	private SeRunner seRunner;
	
	@Before public void
	init() {
		seRunner = new SeRunner();
	}
	
	@Test public void
	canExecuteASe() throws IOException, InterruptedException {
		seRunner.setRunnerDirectory( "target/test-classes/test-greenpepper");
		seRunner.setClassesRelativeDirectory( "mastermind/target/classes" );
		seRunner.setSeRelativeFile( "mastermind/se/sample.html" );
		seRunner.setOutputRelativeDirectory( "mastermind/se/out" );
		seRunner.executeSpecification();
		
		File out = new File ( "target/test-classes/test-greenpepper/mastermind/se/out/sample.html" );
		assertTrue( "se output", out.exists() );
	}
	
}
