package org.ericmignot.core;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;


public class SpecRunnerTest {

	private SpecRunner specRunner;
	
	@Before public void
	init() {
		specRunner = new SpecRunner();
	}
	
	@Test public void
	canExecuteASe() throws IOException, InterruptedException {
		specRunner.setWorkingDirectory( "target/test-classes/test-greenpepper");
		specRunner.setClassesRelativeDirectory( "mastermind/target/classes" );
		specRunner.setSpecFileRelativeFile( "mastermind/se/sample.html" );
		specRunner.setOutputRelativeDirectory( "mastermind/se/out" );
		specRunner.work();
		
		File out = new File ( "target/test-classes/test-greenpepper/mastermind/se/out/sample.html" );
		assertTrue( "se output", out.exists() );
	}
	
}
