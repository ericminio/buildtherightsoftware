package org.ericmignot.jetty;

import javax.servlet.http.HttpServletRequest;

public interface PageBuilder {

	public boolean isActivatedBy(HttpServletRequest request);

	public Page buildsPage(HttpServletRequest request);

}
