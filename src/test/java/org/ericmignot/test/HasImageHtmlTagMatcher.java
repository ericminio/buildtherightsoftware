package org.ericmignot.test;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;


public class HasImageHtmlTagMatcher extends BaseMatcher<String> {

	private String expected;
	
	public HasImageHtmlTagMatcher(String fileName) {
		this.expected = "<img src=/" + fileName + " />";
	}
	
	public boolean matches(Object obj) {
		String content = obj.toString();
		return ((String) content).indexOf(expected) != -1;
	}

	public void describeTo(Description description) {
		description.appendText("image tag " + expected);
	}
	
	public static HasImageHtmlTagMatcher hasImageHtmlTag(String fileName) {
		return new HasImageHtmlTagMatcher(fileName);
	}


}
