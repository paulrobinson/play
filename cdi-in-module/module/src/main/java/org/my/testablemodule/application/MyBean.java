package org.my.testablemodule.application;

import javax.inject.Named;

/**
 * @Author paul.robinson@redhat.com 08/02/2012
 */
@Named
public class MyBean {

    public String sayHello(String name) {
        System.out.println("Invoked sayHello from module!!");
        return "Hello " + name;
    }
}
