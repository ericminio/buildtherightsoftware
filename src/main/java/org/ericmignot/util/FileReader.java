package org.ericmignot.util;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {

	public String readFile(String fileName) throws IOException {
		java.io.FileReader fileReader = new java.io.FileReader(fileName);
		BufferedReader reader = new BufferedReader(fileReader);

		StringBuffer buffer = new StringBuffer();
		String str;
		while ((str = reader.readLine()) != null) {
			buffer.append(str);
		}
		reader.close();
		return buffer.toString();
	}
}