package org.ericmignot.page;

import java.io.IOException;


public class ModifyPage extends PageTemplate {

	public ModifyPage(String specX) {
		super( specX );
	}

	public String content() throws IOException {
		String content = readFile( "target/html/template.html" );
		content = content.replaceAll( "page-content", pageContent() );
		return content;
	}

	private String pageContent() throws IOException {
		String content = 
			modifyLink() + editContent();
		return content;
	}

	protected String editContent() {
		String content = readFile( "target/html/edit.html" );
		content = updateFormAction(content);
		content = updateSpecContent(content);
		content = updateSpecLabel(content);
		return content;
	}

	protected String updateSpecLabel(String content) {
		return content.replaceAll( "specX-label", readFile( getSpecXDirectory() + getSpecX() + ".label" ));
	}
	
	protected String updateSpecContent(String content) {
		return content.replaceAll( "specX-content", readFile( getSpecXDirectory() + getSpecX() + ".html" ));
	}

	protected String updateFormAction(String content) {
		return content.replaceAll( "action=\"execute-specX\"", "action=\"/specs/save/" + getSpecX() + "\"" );
	}

}
