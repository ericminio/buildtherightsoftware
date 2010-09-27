package org.ericmignot.page.section;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;

public class ModifyLinkTest {

	private ModifyLink section;
	private Writer out;
	
	@Before public void
	init() {
		section = new ModifyLink();
		out = new StringWriter();
	}
	
	@Test public void
	targetsTheModificationOfTheGivenSpec() throws IOException {
		section.setSpec( aSpec().withTitle( "modify-this" ).build() );
		section.render( out);
		assertThat( doc( out.toString() ), hasSelector( "a", withAttribute("name", "modifyLink")
				 						 , withAttribute("href", "/specs/modify/modify-this") ));
	}
}
