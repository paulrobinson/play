package org.my.reflect.pvt;

import org.junit.Assert;
import org.junit.Test;
import org.my.reflect.Message;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class InvokePrivatesTest
{
    @Test
    public void childTestDirect() throws Exception
    {
        Object object = new PrivateChild();
        Class clazz = object.getClass();
        Method method = clazz.getDeclaredMethod("publicChild");

        method.setAccessible(true);

        String result = (String) method.invoke(object);

        Assert.assertEquals("publicChild", result);
    }

    @Test
    public void childTestSearch() throws Exception
    {
        Object object = new PrivateChild();
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        String result = "";
        for (Method m : methods)
        {
            if (m.getName().equals("privateChild"))
            {
                m.setAccessible(true);
                result = (String) m.invoke(object);
            }
        }

        Assert.assertEquals("privateChild", result);
    }

    @Test
    public void parentTestDirect() throws Exception
    {
        Object object = new PrivateChild();
        Class clazz = object.getClass();
        Method method = clazz.getMethod("publicParent");

        method.setAccessible(true);

        String result = (String) method.invoke(object);

        Assert.assertEquals("publicParent", result);
    }

    @Test
    public void parentTestSearch() throws Exception
    {
        Object object = new PrivateChild();
        Class clazz = object.getClass();
        Method[] methods = clazz.getMethods();

        String result = "";
        for (Method m : methods)
        {
            if (m.getName().equals("publicParent"))
            {
                m.setAccessible(true);
                result = (String) m.invoke(object);
            }
        }

        Assert.assertEquals("publicParent", result);
    }

    @Test
    public void showMethodsTest()
    {
        Object object = new PrivateChild();
        Class clazz = object.getClass();

        System.out.println("Methods:");
        for (Method m : clazz.getMethods())
        {
            System.out.println(m.getName());
        }
        System.out.println("");

        System.out.println("Declared Methods:");
        for (Method m : clazz.getDeclaredMethods())
        {
            System.out.println(m.getName());
        }

    }

    @Test
    public void combineMethodsTest()
    {
        Object object = new PrivateChild();
        Class clazz = object.getClass();

        Method[] methods = clazz.getMethods();
        Method[] declaredMethods = clazz.getDeclaredMethods();

        List<Method> results = new LinkedList<Method>();

        for (Method m : methods)
        {
            if (!results.contains(m))
            {
                results.add(m);
            }
        }
        for (Method m : declaredMethods)
        {
            if (!results.contains(m))
            {
                results.add(m);
            }
        }

        Assert.assertTrue(results.size() == 12);
        assertContainsMethod(results, "publicChild");
        assertContainsMethod(results, "privateChild");
        assertContainsMethod(results, "publicParent");

        assertNotContainsMethod(results, "priavteParent");

    }

    private void assertContainsMethod(List<Method> methods, String methodName)
    {
        searchForMethod(methods, methodName, true);
    }

    private void assertNotContainsMethod(List<Method> methods, String methodName)
    {
        searchForMethod(methods, methodName, false);
    }

    private void searchForMethod(List<Method> methods, String methodName, boolean expectToFind)
    {
        boolean found = false;
        for (Method m : methods)
        {
            if (m.getName().equals(methodName))
            {
                found = true;
            }
        }

        if (!found && expectToFind)
        {
            Assert.fail("Method '" + methodName + "' not found");
        }
    }
}
