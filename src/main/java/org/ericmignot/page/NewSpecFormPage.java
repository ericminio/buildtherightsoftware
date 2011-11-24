package org.ericmignot.page;

import static org.ericmignot.util.FileUtils.readFile;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.adapters.ui.Renderer;

public class NewSpecFormPage implements Renderer {

	public void render(Writer out) throws IOException {
		String template = readFile("target/html/template.html");
		String page = template.replaceAll("page-content",
				readFile("target/html/new.html"));
		out.write(page);
	}

}
