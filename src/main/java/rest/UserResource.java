package rest;

import entity.Stocks;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import mappers.UserMapper;

/**
 * REST Web Service
 *
 */
@Path("user")
public class UserResource
{

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser()
    {
        String user = securityContext.getUserPrincipal().getName();
        return "\"Hello from USER: " + user + "\"";
    }

    /**
     * Takes in the users name and returns a list of said user's stocks if it is
     * the user who is logged in.
     *
     * @param userName
     * @return a json array of stocks
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{username}/list")
    @RolesAllowed("user")
    public Response getUserStockList(@PathParam("username") String userName)
    {
        String user = securityContext.getUserPrincipal().getName();

        if (userName.equals(user)) {
            StockFetcher sf = new StockFetcher();
            
            List<Stocks> userStockList = UserMapper.getInstance().getUserStockList(userName);
            
            Response result = sf.multiFetch(userStockList);
            return result.ok().build();
        } else {
            return Response.ok("Not allowed").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin()
    {
        String user = securityContext.getUserPrincipal().getName();
        return "\"Hello from ADMIN" + user + "\"";
    }
}
