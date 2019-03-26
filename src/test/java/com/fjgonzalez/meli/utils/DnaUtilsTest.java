package com.fjgonzalez.meli.utils;

import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.fjgonzalez.meli.utils.DnaUtils;
import com.fjgonzalez.meli.utils.ParserUtils;
public class DnaUtilsTest {

	@Test
	public void testValidDna() {
		JSONObject bodyJson;
		try {
			bodyJson = ParserUtils.parseStringToJSONObject("{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}");
			JSONArray dna=(JSONArray)bodyJson.get("dna");
			assertTrue(DnaUtils.isValidDna(dna));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}
	@Test
	public void testInvalidDnaNotNxN() {
		JSONObject bodyJson;
		try {
			bodyJson = ParserUtils.parseStringToJSONObject("{\"dna\":[\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}");
			JSONArray dna=(JSONArray)bodyJson.get("dna");
			assertFalse(DnaUtils.isValidDna(dna));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}
	@Test
	public void testInvalidDnaLetter() {
		JSONObject bodyJson;
		try {
			bodyJson = ParserUtils.parseStringToJSONObject("{\"dna\":[\"APPPPA\",\"CAGRGC\",\"TCXTGT\",\"AGXXGG\",\"XXXXTA\",\"TCACTG\"]}");
			JSONArray dna=(JSONArray)bodyJson.get("dna");
			assertFalse(DnaUtils.isValidDna(dna));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}
	@Test
	public void testInvalidDnaMinSize() {
		JSONObject bodyJson;
		try {
			bodyJson = ParserUtils.parseStringToJSONObject("{\"dna\":[\"AAA\",\"CCC\"]}");
			JSONArray dna=(JSONArray)bodyJson.get("dna");
			assertFalse(DnaUtils.isValidDna(dna));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}
	@Test
	public void testIsMutantHorizontalMatch() {
		
		JSONObject bodyJson;
		try {
			bodyJson = ParserUtils.parseStringToJSONObject("{\"dna\":[\"TTTTTT\",\"ATCGAG\",\"CCCCGT\",\"AGAAGG\",\"GTGTTA\",\"TCACTG\"]}");
			JSONArray dna=(JSONArray)bodyJson.get("dna");
			assertTrue(DnaUtils.isMutant(dna));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}
	@Test
	public void testIsMutantVerticalMatch() {
		
		JSONObject bodyJson;
		try {
			bodyJson = ParserUtils.parseStringToJSONObject("{\"dna\":[\"TATATG\",\"TTCGAG\",\"TGGCGG\",\"TGAAGG\",\"GTGTTA\",\"TCACTG\"]}");
			JSONArray dna=(JSONArray)bodyJson.get("dna");
			assertTrue(DnaUtils.isMutant(dna));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}
	@Test
	public void testIsMutantObliqueMatch() {
		
		JSONObject bodyJson;
		try {
			bodyJson = ParserUtils.parseStringToJSONObject("{\"dna\":[\"TATAAT\",\"ATCGTG\",\"GACTGT\",\"AGTAGG\",\"GTGTTA\",\"TCAGTG\"]}");
			JSONArray dna=(JSONArray)bodyJson.get("dna");
			assertTrue(DnaUtils.isMutant(dna));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}
	@Test
	public void testIsNotMutant() {
		
		JSONObject bodyJson;
		try {
			bodyJson = ParserUtils.parseStringToJSONObject("{\"dna\":[\"TATAAT\",\"ATCGTG\",\"GACAGT\", \"ACTAGG\", \"GAGTTA\", \"TCAGTG\"]}");
			JSONArray dna=(JSONArray)bodyJson.get("dna");
			assertFalse(DnaUtils.isMutant(dna));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}
}
