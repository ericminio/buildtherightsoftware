package org.ericmignot.route;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.LegacyPage;


public interface LegacyPageBuilder {

	public boolean isActivatedBy(HttpServletRequest request);

	public LegacyPage buildsPage(HttpServletRequest request);

}
