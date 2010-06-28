package org.ericmignot.page;

public abstract class Page {

	private SecondColumn secondColumn;
	
	public SecondColumn getSecondColumn() {
		return secondColumn;
	}

	public void setSecondColumn(SecondColumn secondColumn) {
		this.secondColumn = secondColumn;
		updateSpecificContent();
	}
	
	protected abstract void updateSpecificContent();
	
	protected void workBeforeRenderingHtml() {}
	
	public String html() {
		workBeforeRenderingHtml();
		
		String content = "";
		
		content += new Header().html();
		
		content += "<td class=firstcolumn>";
		content += new FirstColumn().html();
		content += "</td>";
		
		content += "<td class=secondcolumn>";
		content += secondColumn.html();
		content += "</td>";
		
		content += new Footer().html();
		
		return content;
	}

}
