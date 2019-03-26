package com.fjgonzalez.meli.api;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fjgonzalez.meli.api.dao.DnaDAO;
import com.fjgonzalez.meli.api.model.Dna;
import com.fjgonzalez.meli.utils.DnaUtils;
import com.fjgonzalez.meli.utils.DuplicatedDnaException;
import com.fjgonzalez.meli.utils.InvalidDnaException;
import com.fjgonzalez.meli.utils.ParserUtils;

@Path("/mutant")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MutantApi {
	  @POST
	  public Response mutant(String body) {
		
            try {
    			JSONObject bodyJson=ParserUtils.parseStringToJSONObject(body);
    			JSONArray dna=(JSONArray)bodyJson.get("dna");
    			if(!DnaUtils.isValidDna(dna))
    				throw new InvalidDnaException();
    	        
        	    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
                DnaDAO dnaDAO = (DnaDAO) context.getBean("dnaDAO");
                Dna exists = dnaDAO.findByDnaId(Integer.toString(dna.hashCode()));
                if(exists!=null)
                	throw new DuplicatedDnaException();
                
    			if(DnaUtils.isMutant(dna))
    			{   dnaDAO.insert(new Dna(Integer.toString(dna.hashCode()), 1));
    				return Response.status(Response.Status.OK).build();
    			}
    			else {
    				dnaDAO.insert(new Dna(Integer.toString(dna.hashCode()), 0));
    				return Response.status(Response.Status.FORBIDDEN).build();
    			   }
    		}catch(ClassCastException | ParseException | InvalidDnaException e){
                return Response.status(Response.Status.BAD_REQUEST).build();
    		}catch(DuplicatedDnaException d) {
                return Response.status(Response.Status.CONFLICT).build();    			
    		}catch(Exception e) {
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    		}
	  }
}