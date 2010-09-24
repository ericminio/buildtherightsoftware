package org.ericmignot.page;

import static org.ericmignot.util.FileUtils.readFile;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.adapters.Spec;
import org.ericmignot.adapters.View;

public class ShowPage implements View {

	private Spec spec;

	public void setSpec(Spec spec) {
		this.spec = spec;
	}
	
	public void render(Writer out) {
		try {
			String modifyLink = readFile( "target/html/modifyLink.html" );
			modifyLink = modifyLink.replaceAll( "spec-x", spec.getTitle() );
			
			String invitation = readFile( "target/html/invitation.html" );
			invitation = invitation.replaceAll( "spec-x", spec.getTitle() );
			
			String label = "<span class=\"label\">Labels: "+ spec.getLabel() + "</span>";
			
			String specContent = "<span class=\"spec\">" + spec.getContent() + "</span>";
			
			String content = "\n" + modifyLink 
							+ "\n" + label 
							+ "\n" + specContent 
							+ "\n" + invitation;
			
			String template = content();
			String page = template.replaceAll( "page-content", content );
			
			
			out.write( page );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String content() throws IOException {
		String content = readFile( "target/html/template.html" );
		return content;
	}
	

}
