package org.ericmignot.page;

import java.io.IOException;


public class ModifyPage extends PageTemplate {

	public ModifyPage(String specX) {
		super( specX );
	}

	public String content() throws IOException {
		String template = super.content();
		String page = template.replaceAll( "page-content", pageContent() );
		return page;
	}

	private String pageContent() throws IOException {
		String content = "";
		
		String editTemplate = readFile( "target/html/edit.html" );
		String withCorrectFormAction = editTemplate.replaceAll( "action=\"execute-specX\"", "action=\"/specs/save/" + getSpecX() + "\"" );
		String withCorrectTextAreaContent = withCorrectFormAction.replaceAll( "specX-content", readFile( getSpecXDirectory() + getSpecX() + ".html" ));
		
		content += withCorrectTextAreaContent;
		
		return content;
	}

}
