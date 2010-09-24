package org.ericmignot.domain;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.ericmignot.domain.GreenPepperRunner;
import org.junit.Before;
import org.junit.Test;


public class SpecRunnerTest {

	private GreenPepperRunner runner;
	
	@Before public void
	init() {
		runner = new GreenPepperRunner();
	}
	
	@Test public void
	canExecuteASe() throws IOException, InterruptedException {
		runner.setWorkingDirectory( "target/test-classes/test-greenpepper");
		runner.setClassesRelativeDirectory( "mastermind/target/classes" );
		runner.setSpecFileRelativeFile( "mastermind/se/sample.html" );
		runner.setOutputRelativeDirectory( "mastermind/se/out" );
		runner.work();
		
		File out = new File ( "target/test-classes/test-greenpepper/mastermind/se/out/sample.html" );
		assertTrue( "se output", out.exists() );
	}
	
}
