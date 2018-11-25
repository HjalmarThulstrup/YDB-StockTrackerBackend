package rest;

import com.google.gson.Gson;
import entity.Stocks;
import entity.User;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import mappers.StockMapper;
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
    @Path("/{username}/list")
    @RolesAllowed("user")
    public Response getUserStockList(@PathParam("username") String userName)
    {
        String user = securityContext.getUserPrincipal().getName();

        if (userName.equals(user)) {
            StockFetcher sf = new StockFetcher();

            List<Stocks> userStockList = UserMapper.getInstance("pu").getUserStockList(userName);

            return sf.multiSingleFetch(userStockList);
        } else {
            return Response.ok("Not allowed").build();
        }
    }

    /**
     * Takes in the users name and returns a list of said user's stocks if it is
     * the user who is logged in.
     *
     * @param userName
     * @param symbol
     * @return a json array of stocks
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/add/{stock}")
    @RolesAllowed("user")
    public Response addStockToList(@PathParam("username") String userName, @PathParam("stock") String symbol)
    {
        String user = securityContext.getUserPrincipal().getName();
        Gson gson = new Gson();
        if (userName.equals(user)) {
            StockMapper sm = StockMapper.getInstance("pu");

            boolean result = sm.addStockToFavourites(new User(userName, ""), symbol);

            if (result) {
                return Response.ok(gson.toJson("Stock added")).build();
            } else {
                return Response.ok("Stock not added").build();
            }
        } else {
            return Response.ok("Not allowed").build();
        }
    }

    /**
     * Takes in the users name and returns a list of said user's stocks if it is
     * the user who is logged in.
     *
     * @param userName
     * @param symbol
     * @return a json array of stocks
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/remove/{stock}")
    @RolesAllowed("user")
    public Response removeStockFromList(@PathParam("username") String userName, @PathParam("stock") String symbol)
    {
        String user = securityContext.getUserPrincipal().getName();

        if (userName.equals(user)) {
            StockMapper sm = StockMapper.getInstance("pu");

            boolean result = sm.removeStockFromFavourites(new User(userName, ""), symbol);

            if (result) {
                return Response.ok("Stock removed").build();
            } else {
                return Response.ok("Stock not removed").build();
            }
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
