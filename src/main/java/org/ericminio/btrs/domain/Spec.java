package org.ericminio.btrs.domain;

public interface Spec {

	String getTitle();

	String getContent();

	String getLabel();

	void setLabel(String label);

	void setContent(String content);
}
