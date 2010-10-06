package org.ericmignot.application;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class SampleSpecShouldBePresentTest {

	@Test public void
	sampleSpecFilesArePresents() {
		assertTrue( new File( "specs/sample.html" ).exists() );
		assertTrue( new File( "specs/sample.label" ).exists() );
	}
}
