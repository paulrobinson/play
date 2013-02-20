package org.my.simple.client;

import javax.xml.ws.WebServiceFeature;

/**
 * @author paul.robinson@redhat.com 24/01/2013
 */
public class MYFeature extends WebServiceFeature {

    @Override
    public String getID() {

        return this.getClass().getName();
    }
}
