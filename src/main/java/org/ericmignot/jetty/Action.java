package org.ericmignot.jetty;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.store.Repository;

public interface Action {

	public boolean isActivatedBy(HttpServletRequest request);
	
	public void work(HttpServletRequest request, Repository repository, Writer out);
	
	
}
