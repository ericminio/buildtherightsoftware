package org.ericminio.btrs.application.view.pages;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static com.pyxis.matchers.dom.DomMatchers.withText;
import static org.ericminio.btrs.application.view.pages.DocumentBuilder.doc;
import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.ericminio.btrs.application.view.pages.ShowPage;
import org.ericminio.btrs.domain.Spec;
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
	
	@SuppressWarnings("unchecked")
	@Test public void
	displaysModifySection() throws IOException {
		assertThat( doc, hasSelector( "a", withAttribute("name", "modifyLink")
										 , withAttribute("href", "/specs/modify/sample-title") ));
	}

	@SuppressWarnings("unchecked")
	@Test public void
	displaysSpecLabel() {
		assertThat( doc, hasSelector( "span", withAttribute("class", "label")
				 						    , withText("Label: sample-label") ));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	displaysSpecContent() {
		assertThat( doc, hasSelector( "span", withAttribute("class", "spec")
				 						    , withText("sample content") ));
	}
		
	@SuppressWarnings("unchecked")
	@Test public void
	displaysCodeSubmissionInputField() throws IOException {
		assertThat( doc, hasSelector( "form", withAttribute("name", "tryCodeForm")
											, withAttribute("action", "/specs/execute/sample-title") ));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	displaysCodeSubmissionInvitation() throws IOException {
		assertThat( doc, hasSelector( "span", 
				withAttribute("class", "invitation"),
				withText("Can you make it?") ));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	displaysLinkToHelp() throws IOException {
		assertThat( doc, hasSelector( "a",  
				withAttribute( "href", "/" ),
				withText("Learn how") ));
	}
	
}
