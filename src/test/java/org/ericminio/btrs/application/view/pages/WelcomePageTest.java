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
	}

	@SuppressWarnings("unchecked")
	@Test public void
	instructions() throws IOException {
		assertThat( doc, hasSelector( "li", withText( "Create a GreenPepper specification" )));
		assertThat( doc, hasSelector( "li", withText( "Share it" )));
		assertThat( doc, hasSelector( "li", withText( "Make it pass" )));
	}
	
	@SuppressWarnings("unchecked")
	@Test public void
	features() {
		assertThat( doc, hasSelector( "li", withText( "GreenPepper spec + Maven Java 5 code on github.com" )));
		assertThat( doc, hasSelector( "li", withText( "GreenPepper spec + Maven Java 5 code on bitbucket.org" )));
	}
	
	@Test public void
	notes() {
		assertThat( doc, hasSelector( "li", withText( "Your maven project must support a 'mvn clean cobertura:cobertura'" )));
	}
}
