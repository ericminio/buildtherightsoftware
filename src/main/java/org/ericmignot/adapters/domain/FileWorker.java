package org.ericmignot.adapters.domain;


public interface FileWorker {

	public void setWorkingDirectory(String dir);
	
	public void work() throws Exception;
	
}
