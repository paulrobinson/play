package org.my.junit;

import junit.framework.Assert;
import org.junit.Test;
import org.my.Calculator;

public class JUnitCalculatorTest
{
    @Test
    public void testAddJunit() throws Exception
    {
        Calculator calc = new Calculator();
        int result = calc.add(1, 2);

        Assert.assertEquals(result, 3);
    }

    @Test
    public void testSubtractJunit() throws Exception
    {
        Calculator calc = new Calculator();
        int result = calc.subtract(3, 2);

        Assert.assertEquals(result, 1);
    }
}
