package org.ericmignot.util;

public class HtmlManipulator {
	
	public static String extractBodyContent(String fullContent) {
		String body = fullContent.substring( fullContent.indexOf("<body>") );
		return body.substring( 6, body.indexOf( "</body>" ));
	}

	public static String removeScriptSection(String content) {
		String begin = content.substring( 0, content.indexOf( "<script" ) );
		String end = content.substring( content.indexOf( "</script>" ) + 9 );
		return begin + end;
	}

	public static String removeAllScriptSections(String content) {
		String newContent = content;
		while ( newContent.indexOf( "<script" ) != -1) {
			newContent = removeScriptSection( newContent );
		}
		return newContent;
	}

	
}
