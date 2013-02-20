package org.my.reflect;

import java.lang.reflect.Method;

public class Misc
{
    public String invokeMethodOnClass(String msg) throws Exception
    {
        Object object = new Message();
        Class clazz = object.getClass();
        Method method = clazz.getMethod("sayHello", String.class);

        return (String) method.invoke(object, msg);
    }


}
