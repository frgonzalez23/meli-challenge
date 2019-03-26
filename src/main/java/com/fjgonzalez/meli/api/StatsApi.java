package com.fjgonzalez.meli.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fjgonzalez.meli.api.dao.StatsDAO;
import com.fjgonzalez.meli.api.model.Stats;
import com.fjgonzalez.meli.utils.InvalidStatsException;

@Path("/stats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatsApi {
	  @GET
	  public Response stats() {
		  try {  	        
      	      ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
              StatsDAO statsDAO = (StatsDAO) context.getBean("statsDAO");
              Stats stats = statsDAO.findByStatsId(1);
              if(stats==null)
              	throw new InvalidStatsException();
              JSONObject response = new JSONObject();
              response.put("mutant_count_dna", stats.getMutantCountDna());
              response.put("human_count_dna", stats.getHumanCountDna());
              response.put("ratio", stats.getRate());
              
			return Response.ok(response).status(Response.Status.OK).build();
  		}catch(InvalidStatsException e){
              return Response.status(Response.Status.FORBIDDEN).build();
  		}catch(Exception e) {
  			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
  		}
		  
	  }
}
