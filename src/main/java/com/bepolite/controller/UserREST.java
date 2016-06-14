package com.bepolite.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bepolite.model.User;
import com.bepolite.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Path("/user")
public class UserREST {
	private static final Logger logger = LoggerFactory.getLogger(UserREST.class);
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private UserService userService;
	
    @GET
    @Path("/add/{firstName}/{lastName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) throws Exception {
    	String location = "addUser()";
    	logger.debug(location + " START");
    	User user = new User();
    	user.setFirstName(firstName);
    	user.setLastName(lastName);
    	logger.debug(location + " ADD New User " + user);
    	logger.debug(location + " END");
    	userService.saveOrUpdate(user);
        return Response.status(200).entity(mapper.writeValueAsString(user)).build();

    }
    
    @POST
    @Path("/profile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addProfile(User user){
    	return userService.addUser(user);
    }
    
    @GET
    @Path("/list/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUser(@PathParam("limit") String limit) throws Exception {
    	String location = "listUser()";
    	logger.debug(location + " START");
    	logger.debug(location + " Limit = " + limit);
    	logger.debug(location + " END");
    	int limitTmp = 0;
    	try {
    		limitTmp = Integer.parseInt(limit);
    	} catch (Exception e) {
    		
    	}
    	List<User> users = userService.listUser(limitTmp);
    	logger.debug(location + " user size  = " + users.size());
        return Response.status(200).entity(mapper.writeValueAsString(users)).build();
    }
    
    @GET
    @Path("/testcall")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkRest(){
    	return "Working";
    }
}