package org.ericmignot.page;

public class Header {

	public static final String STYLE = "<link rel='stylesheet' type='text/css' href='/style.css' />";

	public String html() {
		return "<html><head>" + STYLE + "</head><body><table><tr>";
	}

}
