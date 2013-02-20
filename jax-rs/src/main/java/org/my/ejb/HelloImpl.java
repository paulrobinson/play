package org.my.ejb;

import javax.ejb.Stateless;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @Author paul.robinson@redhat.com 05/04/2012
 */
@Stateless
public class HelloImpl implements Hello {

    public String sayHello(@QueryParam("name") String name) {
        return "Hello " + name;
    }
}
