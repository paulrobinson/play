package org.my.cdiinmodule.module;

import org.my.cdiinmodule.application.MyBean;

import javax.inject.Inject;

public class MyApp {

    @Inject
    MyBean myBean;

    public String sayHello(String name)
    {
        return myBean.sayHello(name);
    }

}
