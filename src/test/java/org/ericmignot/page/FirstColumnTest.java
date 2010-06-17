package org.ericmignot.page;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FirstColumnTest {

	@Test public void
	content() {
		assertThat(new FirstColumn().html(), equalTo(FirstColumn.LOGO));
	}
}
