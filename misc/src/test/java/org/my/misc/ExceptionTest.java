package org.my.misc;

import org.junit.After;
import org.junit.Test;

/**
 * @author paul.robinson@redhat.com, 2012-02-14
 */
public class ExceptionTest {

    @After
    public void after()
    {
        System.out.println("After");
    }

    @Test
    public void testThrow()
    {
        throw new RuntimeException("");
    }
}
