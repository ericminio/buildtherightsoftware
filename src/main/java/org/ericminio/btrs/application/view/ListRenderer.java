package org.ericminio.btrs.application.view;

import java.util.List;

import org.ericminio.btrs.domain.Spec;

public interface ListRenderer extends Renderer {

	void setSpecs(List<Spec> specs);

}

