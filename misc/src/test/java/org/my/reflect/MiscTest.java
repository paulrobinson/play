package org.my.reflect;

import org.junit.Assert;
import org.junit.Test;

public class MiscTest
{
    @Test
    public void invokeMethodOnClassTest() throws Exception
    {
        Misc misc = new Misc();
        Assert.assertEquals("hello paul", misc.invokeMethodOnClass("paul"));
    }

}
