package org.ericmignot.store;

import java.io.File;
import java.io.FileFilter;

public class DeletableSpecFileStore extends SpecFileStore {

	public DeletableSpecFileStore(String path) {
		super(path);
	}

	public void deleteAllSpecs() {
		deleteFilesWithExtension( ".html" );
		deleteFilesWithExtension( ".label" );
	}

	private void deleteFilesWithExtension(final String extension) {
		File[] allSpecs = new File( getPath() ).listFiles( new FileFilter() {
			public boolean accept(File file) {
				return file.getName().endsWith( extension );
			}
		});
		for (File file : allSpecs) {
			file.delete();
		}
	}

	
}
