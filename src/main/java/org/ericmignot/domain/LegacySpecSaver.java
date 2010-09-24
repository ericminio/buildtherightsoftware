package org.ericmignot.domain;

import static org.ericmignot.util.FileUtils.saveContentInFile;

import java.io.IOException;

public class LegacySpecSaver {

	private String directory;
	
	public void setDirectory(String path) {
		this.directory = path;
	}

	public void saveContent(String specX, String specXContent) throws IOException {
		String file = directory + specX + ".html";
		saveContentInFile(specXContent, file);
	}

	public void saveLabel(String specX, String label) throws IOException {
		String file = directory + specX + ".label";
		saveContentInFile(label, file);
	}

}
