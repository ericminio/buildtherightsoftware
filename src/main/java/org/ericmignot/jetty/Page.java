package org.ericmignot.jetty;

import java.io.IOException;

public interface Page {

	public String content() throws IOException;

}