package org.ericmignot.page.section;

import static org.ericmignot.util.FileUtils.readFile;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.adapters.Spec;
import org.ericmignot.adapters.Renderer;

public class ModifyLink implements Renderer {

	private Spec spec;
	
	public void render(Writer out) {
		String modifyLink = readFile( "target/html/modifyLink.html" );
		modifyLink = modifyLink.replaceAll( "spec-x", spec.getTitle() );
		try {
			out.write( modifyLink );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

}
