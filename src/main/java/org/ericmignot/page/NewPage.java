package org.ericmignot.page;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.adapters.Renderer;

import static org.ericmignot.util.FileUtils.readFile;

public class NewPage implements Renderer {

	public void render(Writer out) {
		try {
			String template = readFile( "target/html/template.html" );
			String page = template.replaceAll( "page-content", readFile( "target/html/new.html" ) );
			out.write( page );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
