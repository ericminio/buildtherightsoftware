package org.ericminio.btrs.domain;

public interface SpecRunner extends FileWorker {

	void setSpecFileRelativeFile(String specFileRelativePath);
	
	void setOutputRelativeDirectory(String outRelativePath);

	void setClassPathRelativeDirectory(String cpRelativePath);
	
}
