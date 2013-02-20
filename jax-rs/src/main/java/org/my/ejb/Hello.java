package org.my.ejb;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * @Author paul.robinson@redhat.com 05/04/2012
 */
@Path("/")
public interface Hello {

    @GET
    @Path("basic")
    @Produces("text/plain")
    public String sayHello(@QueryParam("name")String name);
}
