package org.ericminio.btrs.domain;


public interface FileWorker {

	public void setWorkingDirectory(String dir);
	
	public void work() throws Exception;
	
}
