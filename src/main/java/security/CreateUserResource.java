/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
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

    @GET
    public String test() {
        return ("HEJ!");
    }
    /**
     * Creates user from jsonString object. Example: { "username": "test", "password", "1234" }
     * @param jsonString
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String jsonString) throws UserAlreadyExistException, JOSEException {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        String username = json.get("username").getAsString();
        
        //If username is not valid
        if(!UserFacade.getInstance().verifyUsername(username)) {
            throw new UserAlreadyExistException("Username: "+ username + " is already in use");
        }
        
        String pass = json.get("password").getAsString();
        
        User u = new User(username, pass);
        u.addRole(new Role("user"));
        
        JWTCreator jwtc = new JWTCreator();
        
        String token = jwtc.createToken(username, u.getRolesAsStrings());
        System.out.println(token);
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("username", username);
        responseJson.addProperty("token", token);
        return Response.ok(new Gson().toJson(responseJson)).build();
        
    }
}
