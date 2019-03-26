package com.fjgonzalez.meli.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParserUtils {
	public static JSONObject parseStringToJSONObject(String jsonString) throws ParseException {

		JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
        
		return jsonObject;
	}
}