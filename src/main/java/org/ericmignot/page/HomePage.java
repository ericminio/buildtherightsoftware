package org.ericmignot.page;

public class HomePage {

	public static final String STYLE = "<head><link rel='stylesheet' type='text/css' href='/style.css' /></head>";
	private FirstColumn firstColumn;
	
	public HomePage() {
		setFirstColumn(new FirstColumn());
	}
	
	public String html() {
		String content = "";
		
		content += "<html>";
		content += head();
		content += openTable();
		content += firstColumn.html();
		content += closeTable();
		content += "</html>";
		
		return content;
	}
	
	private String head() {
		return STYLE;
	}

	private String closeTable() {
		return "</tr></table></body>";
	}

	private String openTable() {
		return "<body><table><tr>";
	}

	public void setFirstColumn(FirstColumn firstColumn) {
		this.firstColumn = firstColumn;
	}

	public FirstColumn getFirstColumn() {
		return this.firstColumn;
	}

}
