package security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import exceptions.AuthenticationException;
import exceptions.GenericExceptionMapper;
import mappers.UserMapper;

@Path("login")
public class LoginEndpoint {



  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(String jsonString) throws AuthenticationException {

    JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
    String username = json.get("username").getAsString();
    String password = json.get("password").getAsString();

    //Todo refactor into facade
    try {
        JWTCreator jwtc = new JWTCreator();
      User user = UserMapper.getInstance("pu").getVeryfiedUser(username, password);
      String token = jwtc.createToken(username, user.getRolesAsStrings());
      JsonObject responseJson = new JsonObject();
      responseJson.addProperty("username", username);
      responseJson.addProperty("token", token);
      return Response.ok(new Gson().toJson(responseJson)).build();

    } catch (Exception ex) {
      if (ex instanceof AuthenticationException) {
        throw (AuthenticationException) ex;
      }
      Logger.getLogger(GenericExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
    }
    throw new AuthenticationException("Invalid username or password! Please try again");
  }

  
}

