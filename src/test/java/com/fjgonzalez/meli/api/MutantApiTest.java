package com.fjgonzalez.meli.api;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

public class MutantApiTest extends JerseyTest{

	   @Override
	    protected Application configure() {
	        return new ResourceConfig(MutantApi.class);
	    }
	    
	    @Test
	    public void testDnaMutant() {
	        JSONObject json = new JSONObject();

	        JSONArray jsonArray = new JSONArray();
	        jsonArray.add("AAAAGA");
	        jsonArray.add("CAGTGC");
	        jsonArray.add("TTATGT");
	        jsonArray.add("AGATGG");
	        jsonArray.add("CCCTTA");
	        jsonArray.add("TCACTG");

	        json.put("dna", jsonArray);
	        Response response = target("mutant").request().post(Entity.json(json.toString()));
	        assertEquals(200, response.getStatus());
	    }
	    @Test
	    public void testDnaHuman() {
	        JSONObject json = new JSONObject();

	        JSONArray jsonArray = new JSONArray();
	        jsonArray.add("AACAGA");
	        jsonArray.add("CAGTGC");
	        jsonArray.add("TTAACT");
	        jsonArray.add("AGATGG");
	        jsonArray.add("CTCTTA");
	        jsonArray.add("TCACTG");

	        json.put("dna", jsonArray);
	        Response response = target("mutant").request().post(Entity.json(json.toString()));
	        assertEquals(403, response.getStatus());
	    }
	    @Test
	    public void testBadRequest() {
	        JSONObject json = new JSONObject();

	        JSONArray jsonArray = new JSONArray();
	        jsonArray.add("AAAAG");
	        jsonArray.add("CAFTGC");
	        jsonArray.add("TTATGT");
	        jsonArray.add("AGATGG");
	        jsonArray.add("CCCTTA");
	        jsonArray.add("TCACTG");

	        json.put("dna", jsonArray);
	        Response response = target("mutant").request().post(Entity.json(json.toString()));
	        assertEquals(400, response.getStatus());
	    }
	    @Test
	    public void testMissingDnaField() {
	        JSONObject json = new JSONObject();

	        JSONArray jsonArray = new JSONArray();
	        jsonArray.add("AAAAGC");
	        jsonArray.add("CACTGC");
	        jsonArray.add("TTATGT");
	        jsonArray.add("AGATGG");
	        jsonArray.add("CCCTTA");
	        jsonArray.add("TCACTG");

	        json.put("dnaBAD", jsonArray);
	        Response response = target("mutant").request().post(Entity.json(json.toString()));
	        assertEquals(400, response.getStatus());
	    }


}
