package org.ericminio.btrs.application.view;

import java.io.IOException;
import java.io.Writer;


public interface Renderer {

	void render(Writer out) throws IOException;
}
