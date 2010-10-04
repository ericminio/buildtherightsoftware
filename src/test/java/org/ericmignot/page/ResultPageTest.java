package org.ericmignot.page;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ResultPageTest {

	private ResultPage page;
	private String content;
	
	@Before public void
	init() throws IOException {
		page = new ResultPage();
		page.setWorkingDirectory( "target/test-classes/test-page-result" );
		page.setChrono( "chrono" );
		page.setSpec( aSpec().withTitle( "sample" ).build() );
		page.setGitRepositoryName( "mastermind" );
		
		Writer out = new StringWriter();
		page.render( out);
		content = out.toString();
	}
	
	@Test public void
	resultFilePath() {
		assertEquals( "target/test-classes/test-page-result/runs/chrono/mastermind/se/out/sample.html", 
				page.getResultPagePath() );
	}
	
	@Test public void
	coberturaReportFilePath() {
		assertEquals( "target/test-classes/test-page-result/runs/chrono/mastermind/target/site/cobertura/frame-summary.html", 
				page.getCoberturaReportPath() );
	}
	
	@Test public void
	containsExecutionResult() {
		assertThat( content, containsString( "Rule for" ) );
	}
	
	@Test public void
	containsCoberturaReport() {
		assertThat( content, containsString( "Cobertura" ) );
		assertThat( content, containsString( "Coverage Report" ) );
	}
	
}
