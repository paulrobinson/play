package org.my.simple.ejb;

import javax.ejb.Stateless;

/**
 * @author paul.robinson@redhat.com, 2012-03-07
 */
@Stateless
public interface MyEJB {

    public String sayHello(String msg);
}
