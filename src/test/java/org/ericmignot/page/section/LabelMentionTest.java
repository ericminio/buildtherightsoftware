package org.ericmignot.page.section;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static com.pyxis.matchers.dom.DomMatchers.withText;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;


public class LabelMentionTest {

	private LabelMention section;
	private Writer out;
	
	@Before public void
	init() {
		section = new LabelMention();
		out = new StringWriter();
	}
	
	@Test public void
	targetsTheModificationOfTheGivenSpec() throws IOException {
		section.setSpec( aSpec().withLabel( "label-this" ).build() );
		section.render( out);
		assertThat( doc( out.toString() ), hasSelector( "span"
										 , withAttribute( "class", "label" )
				 						 , withText( "Labels: label-this" ) ));
	}
}
