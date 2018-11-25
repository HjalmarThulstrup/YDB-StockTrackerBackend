/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Role;
import entity.User;
import entity.UserFacade;
import exceptions.UserAlreadyExistException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author rlumh
 */
@Path("createUser")
public class CreateUserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CreateUserResource
     */
    public CreateUserResource() {
    }
    
    /**
     * 
     * @param jsonString
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String jsonString) throws UserAlreadyExistException {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        String username = json.get("username").getAsString();
        
        //If username is not valid
        if(!UserFacade.getInstance().verifyUsername(username)) {
            throw new UserAlreadyExistException("Username: "+ username + " is already in use");
        }
        
        String pass = json.get("password").getAsString();
        
        User u = new User(username, pass);
        u.addRole(new Role("user"));
        
        
        
    }

    /**
     * Retrieves representation of an instance of security.CreateUserResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CreateUserResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
