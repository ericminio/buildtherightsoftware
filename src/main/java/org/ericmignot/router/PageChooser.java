package org.ericmignot.router;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.Page;

public interface PageChooser {

	public boolean isConcernedBy(HttpServletRequest request);

	public Page getPage(HttpServletRequest request);

	public String extractSpecX(String uri);

}
