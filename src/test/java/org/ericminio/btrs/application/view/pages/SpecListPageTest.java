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
import java.util.ArrayList;
import java.util.List;

import org.ericminio.btrs.application.view.pages.SpecListPage;
import org.ericminio.btrs.domain.Spec;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class SpecListPageTest {

	private SpecListPage page;
	private Element doc;
	private Writer out = new StringWriter();
	
	@Before public void
	initRepository() throws IOException {
		Spec aSpec = aSpec().withTitle( "sample-title" ).build();
		Spec anotherSpec = aSpec().withTitle( "another" ).build();
		List<Spec> specs = new ArrayList<Spec>();
		specs.add( aSpec );
		specs.add( anotherSpec );

		page = new SpecListPage();
		page.setSpecs( specs );
		page.render( out);
		doc = doc( out.toString() );
	}
	
	@Test public void
	displaysSpecList() throws IOException {
		assertThat( doc, hasSelector( "a", withAttribute("class", "list")
										 , withAttribute("href", "/specs/show/sample-title")
										 , withText( "sample-title" ) ));
		
		assertThat( doc, hasSelector( "a", withAttribute("class", "list")
				 						 , withAttribute("href", "/specs/show/another")
				 						 , withText( "another" ) ));
	}
}
