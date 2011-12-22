package org.ericminio.btrs.domain;

public interface SourcePuller extends FileWorker {

	void setUrl(String url);
	
	String getRepositoryName();
	
}
