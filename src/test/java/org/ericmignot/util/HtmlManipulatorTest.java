package org.ericmignot.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class HtmlManipulatorTest {

	private HtmlManipulator manipulator;
	
	@Before public void
	init() {
		manipulator = new HtmlManipulator();
	}
	
	@Test public void
	canRemoveAScriptSection() {
		String content = "before<script toto>tutu</script>after";
		assertEquals( "script removed", "beforeafter", manipulator.removeScriptSection(content) );
	}
	
	@Test public void
	canRemoveTwoScriptSection() {
		String content = "before<script toto>tutu</script><script toto>tutu</script>after";
		assertEquals( "script removed", "beforeafter", manipulator.removeAllScriptSections(content) );
	}
	
	@Test public void
	canExtractBodySection() {
		String content = "<head><body>hello</body>";
		assertEquals( "body extracted", "hello", manipulator.extractBodyContent(content) );
	}
}
