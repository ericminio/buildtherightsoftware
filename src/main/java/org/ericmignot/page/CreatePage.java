package org.ericmignot.page;

import org.ericmignot.util.PageFileReader;


public class CreatePage extends SavePage {

	public CreatePage(String name) {
		super( name, new PageFileReader().readFile( "target/html/newSpecTemplate.html" ));
	}
	

}
