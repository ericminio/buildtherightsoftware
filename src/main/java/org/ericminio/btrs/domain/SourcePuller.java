package org.ericminio.btrs.domain;

public interface SourcePuller extends FileWorker {

	public void setUrl(String url);
	
	public String getUrl();
}
