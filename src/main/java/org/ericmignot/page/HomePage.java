package org.ericmignot.page;

public class HomePage {

	private Header header;
	private FirstColumn firstColumn;
	private SecondColumn secondColumn;
	private Footer footer;
	
	public HomePage() {
		setHeader(new Header());
		setFirstColumn(new FirstColumn());
		setSecondColumn(new SecondColumn());
		setFooter(new Footer());
	}
	
	public String html() {
		String content = "";
		
		content += header.html();
		content += firstColumn.html();
		content += secondColumn.html();
		content += footer.html();
		
		return content;
	}
	
	public void setFirstColumn(FirstColumn firstColumn) {
		this.firstColumn = firstColumn;
	}

	public FirstColumn getFirstColumn() {
		return this.firstColumn;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public void setFooter(Footer footer) {
		this.footer = footer;
	}

	public Footer getFooter() {
		return footer;
	}

	public SecondColumn getSecondColumn() {
		return secondColumn;
	}

	public void setSecondColumn(SecondColumn secondColumn) {
		this.secondColumn = secondColumn;
	}

}
