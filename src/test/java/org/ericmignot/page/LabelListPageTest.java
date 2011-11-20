package org.ericmignot.page;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.ericmignot.util.matchers.SpecMatcher.isASpec;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.ericmignot.adapters.domain.Spec;
import org.junit.Test;
import org.w3c.dom.Element;

public class LabelListPageTest {

	private LabelListPage page;
	private Element doc;
	private Writer out = new StringWriter();
	
	private String[][] data = { 
			{ "game", "tetris" }, { "game (1)" }
	
			,{ "game", "tetris",
			   "game", "poker" }, { "game (2)" }
									
			,{ "game", "tetris",
			   "challenge", "lotery" }, { "game (1)", "challenge (1)" }
								  
			};
	
	@Test public void
	testAll() throws IOException {
		for (int testIndex=0; testIndex < testCount() ; testIndex++) {
			List<Spec> specs = new ArrayList<Spec>();
			for (int specIndex=0; specIndex < specCountOfTestIndex( testIndex ); specIndex++) {
				specs.add( specAtIndexGivenTheTestIndex( specIndex, testIndex ) );
			}
			page = new LabelListPage();
			page.setSpecs( specs );
			page.render( out);
			String pageContent = out.toString();
			
			for (int labelIndex=0; labelIndex < expectedLabelCountOfTest( testIndex ) ; labelIndex ++) {
				String expectedText = labelInfoAtIndexGivenTestIndex( labelIndex, testIndex );
				String expectedLabel = labelAtIndexGivenTestIndex( labelIndex, testIndex );
				String expectedLink = "<a name=\"" + expectedLabel + "\" class=\"label-filter\" href=\"/specs/list?label=" + expectedLabel + "\" >" + expectedText+ "</a>";
				assertThat( pageContent, containsString(expectedLink) );
			}
		}
	}
	
	
	@Test public void
	canReadData() {
		assertEquals( 3, testCount() );
		assertEquals( 1, specCountOfTestIndex(0) );
		assertThat( specAtIndexGivenTheTestIndex(1, 2), isASpec().withTitle( "lotery" ).withLabel( "challenge" ) );
		assertEquals( 2, expectedLabelCountOfTest(2) );
		assertEquals( "game (1)", labelInfoAtIndexGivenTestIndex(0, 2) );
		assertEquals( "game", labelAtIndexGivenTestIndex(0, 2) );
	}
	
	private String labelInfoAtIndexGivenTestIndex(int i, int j) {
		return data[2*j+1][i];
	}

	private String labelAtIndexGivenTestIndex(int i, int j) {
		String labelInfo =  labelInfoAtIndexGivenTestIndex( i, j );
		return labelInfo.substring( 0, labelInfo.indexOf( " ") );
	}

	private int expectedLabelCountOfTest(int i) {
		return data[2*i+1].length; 
	}

	private Spec specAtIndexGivenTheTestIndex(int specIndex, int testIndex) {
		String label = data[2*testIndex][2*specIndex];
		String title = data[2*testIndex][2*specIndex + 1];
		Spec aSpec = aSpec().withTitle( title ).withLabel( label ).build();
		return aSpec;
	}

	private int specCountOfTestIndex(int i) {
		return data[2*i].length/2;
	}

	private int testCount() {
		return data.length / 2;
	}
	
	@Test public void
	canBuildLabelMap() {
		Spec tetris = aSpec().withLabel( "game" ).withTitle( "tetris" ).build();
		Spec poker = aSpec().withLabel( "game" ).withTitle( "poker" ).build();
		page = new LabelListPage();
		page.setSpecs( Arrays.asList( tetris, poker ) );
		Map<String, Integer> labels = page.labelSummary();
		assertEquals( 1, labels.size() );
		assertEquals( 2, (int)labels.get( "game" ));
	}

}
