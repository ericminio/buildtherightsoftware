package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static com.pyxis.matchers.dom.DomMatchers.withText;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.ericmignot.adapters.domain.Spec;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class ShowPageTest {

	private ShowPage page;
	private Element doc;
	
	@Before public void
	rendersASampleShowPage() throws IOException {
		Spec spec = aSpec().withTitle( "sample-title" ).withContent( "sample content" ).withLabel( "sample-label" ).build();
		
		page = new ShowPage();
		Writer out = new StringWriter();
		page.setSpec(spec);
		page.render( out);
		doc = doc( out.toString() );
	}
	
	@Test public void
	displaysModifySection() throws IOException {
		assertThat( doc, hasSelector( "a", withAttribute("name", "modifyLink")
										 , withAttribute("href", "/specs/modify/sample-title") ));
	}
	
	@Test public void
	displaysCodeSubmissionSection() throws IOException {
		assertThat( doc, hasSelector( "form", withAttribute("name", "tryCodeForm")
											, withAttribute("action", "/specs/execute/sample-title") ));
	}
	
	@Test public void
	displaysSpecLabel() {
		assertThat( doc, hasSelector( "span", withAttribute("class", "label")
				 						    , withText("Labels: sample-label") ));
	}
	
	@Test public void
	displaysSpecContent() {
		assertThat( doc, hasSelector( "span", withAttribute("class", "spec")
				 						    , withText("sample content") ));
	}
	
}
