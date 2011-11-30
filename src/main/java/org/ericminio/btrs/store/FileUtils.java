package org.ericminio.btrs.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtils {

	public static String readFile(String fileName) {
		String content = null;
		try {
			java.io.FileReader fileReader = new java.io.FileReader(fileName);
			BufferedReader reader = new BufferedReader(fileReader);
	
			StringBuffer buffer = new StringBuffer();
			String str;
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
				buffer.append("\n");
			}
			content = buffer.substring( 0, buffer.length() - 1 );
			reader.close();
			fileReader.close();
		}
		catch (IOException fileDoesntExist) {
		}
		return content;
	}
	
	public static void saveContentInFile(String content, String file) throws IOException {
		PrintWriter out
		   = new PrintWriter(new BufferedWriter(new FileWriter( file )));
		out.print( content );
		out.flush();
	}
	
	public static void removeDir(String dir) {
		String[] files = new File( dir ).list();
		if (files != null) {
			for (String fileName : files) {
				if ( new File( dir + "/" + fileName ).isDirectory() ) {
					removeDir( dir + "/" + fileName );
				}
				new File( dir + "/" + fileName ).delete();
			}
		}
		new File( dir ).delete();
	}
	
}
