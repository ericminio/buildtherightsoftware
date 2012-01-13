package org.ericminio.btrs.application.view.pages;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withText;
import static org.ericminio.btrs.application.view.pages.DocumentBuilder.doc;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class WelcomePageTest {

	WelcomePage page;
	Writer out;
	Element doc;
	
	@Before public void
	rendersASampleShowPage() throws IOException {
		page = new WelcomePage();
		out = new StringWriter();
		page.render(out);
		doc = doc( out.toString() );
	}
	
	@Test public void
	welcome() {
		assertThat( out.toString(), containsString( "Welcome :)" ) );
		assertThat( out.toString(), containsString( "Github sample" ) );
		assertThat( out.toString(), containsString( "Bitbucket sample" ) );
	}

	@SuppressWarnings("unchecked")
	@Test public void
	instructions() throws IOException {
		assertThat( doc, hasSelector( "a", withText( "GreenPepper" )));
		assertThat( doc, hasSelector( "a", withText( "Maven" )));
		assertThat( doc, hasSelector( "a", withText( "github.com" )));
		assertThat( doc, hasSelector( "a", withText( "bitbucket.org" )));
	}
	
}
