package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.core.SpecSaver;

public class SavePage extends PageTemplate {

	private String specXContent;
	private SpecSaver specSaver;
	private String specXLabel;
	
	public SavePage(String specX, String specXContent, String labels) {
		super(specX);
		this.specXContent = specXContent;
		this.specXLabel = labels;
		this.specSaver = new SpecSaver();
	}

	public String getSpecXContent() {
		return specXContent;
	}
	
	public String getSpecXLabel() {
		return specXLabel;
	}

	public void setSpecSaver(SpecSaver specSaver) {
		this.specSaver = specSaver;
	}
	
	public SpecSaver getSpecSaver() {
		return specSaver;
	}

	public String getFilePathToBeIncluded() {
		return getSpecXDirectory() + getSpecX() + ".html";
	}
	
	public String content() throws IOException {
		specSaver.setDirectory( getSpecXDirectory() );
		specSaver.saveContent( getSpecX(), specXContent );
		specSaver.saveLabel( getSpecX(), specXLabel );
		
		String content = readFile( "target/html/template.html" );
		content = content.replaceAll( "page-content", pageContent() );
		return content;
	}

	protected String pageContent() throws IOException {
		String content = 
					modifyLink()
					+ specLabel()
					+ specContent()
					+ invitationToTryACode();
		return content;
	}
	
	protected String specContent() {
		return readFile( getFilePathToBeIncluded() );
	}

}
