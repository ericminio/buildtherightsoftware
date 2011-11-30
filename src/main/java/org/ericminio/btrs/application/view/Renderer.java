package org.ericminio.btrs.application.view;

import java.io.IOException;
import java.io.Writer;


public interface Renderer {

	public void render(Writer out) throws IOException;
}
