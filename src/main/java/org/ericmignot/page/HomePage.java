package org.ericmignot.page;

public class HomePage implements Page {

	public static final String SE_SAMPLE = "specs/sample.html";
	
	private SecondColumn secondColumn;
	
	public HomePage() {
		setSecondColumn(new SecondColumn());
		secondColumn.setContent( SE_SAMPLE );
	}
	
	public String html() {
		String content = "";
		
		content += new Header().html();
		
		content += "<td class=firstcolumn>";
		content += new FirstColumn().html();
		content += "</td>";
		
		content += "<td valign=top>";
		content += secondColumn.html();
		content += "</td>";
		
		content += new Footer().html();
		
		return content;
	}
	
	public SecondColumn getSecondColumn() {
		return secondColumn;
	}

	public void setSecondColumn(SecondColumn secondColumn) {
		this.secondColumn = secondColumn;
	}
}
