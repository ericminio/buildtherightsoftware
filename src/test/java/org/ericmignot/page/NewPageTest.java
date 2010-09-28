package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static com.pyxis.matchers.dom.DomMatchers.withText;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class NewPageTest {

	private NewSpecFormPage page;
	private Element doc;
	
	@Before public void
	init() throws IOException {
		page = new NewSpecFormPage( );
		
		Writer out = new StringWriter();
		page.render( out);
		doc = doc( out.toString() );
	}
	
	@Test public void
	containsFieldToSetSpecName() throws IOException {
		assertThat( doc, hasSelector( "input", withAttribute("name", "spec"),
												withAttribute("size", "80") ));
	}
	
	@Test public void
	containsFormToSaveTheNewSpec() throws IOException {
		assertThat( doc, hasSelector( "form", withAttribute("name", "newSpecXForm")
											, withAttribute("method", "get") 
											, withAttribute("action", "/specs/create") ));
		
		assertThat( doc, hasSelector( "a", withAttribute("name", "createSpecXLink")
				 , withAttribute("href", "javascript:newSpecXForm.submit()") 
				 , withAttribute("class", "button")
			     , withText("Create") ));
	}
	
}
