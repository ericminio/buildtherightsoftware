package org.ericmignot.store;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.ericmignot.adapters.domain.FileWorker;

public class LabelMigration implements FileWorker {

	private String directory;
	
	public void setWorkingDirectory(String dir) {
		this.directory = dir;
	}

	public void work() {
		File[] files = new File( directory ).listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return ( pathname.getName().endsWith( ".html" ) );
			}
		});
		for (File file : files) {
			String nameWithoutExtension = file.getName().substring( 0, file.getName().indexOf(".") );
			if (! new File( directory + "/" + nameWithoutExtension + ".label" ).exists() ) {
				try {
					new File( directory + "/" + nameWithoutExtension + ".label" ).createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
