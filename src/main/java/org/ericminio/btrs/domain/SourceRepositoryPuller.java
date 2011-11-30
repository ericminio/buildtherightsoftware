package org.ericminio.btrs.domain;

public interface SourceRepositoryPuller extends FileWorker {

	public void setUrl(String url);
	
	public String getUrl();
}
