package org.my;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGCalculatorTest
{
    @Test
    public void testAddTestNG() throws Exception
    {
        Calculator calc = new Calculator();
        int result = calc.add(1, 2);

        Assert.assertEquals(result, 3);
    }
}
