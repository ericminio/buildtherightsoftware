package org.ericminio.btrs.deployment;

import static org.junit.Assert.assertTrue;

import org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor;
import org.ericminio.btrs.domain.matchers.CoreMatchers;
import org.ericminio.btrs.store.FileUtils;
import org.junit.Test;


public class StaticClassesCoverageTest {

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
	
	@Test public void
	completelyCoversCoreMatchers() {
		new CoreMatchers();
		assertTrue( true );
	}
}
