package org.ericmignot.page;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ResultPageTest {

	private ResultPage page;
	
	@Before public void
	init() {
		page = new ResultPage();
		page.setWorkingDirectory( "working-directory" );
		page.setChrono( "chrono-test" );
		page.setSpec( aSpec().withTitle( "sample" ).build() );
		page.setGitRepositoryName( "mastermind" );
	}
	
	@Test public void
	resultFilePath() {
		assertEquals( "working-directory/runs/chrono-test/mastermind/se/out/sample.html", page.getResultPagePath() );
	}
	
	@Test public void
	coberturaReportFilePath() {
		assertEquals( "working-directory/runs/chrono-test/mastermind/target/site/cobertura/frame-summary.html", page.getCoberturaReportPath() );
	}
	
}
