package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static com.pyxis.matchers.dom.DomMatchers.withText;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class NewPageTest {

	private NewPage page;
	
	@Before public void
	init() throws IOException {
		page = new NewPage( );
	}
	
	@Test public void
	containsFieldToSetSpecXName() throws IOException {
		Element doc = doc( page );
		assertThat( doc, hasSelector( "input", withAttribute("name", "specXName") ));
	}
	
	@Test public void
	containsFormToSaveTheNewSpecX() throws IOException {
		Element doc = doc( page );
		assertThat( doc, hasSelector( "form", withAttribute("name", "newSpecXForm")
											, withAttribute("method", "get") 
											, withAttribute("action", "/specs/create") ));
		
		assertThat( doc, hasSelector( "a", withAttribute("name", "createSpecXLink")
				 , withAttribute("href", "javascript:newSpecXForm.submit()") 
				 , withAttribute("class", "button")
			     , withText("Create") ));
	}
	
}
