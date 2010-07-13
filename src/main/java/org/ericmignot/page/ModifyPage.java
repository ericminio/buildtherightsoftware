package org.ericmignot.page;

import java.io.IOException;

public class ModifyPage extends Page {

	private String specX;
	private String specXDirectory;
	
	public ModifyPage(String specX) {
		this.specX = specX;
		this.specXDirectory = DEFAULT_WORKING_DIRECTORY;
	}

	public String getSpecX() {
		return specX;
	}

	public String getSpecXDirectory() {
		return specXDirectory;
	}

	public void setSpecXDirectory(String dir) {
		this.specXDirectory = dir;
	}
	
	public String html() throws IOException {
		String template = super.html();
		String page = template.replaceAll( "page-content", pageContent() );
		return page;
	}

	private String pageContent() throws IOException {
		String content = "";
		
		String editTemplate = readFile( "target/html/edit.html" );
		String withCorrectFormAction = editTemplate.replaceAll( "action=\"execute-specX\"", "action=\"/specs/save/" + specX + "\"" );
		String withCorrectTextAreaContent = withCorrectFormAction.replaceAll( "specX-content", readFile( specXDirectory + specX + ".html" ));
		
		content += withCorrectTextAreaContent;
		
		return content;
	}

}
