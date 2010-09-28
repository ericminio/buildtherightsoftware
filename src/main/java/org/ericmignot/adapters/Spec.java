package org.ericmignot.adapters;

public interface Spec {

	public String getTitle();

	public String getContent();

	public String getLabel();

	public void setLabel(String label);

	public void setContent(String content);
}
