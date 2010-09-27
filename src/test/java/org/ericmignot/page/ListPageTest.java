package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static com.pyxis.matchers.dom.DomMatchers.withText;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.ericmignot.adapters.Spec;
import org.ericmignot.adapters.SpecRepository;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import static org.ericmignot.util.RepositoryMockBuilder.aMockRepo;

public class ListPageTest {

	private ListPage page;
	private Element doc;
	private Writer out = new StringWriter();
	private SpecRepository repo;
	
	@Before public void
	initRepository() throws IOException {
		Spec aSpec = aSpec().withTitle( "sample-title" ).build();
		Spec anotherSpec = aSpec().withTitle( "another" ).build();
		repo = aMockRepo().withSpec( aSpec )
										 .withSpec( anotherSpec ).build();
		
		page = new ListPage();
		page.setRepository( repo );
		page.render( out);
		doc = doc( out.toString() );
	}
	
	@Test public void
	getSpecsFromRepository() {
		verify( repo ).getSpecs();
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
