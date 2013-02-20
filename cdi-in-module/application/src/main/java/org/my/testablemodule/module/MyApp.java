package org.my.testablemodule.module;

import org.my.testablemodule.application.MyBean;

import javax.inject.Inject;

public class MyApp {

    @Inject
    MyBean myBean;

    public String sayHello(String name)
    {
        return myBean.sayHello(name);
    }

}
