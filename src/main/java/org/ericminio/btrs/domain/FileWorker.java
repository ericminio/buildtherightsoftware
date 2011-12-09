package org.ericminio.btrs.domain;


public interface FileWorker {

	void setWorkingDirectory(String dir);
	
	void work() throws Exception;
	
}
