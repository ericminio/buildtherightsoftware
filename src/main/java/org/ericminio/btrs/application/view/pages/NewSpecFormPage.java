package org.ericminio.btrs.application.view.pages;

import static org.ericminio.btrs.store.FileUtils.readFile;

import java.io.IOException;
import java.io.Writer;

import org.ericminio.btrs.application.view.Renderer;

public class NewSpecFormPage implements Renderer {

	public void render(Writer out) throws IOException {
		String template = readFile("target/html/template.html");
		String page = template.replaceAll("page-content",
				readFile("target/html/new.html"));
		out.write(page);
	}

}
