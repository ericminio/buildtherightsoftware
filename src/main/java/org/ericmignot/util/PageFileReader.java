package org.ericmignot.util;

import java.io.BufferedReader;
import java.io.IOException;

public class PageFileReader {

	public String readFile(String fileName) {
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
		catch (StringIndexOutOfBoundsException emptyFile) {
			content = "";
		}
		return content;
	}
}
