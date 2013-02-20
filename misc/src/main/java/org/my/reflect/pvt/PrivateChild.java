package org.my.reflect.pvt;

public class PrivateChild extends PrivateParent
{
    private String privateChild()
    {
        return "privateChild";
    }

    public String publicChild()
    {
        return "publicChild";
    }
}
