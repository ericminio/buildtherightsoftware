package org.ericmignot.page;

import static org.ericmignot.util.FileUtils.readFile;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.ui.SpecRenderer;

public class ModifyPage implements SpecRenderer {

	private Spec spec;

	public void setSpec(Spec spec) {
		this.spec = spec;
	}
	
	public void render(Writer out) {
		try {
			String template = readFile( "target/html/template.html" );
			String page = template.replaceAll( "page-content", pageContent() );
			out.write( page );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String pageContent() throws IOException {
		String content = modifyLink() + editContent();
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
