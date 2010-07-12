package org.ericmignot.page.section;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.ericmignot.page.section.FirstColumn;
import org.junit.Test;

public class FirstColumnTest {

	@Test public void
	content() {
		assertThat(new FirstColumn().html(), containsString(FirstColumn.LOGO));
	}
}
