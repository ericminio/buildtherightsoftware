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

public class ModifyPageTest {

	private ModifyPage page;
	private Element doc;
	
	@Before public void
	rendersASampleShowPage() throws IOException {
		Spec spec = aSpec().withTitle( "sample-title" ).withContent( "sample content" ).withLabel( "sample-label" ).build();
		
		page = new ModifyPage();
		Writer out = new StringWriter();
		page.setSpec(spec);
		page.render(out);
		doc = doc( out.toString() );
	}
	
	@Test public void
	containsInputFieldToEditLabel() throws IOException {
		assertThat( doc, hasSelector( "input", withAttribute("name", "label")
											 , withAttribute("size", "80") 
											 , withAttribute("value", "sample-label") ));
	}
	
	@Test public void
	containsTextareaToEditContent() throws IOException {
		assertThat( doc, hasSelector( "textarea", withAttribute("name", "content")
												, withAttribute("cols", "80")
												, withAttribute("rows", "20")
												, withText( "sample content" )));
	}
	
	@Test public void
	containsFormToSaveTheSpec() throws IOException {
		assertThat( doc, hasSelector( "form", withAttribute("name", "saveSpecXForm")
											, withAttribute("method", "post") 
											, withAttribute("action", "/specs/save/sample-title") ));
		
		assertThat( doc, hasSelector( "a", withAttribute("name", "saveSpecXLink")
										 , withAttribute("href", "javascript:saveSpecXForm.submit()") 
										 , withAttribute("class", "button")
									     , withText("Save") ));
	}
	
}
