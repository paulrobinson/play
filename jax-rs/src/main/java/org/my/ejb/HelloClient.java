package org.my.ejb;

import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.inject.Inject;

/**
 * @Author paul.robinson@redhat.com 05/04/2012
 */
public class HelloClient {

    @Inject
    static Hello hello;

    public static void main(String[] args) {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());

        Hello client = ProxyFactory.create(Hello.class, "http://localhost:8080/jax-rs-1.0");
        System.out.println(client.sayHello("Paul - remote"));
        System.out.println(hello.sayHello("Paul - injected"));
    }

}
