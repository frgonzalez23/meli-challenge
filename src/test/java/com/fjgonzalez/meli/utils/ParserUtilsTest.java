package com.fjgonzalez.meli.utils;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class ParserUtilsTest {

	@Test(expected = ParseException.class)
	public void testStringEmpty() throws ParseException{
		String dna="";
		ParserUtils.parseStringToJSONObject(dna);
	}
	@Test(expected = ParseException.class)
	public void testStringBadFormat() throws ParseException{
		String dna="{\"dna\": [,}";
		ParserUtils.parseStringToJSONObject(dna);
	}

}
