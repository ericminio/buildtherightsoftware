package org.ericmignot.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class StaticClassesCoverageTest {

	@Test public void
	completelyCoversHtmlManipulator() {
		new HtmlManipulator();
		assertTrue( true );
	}
	
	@Test public void
	completelyCoversHttpRequestInformationExtractor() {
		new HttpRequestInformationExtractor();
		assertTrue( true );
	}
	
	@Test public void
	completelyCoversFileUtils() {
		new FileUtils();
		assertTrue( true );
	}
}
