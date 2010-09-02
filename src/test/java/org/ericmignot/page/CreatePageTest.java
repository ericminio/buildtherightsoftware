package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withText;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.ericmignot.util.PageFileReader;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class CreatePageTest {

	private CreatePage page;
	
	@Before public void
	init() {
		page = new CreatePage( "spec-test" );
		page.setSpecXDirectory("target/test-classes/test-system/");
	}
	
	@Test public void
	alwaysCreateSpecWithSameContent() throws IOException {
		page.html();
		
		String content = new PageFileReader().readFile( 
				"target/test-classes/test-system/spec-test.html" );
		Element doc = doc( content );
		assertThat( doc, hasSelector( "td", withText( "Rule for" ) ));
	}
}
