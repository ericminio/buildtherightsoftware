package org.ericmignot.adapters.ui;

import java.io.IOException;
import java.io.Writer;


public interface Renderer {

	public void render(Writer out) throws IOException;
}
