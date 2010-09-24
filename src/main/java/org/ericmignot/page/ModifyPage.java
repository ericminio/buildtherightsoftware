package org.ericmignot.page;

import static org.ericmignot.util.FileUtils.readFile;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.adapters.Spec;
import org.ericmignot.adapters.View;

public class ModifyPage implements View {

	private Spec spec;

	public void setSpec(Spec spec) {
		this.spec = spec;
	}
	
	public void render(Writer out) {
		try {
			out.write( content() );
		} catch (IOException e) {
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
