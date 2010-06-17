package org.ericmignot.test;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;


public class ContainsTableMatcher extends BaseMatcher<String> {

	public boolean matches(Object obj) {
		String content = obj.toString();
		return (content.indexOf("<body><table><tr>") != -1 && content.indexOf("</tr></table></body>") != -1);
	}

	public void describeTo(Description description) {
		description.appendText("<body><table><tr>");
	}
	
	public static ContainsTableMatcher containsTableAsRootElement() {
		return new ContainsTableMatcher();
	}


}
