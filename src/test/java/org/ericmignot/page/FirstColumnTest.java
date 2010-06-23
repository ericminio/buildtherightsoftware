package org.ericmignot.page;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FirstColumnTest {

	@Test public void
	content() {
		assertThat(new FirstColumn().html(), containsString(FirstColumn.LOGO));
	}
}
