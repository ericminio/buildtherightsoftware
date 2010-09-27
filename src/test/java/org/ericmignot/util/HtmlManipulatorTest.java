package org.ericmignot.util;

import static org.ericmignot.util.HtmlManipulator.extractBodyContent;
import static org.ericmignot.util.HtmlManipulator.removeAllScriptSections;
import static org.ericmignot.util.HtmlManipulator.removeScriptSection;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HtmlManipulatorTest {

	@Test public void
	canRemoveAScriptSection() {
		String content = "before<script toto>tutu</script>after";
		assertEquals( "script removed", "beforeafter", removeScriptSection(content) );
	}
	
	@Test public void
	canRemoveTwoScriptSection() {
		String content = "before<script toto>tutu</script><script toto>tutu</script>after";
		assertEquals( "script removed", "beforeafter", removeAllScriptSections(content) );
	}
	
	@Test public void
	canExtractBodySection() {
		String content = "<head><body>hello</body>";
		assertEquals( "body extracted", "hello", extractBodyContent(content) );
	}
}
