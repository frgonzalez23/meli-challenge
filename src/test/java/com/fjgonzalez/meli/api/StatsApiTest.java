package com.fjgonzalez.meli.api;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class StatsApiTest extends JerseyTest{
	@Override
    protected Application configure() {
        return new ResourceConfig(StatsApi.class);
    }
	@Test
	public void testStats() {

        Response response = target("mutant").request().get();
        assertEquals(200, response.getStatus());
	}

}
