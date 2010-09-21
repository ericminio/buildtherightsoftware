package org.ericmignot.jetty;

import java.io.Writer;

import org.ericmignot.core.Spec;

public interface View {

	public void render(Spec spec, Writer out);
}
