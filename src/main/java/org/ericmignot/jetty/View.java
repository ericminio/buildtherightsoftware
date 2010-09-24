package org.ericmignot.jetty;

import java.io.Writer;

import org.ericmignot.core.Spec;

public interface View {

	public void setSpec(Spec spec);
	
	public void render(Writer out);
}
