package org.my.common.interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;

@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface Benchmarkable
{
}