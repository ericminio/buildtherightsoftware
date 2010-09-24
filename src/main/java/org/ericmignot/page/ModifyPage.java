package org.ericmignot.page;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.core.Spec;
import org.ericmignot.jetty.View;
import org.ericmignot.util.FileReader;


public class ModifyPage implements View {

	private FileReader fileReader;
	private Spec spec;

	public ModifyPage() {
		fileReader = new FileReader();
	}
	
	public String readFile(String fileName) {
		return fileReader.readFile( fileName );
	}
	
	public void setSpec(Spec spec) {
		this.spec = spec;
	}
	
	public void render(Writer out) {
		try {
			out.write( content() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String content() throws IOException {
		String content = readFile( "target/html/template.html" );
		content = content.replaceAll( "page-content", pageContent() );
		return content;
	}

	protected String pageContent() throws IOException {
		String content = 
			modifyLink() + editContent();
		return content;
	}
	
	protected String modifyLink() {
		String modifyLink = readFile( "target/html/modifyLink.html" );
		return modifyLink.replaceAll( "spec-x", spec.getTitle() );
	}

	protected String editContent() {
		String content = readFile( "target/html/edit.html" );
		content = updateFormAction(content);
		content = updateSpecContent(content);
		content = updateSpecLabel(content);
		return content;
	}

	protected String updateSpecLabel(String content) {
		return content.replaceAll( "specX-label", spec.getLabel() );
	}
	
	protected String updateSpecContent(String content) {
		return content.replaceAll( "specX-content", spec.getContent() );
	}

	protected String updateFormAction(String content) {
		return content.replaceAll( "action=\"execute-specX\"", "action=\"/specs/save/" + spec.getTitle() + "\"" );
	}

}
