package org.ericmignot.page;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.core.Spec;
import org.ericmignot.jetty.View;
import org.ericmignot.util.FileReader;


public class ShowPage implements View {

	private FileReader fileReader;

	public ShowPage() {
		fileReader = new FileReader();
	}
	
	protected String readFile(String fileName) {
		return fileReader.readFile( fileName );
	}

	public void render(Spec spec, Writer out) {
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
