package com.eNote.eNote_core;

import com.eNote.eNote_core.services.analytics.lib.CompletedTaskLiner;
import com.eNote.eNote_core.services.task.lib.RateResolver;
import com.eNote.eNote_core.services.task.lib.SpeedResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ENoteCoreApplicationTests {
	@Test
	public void dateFormatTest() throws ParseException {
		Date act = new Date();
		Assertions.assertEquals(
				new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(act.toString()),
				act
		);
	}
}
