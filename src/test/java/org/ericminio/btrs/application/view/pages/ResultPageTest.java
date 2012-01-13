package org.ericminio.btrs.application.view.pages;

import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class ResultPageTest {

	private ResultPage page;
	private String content;
	private Element doc;
	
	@Before public void
	init() throws IOException {
		page = new ResultPage();
		page.setWorkingDirectory( "target/test-classes/test-page-result" );
		page.setChrono( "chrono" );
		page.setSpec( aSpec().withTitle( "sample" ).withLabel( "sample-label" ).build() );
		page.setGitRepositoryName( "mastermind" );
		page.setSourceRepositoryUrl( "url-to-sources" );
		
		Writer out = new StringWriter();
		page.render( out);
		content = out.toString();
	}
	
	@Test public void
	displaysSourceRepository() {
		assertThat( content, containsString( "<p><span class=\"repository\">Repository: url-to-sources</span></p>" ) );
	}
	
	@Test public void
	displaysSpecLabel() {
		assertThat( content, containsString( "<p><span class=\"label\">Label: sample-label</span></p>" ) );
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
