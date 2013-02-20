package org.my.rendezvous;

import javax.jws.soap.SOAPBinding;

@WebService(serviceName = "RestaurantServiceATService", portName = "RestaurantServiceAT", name = "RestaurantServiceAT", targetNamespace = "http://www.jboss.org/narayana/quickstarts/wsat/simple/Restaurant")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@Remote(RestaurantServiceAT.class)
@Stateless
public class TemperatureConverter
{


    public double convertToCelsius(double f)
    {

        return ((f - 32) * 5 / 9);

    }


    public double convertToFarenheit(double c)
    {

        return ((c * 9 / 5) + 32);

    }


}